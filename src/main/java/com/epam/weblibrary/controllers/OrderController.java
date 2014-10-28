package com.epam.weblibrary.controllers;

import com.epam.weblibrary.entities.book.Book;
import com.epam.weblibrary.entities.order.Order;
import com.epam.weblibrary.entities.user.Roles;
import com.epam.weblibrary.entities.user.User;
import com.epam.weblibrary.entities.user.UserCredentials;
import com.epam.weblibrary.service.book.BookService;
import com.epam.weblibrary.service.order.OrderService;
import com.epam.weblibrary.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Locale;

/**
 * Controller for all functions and features related to orders
 * @author Iurii Miedviediev
 * @version 1.0 Build 18.09.14
 */
@Controller
@RequestMapping("/order")
@SessionAttributes({"cart"})
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @Autowired
    MessageSource messageSource;

    //SLF4J logger
    private final static Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    /**
     * Display all user active orders
     * @param model
     * @param principal current user
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String displayUserOrders(Model model, @AuthenticationPrincipal UserCredentials principal) {
        User user = userService.findUserByCredentials(principal);

        //if current user was deleted by the administrator
        if(user == null) {
            return "redirect:/j_spring_security_logout";
        }

        model.addAttribute("orders", orderService.findByUser(user));
        return "order/userorders";
    }

    /**
     * Create new order
     * @param principal current user
     * @param cart shopping cart
     * @param token payment token with card data
     * @return redirect to the created order, or to 403 page, if card is declined
     */
    @RequestMapping(method = RequestMethod.POST)
    public String createNewOrder(@AuthenticationPrincipal UserCredentials principal,
                                 @ModelAttribute("cart") List<Book> cart,
                                 @RequestParam(value="stripeToken") String token,
                                 Model model, Locale locale) {

        //check the availability of each book (if not already ordered)
        for(Book book : cart) {
            if(!bookService.checkAvailability(book)) {
                cart.remove(book);
                model.addAttribute("unavailable",
                        book.getTitle() + " " + messageSource.getMessage("cart.error.unavailable", null, locale));
                return "redirect:/cart";
            }
        }

        User user = userService.findUserByCredentials(principal);

        //if current user was deleted by the administrator
        if(user == null) {
            return "redirect:/j_spring_security_logout";
        }

        //count total price of the order
        Long totalPrice = 0L;
        for(Book book : cart) {
            totalPrice += book.getPrice();
        }

        //charge the money from the card and redirect to the created order
        if(orderService.chargeMoneyFromCard(token, Math.round(totalPrice - totalPrice * user.getDiscount()))) {
            Order newOrder = orderService.createOrder(cart, user);
            LOGGER.info("New Order with id: " + newOrder.getId() + " for user #" + user.getId() + " was created");
            cart.clear();
            return "redirect:order/"+newOrder.getId();
        }

        //redirect to the 403 page, if card is declined
        LOGGER.info("Card for user #" + user.getId() + " was declined");
        throw new AccessDeniedException("Your card is declined");
    }

    /**
     * Get the detailed view of the order or throw an access denied exception
     * The order is allowed to see only to user who created it or to the administrator
     * @param id order id
     * @param user current user
     * @param model
     * @return name of the view
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String displayOrderDetails(@PathVariable("id") Long id, @AuthenticationPrincipal UserCredentials user, Model model) {
        Order order = orderService.find(id);
        Long orderCreatorId = order.getUser().getCredentials().getId();

        //if user created the order or have admin role, permit him to see the details
        if(user.getRole().equals(Roles.ROLE_ADMIN) || orderCreatorId.equals(user.getId())) {
            model.addAttribute("order", orderService.find(id));
            return "order/details";
        }

        //redirect to the 403 page
        LOGGER.info("User #" + user.getId() + " tried to access denied page");
        throw new AccessDeniedException("You are not permitted to see this order");
    }

    /**
     * Get the image of QR code
     * @param id order id
     * @return response consisting of bytes representing corresponding qr code
     */
    @RequestMapping(value = "/qr/{id}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> renderOrderQrCode(@PathVariable("id") Long id) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        Order order = orderService.find(id);

        return new ResponseEntity<byte[]>(order.getQrcode(), headers, HttpStatus.CREATED);
    }

    /**
     * Generate PDF document of the order
     * @param id order id
     * @param user current user
     * @return PDF document
     */
    @RequestMapping(value = "/{id}", params = "pdf", method = RequestMethod.GET, produces = "application/pdf; charset=utf-8")
    @ResponseBody
    public byte[] generatePdf(@PathVariable("id") Long id, @AuthenticationPrincipal UserCredentials user) {

        Order order = orderService.find(id);
        Long orderCreatorId = order.getUser().getCredentials().getId();

        //if user have access to this page
        if(user.getRole().equals(Roles.ROLE_ADMIN) || orderCreatorId.equals(user.getId())) {
            return orderService.createQrPdfDocument(id);
        }

        //redirect to the 403 page
        LOGGER.info("User #" + user.getId() + " tried to access denied page");
        throw new AccessDeniedException("You are not allowed to see this order");
    }
}
