package com.epam.weblibrary.controllers;

import com.epam.weblibrary.entities.book.Book;
import com.epam.weblibrary.entities.book.BookCover;
import com.epam.weblibrary.entities.forms.BookForm;
import com.epam.weblibrary.entities.order.Order;
import com.epam.weblibrary.entities.order.OrderStatus;
import com.epam.weblibrary.entities.user.Roles;
import com.epam.weblibrary.entities.user.User;
import com.epam.weblibrary.service.book.BookService;
import com.epam.weblibrary.service.order.OrderService;
import com.epam.weblibrary.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import javax.validation.Valid;
import java.util.Locale;

/**
 * Controller for all admin functions and features
 * @author Iurii Miedviediev
 * @version 1.0 Build 24.09.14
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private BookService bookService;

    @Autowired
    private MessageSource messageSource;

    //SLF4J logger
    private final static Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    /**
     * Binder for handling the incoming multipart files (images)
     * @param binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(byte[].class,
                new ByteArrayMultipartFileEditor());
    }

    /**
     * Get Admin control panel
     * @param model
     * @return name of the view
     */
    @RequestMapping(value = "/panel", method = RequestMethod.GET)
    public String getControlPanel(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("orders", orderService.findAll());
        return "admin/panel";
    }

    /**
     * Remove given user from the data source
     * @param id id of the user
     * @return string, redirecting back to panel
     */
    @RequestMapping(value = "/user", params = "remove")
    public String removeUser(@RequestParam Long id) {
        userService.delete(id);
        LOGGER.info("User with id: " + id + " was deleted");
        return "redirect:panel";
    }

    /**
     * Get page for adding new books
     * @param bookForm form to populate
     * @param result result where the errors would be written
     * @param model
     * @return name of the view
     */
    @RequestMapping(value = "/book", params = "add", method = RequestMethod.GET)
    public String getAddBook(@ModelAttribute BookForm bookForm, BindingResult result, Model model) {
        model.addAttribute("genres", bookService.findAllGenres());
        return "book/add";
    }

    /**
     * Add new Book to the DB
     * @param bookForm populated form
     * @param result possible errors
     * @param model
     * @return name of the view
     */
    @RequestMapping(value = "/book", params = "add", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public String postAddBook(@ModelAttribute @Valid BookForm bookForm,
                                 BindingResult result, Model model, Locale locale) {

        //get cover and book from the form separately
        CommonsMultipartFile myCover = bookForm.getCover();
        Book book = bookForm.getBook();

        //if cover is of wrong type or has too big size, add corresponding errors
        bookService.checkCover(myCover, result, locale, messageSource);

        //if there are some errors, return the form back
        if(result.hasErrors()) {
            model.addAttribute("genres", bookService.findAllGenres());
            return "book/add";
        }

        //if cover is not empty, tie it with the book
        if(!myCover.isEmpty()) book.setCover(new BookCover(myCover.getBytes()));
        book = bookService.save(book);
        LOGGER.info("New book with id: " + book.getId() + " was added");
        return "redirect:/book?id=" + book.getId();
    }

    /**
     * Get form for book editing
     * @param id book id
     * @param bookForm form to populate
     * @param result possible errors
     * @param model
     * @return name of the view
     */
    @RequestMapping(value = "/book", params = "edit", method = RequestMethod.GET)
    public String getEditBook(@RequestParam Long id, @ModelAttribute BookForm bookForm, BindingResult result, Model model) {
        BookForm form = new BookForm();
        form.setBook(bookService.find(id));
        model.addAttribute("bookForm", form);
        model.addAttribute("genres", bookService.findAllGenres());
        return "book/edit";
    }

    /**
     * Edit the book and save the edits to the DB
     * @param bookForm populated form
     * @param result possible errors
     * @param model
     * @return redirect to the book view
     */
    @RequestMapping(value = "/book", params = "edit", method = RequestMethod.POST)
    public String postEditBook(@ModelAttribute @Valid BookForm bookForm, BindingResult result,
                               Model model, Locale locale) {

        //get cover and book from the form separately
        CommonsMultipartFile myCover = bookForm.getCover();
        Book book = bookForm.getBook();

        //if cover is of wrong type or has too big size, add corresponding errors
        bookService.checkCover(myCover, result, locale, messageSource);

        //if there are some errors, return the form back
        if(result.hasErrors()) {
            model.addAttribute("genres", bookService.findAllGenres());
            return "book/edit";
        }

        //if new cover is empty, tie the book with its previous cover
        if(myCover.isEmpty()) {
            Book oldBook = bookService.find(book.getId());
            book.setCover(oldBook.getCover());
        } else {
            book.setCover(new BookCover(myCover.getBytes()));
        }

        bookService.save(book);
        LOGGER.info("Book with id: " + book.getId() + " was edited");
        return "redirect:/book?id=" + book.getId();
    }

    /**
     * Delete book from the library
     * @param id book id
     * @return redirect to the books view
     */
    @RequestMapping(value = "/book", params = "delete", method = RequestMethod.POST)
    public String postDeleteBook(@RequestParam Long id) {
        bookService.delete(id);
        LOGGER.info("Book with id " + id + " was deleted");
        return "redirect:/books";
    }

    /**
     * Update particular user's discount
     * @param id id of the user
     * @param newDiscount
     * @return redirect back to the admin panel
     */
    @RequestMapping(value = "/user", params = "discount", method = RequestMethod.POST)
    public String updateUserDiscount(@RequestParam Long id, @RequestParam Long newDiscount) {

        //if new discount not empty and valid
        if(newDiscount != null && newDiscount >= 0 && newDiscount <= 100) {
            User user = userService.find(id);
            user.setDiscount((float) (newDiscount/100.));
            userService.save(user);
            LOGGER.info("User with id: " + user.getId() + " was given a new discount: " + newDiscount + "%");
        }
        return "redirect:panel";
    }

    /**
     * Update role of the particular user
     * @param id id of the user
     * @param newRole
     * @return redirect back to the admin panel
     */
    @RequestMapping(value = "/user", params = "role", method = RequestMethod.POST)
    public String updateUserRole(@RequestParam Long id, @RequestParam Roles newRole) {
        User user = userService.find(id);
        user.getCredentials().setRole(newRole);
        userService.save(user);
        LOGGER.info("User with id: " + user.getId() + " was given a new role: " + newRole.toString());
        return "redirect:panel";
    }

    /**
     * Update the status of the order
     * @param id order id
     * @param newStatus
     * @return redirect back to admin panel
     */
    @RequestMapping(value = "/changestatus", method = RequestMethod.POST)
    public String updateOrderStatus(@RequestParam Long id, @RequestParam OrderStatus newStatus) {
        if(newStatus.equals(OrderStatus.RETURNED)) {
            orderService.delete(id);
            LOGGER.info("Order #" + id + " was returned");
            return "redirect:panel";
        } else {
            Order order = orderService.find(id);
            order.setStatus(newStatus);
            LOGGER.info("Order #" + order.getId() + " is " + newStatus.toString());
            orderService.save(order);
            return "redirect:/order/" + order.getId();
        }
    }

    /**
     * Get the project diagrams
     * @return name of the view
     */
    @RequestMapping(value = "/diagrams")
    public String getDiagramsPage() {
        return "other/diagrams";
    }
}
