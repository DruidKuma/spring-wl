package com.epam.weblibrary.repositories.book;

import com.epam.weblibrary.entities.book.Book;
import com.epam.weblibrary.entities.book.Genre;
import com.epam.weblibrary.entities.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

/**
 * Repository for books
 * @author Iurii Miedviediev
 * @version 1.0 Build 08.09.2014
 */
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    /**
     * Find a page of all books that not appear in any active orders
     * @param pageable
     * @return page of available books
     */
    @Query("SELECT b FROM books b WHERE NOT EXISTS (SELECT o FROM orders o WHERE b MEMBER OF o.items)")
    Page<Book> findAvailable(Pageable pageable);

    /**
     * Find all books, ordered (and not returned) by the given user
     * @param user
     * @return list of all ordered books by the user
     */
    @Query("SELECT o.items from orders o where (?1) = o.user and (o.status = 'WAITING' or o.status = 'TAKEN')")
    List<Book> findOrderedBooks(User user);

    /**
     * Find all genres, appeared in the library
     * @return list of all appeared genres
     */
    @Query("SELECT DISTINCT b.genre from books b")
    List<Genre> findAllGenres();

    /**
     * Find page of books by given genre
     * @param genre
     * @param request pageable
     * @return page of books of requested genre
     */
    Page<Book> findByGenre(Genre genre, Pageable request);

    /**
     * Find a page of books according to given specification
     * @param spec
     * @param request pageable
     * @return page of books which satisfies given criteria
     */
    Page<Book> findAll(Specification<Book> spec, Pageable request);

    /**
     * Find book by its id, search only in active orders
     * @param id book id
     * @return found Book or null if not found
     */
    @Query("SELECT b FROM books b WHERE b.id = (?1) AND EXISTS (SELECT o FROM orders o WHERE b MEMBER OF o.items)")
    Book findInOrders(Long id);
}
