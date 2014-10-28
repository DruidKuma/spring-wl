package com.epam.weblibrary.service.book;

import com.epam.weblibrary.entities.book.Book;
import com.epam.weblibrary.entities.book.Genre;
import com.epam.weblibrary.entities.user.User;
import com.epam.weblibrary.repositories.book.BookRepository;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import java.util.List;
import java.util.Locale;

/**
 * Interface for Book service layer
 * @author Iurii Miedviediev
 * @version 1.0 Build 12.09.2014
 */
public interface BookService {

    /**
     * Repository setter (for mock testing)
     * @param repo book repository
     */
    void setRepo(BookRepository repo);

    /**
     * Find book by given id
     * @param id book id
     * @return book with given id or null if not found
     */
    Book find(Long id);

    /**
     * Save given book in the DB
     * @param book book to save
     * @return saved book
     */
    Book save(Book book);

    /**
     * Delete book by given id
     * @param id book id
     */
    void delete(Long id);

    /**
     * Get the number of books in active orders of given user
     * @param user
     * @return integer, number of books in user's active orders
     */
    Integer countOrderedBooks(User user);

    /**
     * Find all existing in library genres
     * @return list of all genres
     */
    List<Genre> findAllGenres();

    /**
     * Get the page by given number
     * @param pageNumber
     * @return page of books
     */
    Page<Book> findAll(Integer pageNumber);

    /**
     * Get page of books with given genre
     * @param genre book genre
     * @param page number of page
     * @return page of books with given genre
     */
    Page<Book> findAllByGenre(Genre genre, Integer page);

    /**
     * Get page of books with given search criteria
     * @param criteria criteria of search (author, title)
     * @param search keyword to search, entered by the user
     * @param page page number
     * @return page of books of given criteria
     */
    Page<Book> findBooksByCriteria(String criteria, String search, Integer page);

    /**
     * Check if book is not present in any active order
     * @param book
     * @return true if book is not present in orders, false otherwise
     */
    boolean checkAvailability(Book book);

    /**
     * Check that incoming file is valid cover
     * @param cover file to check
     * @param errors errors form to populate
     * @param locale locale for the right error messages
     * @param messageSource
     */
    void checkCover(CommonsMultipartFile cover, BindingResult errors, Locale locale, MessageSource messageSource);
}