package com.epam.weblibrary.controllers;

import com.epam.weblibrary.entities.book.Book;
import com.epam.weblibrary.entities.book.Genre;
import com.epam.weblibrary.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

/**
 * Controller for all functions and features related to books
 * @author Iurii Miedviediev
 * @version 1.0 Build 08.09.14
 */
@Controller
@SessionAttributes({"cart"})
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * Main handler showing all wanted books to the user
     * @param criteria search criteria (author, title)
     * @param search search string, that user entered
     * @param genre genre of the book
     * @param page
     * @param model
     * @return name of the view
     */
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String getPagedBookList(@RequestParam(required = false) String criteria,
                                   @RequestParam(required = false) String search,
                                   @RequestParam(required = false) Genre genre,
                                   @RequestParam(required = false) Integer page, Model model) {
        //if cart is not populated, populate it
        if(!model.containsAttribute("cart")) {
            model.addAttribute("cart", new ArrayList<Book>());
        }
        model.addAttribute("genres", bookService.findAllGenres());

        if(page == null) page = 1;

        Page<Book> pages;

        //Depending on what books user wanted, get the right page (by genre, by search, or all books)
        if(genre != null) {
            pages = bookService.findAllByGenre(genre, page);
            model.addAttribute("jenre", genre);
        } else if (criteria != null && criteria.length() > 0) {
            pages = bookService.findBooksByCriteria(criteria, search, page);
            model.addAttribute("criteria", criteria);
            model.addAttribute("search", search);
        } else {
            pages = bookService.findAll(page);
        }

        //pagination staff
        int current = pages.getNumber() + 1;
        int begin = Math.max(1, current - 3);
        int end = Math.min(begin + 3, pages.getTotalPages());

        //populate the model
        model.addAttribute("books", pages);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("contents", pages.getContent());
        return "book/list";
    }

    /**
     * Get book detailed view
     * @param id book id
     * @param model
     * @return name of the view
     */
    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public String getViewBook(@RequestParam Long id, Model model) {
        Book book = bookService.find(id);
        model.addAttribute("book", book);
        model.addAttribute("available", bookService.checkAvailability(book));
        return "book/view";
    }

    /**
     * Get the image of book's cover (Lazy fetching)
     * @param id book id
     * @return response consisting of bytes representing the right book cover
     */
    @RequestMapping("/book-cover/{id}")
    public ResponseEntity<byte[]> getBookCover(@PathVariable("id") Long id) {

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        Book book = bookService.find(id);
        return new ResponseEntity<byte[]>(book.getCover().getImage(), headers, HttpStatus.CREATED);
    }
}
