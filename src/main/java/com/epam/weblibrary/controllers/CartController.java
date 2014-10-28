package com.epam.weblibrary.controllers;

import com.epam.weblibrary.entities.book.Book;
import com.epam.weblibrary.entities.user.User;
import com.epam.weblibrary.entities.user.UserCredentials;
import com.epam.weblibrary.service.book.BookService;
import com.epam.weblibrary.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Locale;

/**
 * Controller for all functions and features related to shopping cart
 * @author Iurii Miedviediev
 * @version 1.0 Build 17.09.14
 */
@Controller
@RequestMapping("/cart")
@SessionAttributes({"cart"})
public class CartController {

    @Autowired
    BookService bookService;

    @Autowired
    UserService userService;

    @Autowired
    MessageSource messageSource;

    //restrict the user to take more than 5 books at once
    private static final int MAX_BOOKS_TAKEN = 5;

    /**
     * Add new Book to the shopping cart
     * @param bookId book id
     * @param principal user
     * @param cart shopping cart of the current user
     * @param model
     * @return redirect to the cart view
     */
    @RequestMapping(params = "create", method = RequestMethod.GET)
     public String addBookToCart(@RequestParam("id") Long bookId, @AuthenticationPrincipal UserCredentials principal,
                             @ModelAttribute("cart") List<Book> cart, Model model, Locale locale) {

        Book book = bookService.find(bookId);

        //if the book was deleted
        if(book == null) {
            model.addAttribute("error", messageSource.getMessage("cart.error.notexist", null, locale));
            return getCartView(model, principal, cart, null);
        }
        //if book is already in the cart
        else if(cart.contains(book)) {
            model.addAttribute("error", messageSource.getMessage("cart.error.exist", null, locale));
            return getCartView(model, principal, cart, null);
        }

        User user = userService.findUserByCredentials(principal);

        //count books in the cart and already taken or ordered by the user
        if(cart.size() + bookService.countOrderedBooks(user) >= MAX_BOOKS_TAKEN) {
            model.addAttribute("maxTakenError", messageSource.getMessage("cart.error.exceed", null, locale));
            return getCartView(model, principal, cart, null);
        }

        cart.add(book);
        return "redirect:cart";
    }

    /**
     * Remove the book from the cart
     * @param id book id
     * @param cart shopping cart of the current user
     * @return redirect back to the cart view
     */
    @RequestMapping(params = "remove", method = RequestMethod.GET)
    public String removeBookFromCart(@RequestParam Long id, @ModelAttribute("cart") List<Book> cart) {
        cart.remove(bookService.find(id));
        return "redirect:cart";
    }

    /**
     * Get the view of the shopping cart
     * @param model
     * @param principal current user
     * @param cart shopping cart of the current user
     * @return name of the view
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getCartView(Model model, @AuthenticationPrincipal UserCredentials principal,
                              @ModelAttribute("cart") List<Book> cart,
                              @RequestParam(required = false) String unavailable) {

        //count the total price of the order
        Long totalPrice = 0L;
        for(Book book : cart) {
            totalPrice += book.getPrice();
        }

        //find the user to get his/her discount
        User user = userService.findUserByCredentials(principal);

        //if current user was removed by the administrator
        if(user == null) {
            return "redirect:/j_spring_security_logout";
        }

        //add error message given by the order controller
        if(unavailable != null) {
            model.addAttribute("unavailable", unavailable);
        }

        model.addAttribute("total", totalPrice);
        model.addAttribute("discount", Math.round(totalPrice - totalPrice * user.getDiscount()));
        return "order/shoppingcart";
    }
}
