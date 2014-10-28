package com.epam.weblibrary.service.order;

import com.epam.weblibrary.entities.book.Book;
import com.epam.weblibrary.entities.order.Order;
import com.epam.weblibrary.entities.user.User;
import com.epam.weblibrary.repositories.book.BookRepository;
import com.epam.weblibrary.repositories.order.OrderRepository;
import com.epam.weblibrary.repositories.user.UserRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import lombok.SneakyThrows;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Order service layer
 * @author Iurii Miedviediev
 * @version 1.0 Build 17.09.2014
 */
@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findByUser(User user) {
        return orderRepository.findByUser(user);
    }

    @Override
    public Order createOrder(List<Book> cart, User user) {
        Order order = new Order(cart, new Date(), user);
        orderRepository.saveAndFlush(order);

        //generate 300x300 pixels QR code with the order url
        order.setQrcode(QRCode.from("http://weblibrary-druidkuma.rhcloud.com/order/" + order.getId())
                .to(ImageType.PNG).withSize(300, 300).stream().toByteArray());

        orderRepository.saveAndFlush(order);
        return order;
    }

    @Override
    public Order find(Long id) {
        return orderRepository.findOne(id);
    }

    @Override
    @SneakyThrows
    public byte[] createQrPdfDocument(Long id) {

        //configure document to convert it then to byte array
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document doc = new Document(PageSize.A4);
        PdfWriter.getInstance(doc, byteArrayOutputStream);
        doc.open();

        //populate the document
        createPDF(doc, id);

        doc.close();

        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public void save(Order order) {
        orderRepository.saveAndFlush(order);
    }

    @Override
    public boolean chargeMoneyFromCard(String token, Integer totalPrice) {
        try {
            // Secret personal Stripe API key for testing
            Stripe.apiKey = "sk_test_kzc5rFRZtdnsdYpsw3xL1VJr";
            Map<String, Object> chargeMap = new HashMap<String, Object>();
            chargeMap.put("amount", totalPrice);
            chargeMap.put("currency", "usd");
            chargeMap.put("card", token);
            Charge.create(chargeMap);
            return true;
        } catch (CardException e) {
            // Since it's a decline, CardException will be caught
            LOGGER.error("Card is declined: " + token);
            LOGGER.error(e.getMessage());
        } catch (InvalidRequestException e) {
            // Invalid parameters were supplied to Stripe's API
            LOGGER.error("Payment is declined: wrong parameters were supplied to Stripe's API");
            LOGGER.error(e.getMessage());
        } catch (AuthenticationException e) {
            // Authentication with Stripe's API failed
            LOGGER.error("Payment is declined: Authentication with Stripe's API failed");
            LOGGER.error(e.getMessage());
        } catch (APIConnectionException e) {
            // Network communication with Stripe failed
            LOGGER.error("Network communication with Stripe failed");
            LOGGER.error(e.getMessage());
        } catch (StripeException e) {
            // Display a very generic error to the user
            LOGGER.error("Payment is declined: Stripe has thrown an undefined error");
            LOGGER.error(e.getMessage());
        }
        return false;
    }

    @Override
    public void delete(Long orderId) {
        orderRepository.delete(orderId);
        orderRepository.flush();
    }

    /**
     * populate the PDF document
     * @param doc document to populate
     * @param id order id
     * @throws DocumentException
     * @throws IOException
     */
    private void createPDF(Document doc, Long id) throws DocumentException, IOException {
        Order order = orderRepository.findOne(id);
        byte[] qrCode = order.getQrcode();

        //set background
        Image background = Image.getInstance("http://i61.tinypic.com/htbvd4.png");
        background.scaleAbsolute(PageSize.A4);
        background.setAbsolutePosition(0, 0);
        doc.add(background);

        //set title of the document
        Paragraph title = new Paragraph("Web-Library Order");
        title.setSpacingBefore(100.0f);
        title.setAlignment(Element.ALIGN_CENTER);
        doc.add(title);

        //table with books from the order
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[] {3.0f, 2.0f, 2.0f, 2.0f});
        table.setSpacingBefore(10);

        // define font for table header row
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.WHITE);

        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(new BaseColor(0,44,120));
        cell.setPadding(5);

        // write table header
        cell.setPhrase(new Phrase("Book Title", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Author", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Year", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Price", font));
        table.addCell(cell);

        Long totalPrice = 0L;

        // write table row data
        for (Book book : order.getItems()) {
            table.addCell(book.getTitle());
            table.addCell(book.getAuthor());
            table.addCell(book.getYear().toString());
            table.addCell("$" + ((Double) (book.getPrice() / 100.0)).toString());
            totalPrice += book.getPrice();
        }

        doc.add(table);

        //set paragraph with total price
        Paragraph total = new Paragraph("Total price is: $" + totalPrice/100.0);
        total.setAlignment(Element.ALIGN_RIGHT);
        doc.add(total);

        //set paragraph with discount price
        if(order.getUser().getDiscount() > 0) {
            Paragraph discount = new Paragraph("Price with discount is: $" +
                    Math.round(totalPrice - totalPrice * order.getUser().getDiscount())/100.0);
            discount.setAlignment(Element.ALIGN_RIGHT);
            doc.add(discount);
        }

        //set paragraph with message to the user
        String login = order.getUser().getCredentials().getLogin();
        Paragraph message = new Paragraph("Dear " + login + ", please, print this document and show it to the librarian to receive your order.");
        message.setSpacingBefore(30.0f);
        doc.add(message);

        //place the qr code
        Image image = Image.getInstance(qrCode);
        image.setAlignment(Image.ALIGN_CENTER);
        doc.add(image);
    }
}
