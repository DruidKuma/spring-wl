package com.epam.weblibrary.repositories.book;

import com.epam.weblibrary.entities.book.Book;
import com.epam.weblibrary.entities.book.Genre;
import com.epam.weblibrary.entities.user.User;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.List;

import static junit.framework.TestCase.assertNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Tests for Book Repository
 * @author Iurii Miedviediev
 * @version 1.0 Build 14.10.14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/test-context.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:config/test-dataset.xml")
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    private PageRequest request;

    @Before
    public void setUp() {
        request = new PageRequest(0, 9, Sort.Direction.DESC, "title");
    }

    @Test
    public void FindOrderedBooksReturnsEmptyListGivenNull() {
        List<Book> bookList = bookRepository.findOrderedBooks(null);
        assertThat(bookList.size(), is(0));
    }

    @Test
    public void FindOrderedBooksReturnsRightBooksGivenUserWithOrders() {
        User user = new User();
        user.setId(1L);
        List<Book> bookList = bookRepository.findOrderedBooks(user);
        assertThat(bookList.size(), is(1));
        assertThat(bookList.get(0), allOf(
                hasProperty("id", is(1L)),
                hasProperty("title", is("Harry Potter")),
                hasProperty("author", is("J.Rowling"))
        ));
    }

    @Test
    public void FindOrderedBooksReturnsEmptyListGivenUserWithoutOrders() {
        User user = new User();
        user.setId(2L);
        List<Book> bookList = bookRepository.findOrderedBooks(user);
        assertThat(bookList.size(), is(0));
    }

    @Test
    public void FindAvailableReturnsListOfOneBook() {
        Page<Book> bookPage = bookRepository.findAvailable(request);
        assertThat(bookPage.getContent().size(), is(1));
        assertThat(bookPage.getContent().get(0), allOf(
                hasProperty("id", is(2L)),
                hasProperty("title", is("Doctor Sleep")),
                hasProperty("author", is("Stephen King"))
        ));
    }

    @Test
    public void FindAllGenresWorksProperly() {
        List<Genre> genres = bookRepository.findAllGenres();
        assertThat(genres.size(), is(2));
    }

    @Test
    public void FindByGenreReturnsRightBooksGivenExistingGenre() {
        Page<Book> bookList = bookRepository.findByGenre(Genre.Fantasy, request);
        assertThat(bookList.getContent().size(), is(1));
        assertThat(bookList.getContent().get(0), allOf(
                hasProperty("id", is(1L)),
                hasProperty("title", is("Harry Potter")),
                hasProperty("author", is("J.Rowling"))
        ));
    }

    @Test
    public void FindByGenreReturnsEmptyPageGivenUnExistingGenre() {
        Page<Book> bookPage = bookRepository.findByGenre(Genre.IT, request);
        assertThat(bookPage.getContent().size(), is(0));
    }

    @Test
    public void FindByGenreReturnsEmptyListGivenNull() {
        Page<Book> bookPage = bookRepository.findByGenre(null, request);
        assertThat(bookPage.getContent().size(), is(0));
    }

    @Test
    public void FindAllCorrectlyFindsBookGivenAuthorSpecification() {
        Page<Book> bookPage = bookRepository.findAll(BookSpecs.containsKeywordInAuthor("Rowling"), request);
        assertThat(bookPage.getContent().size(), is(1));
        assertThat(bookPage.getContent().get(0), allOf(
                hasProperty("id", is(1L)),
                hasProperty("title", is("Harry Potter")),
                hasProperty("author", is("J.Rowling"))
        ));
    }

    @Test
    public void FindAllReturnsEmptyPageGivenUnExistingAuthor() {
        Page<Book> bookPage = bookRepository.findAll(BookSpecs.containsKeywordInAuthor("E.A.Po"), request);
        assertThat(bookPage.getTotalElements(), is(0L));
    }

    @Test
    public void FindAllReturnsPageWithAllBooksGivenNull() {
        Page<Book> bookPage = bookRepository.findAll(null, request);
        assertThat(bookPage.getTotalElements(), is(2L));
    }

    @Test
    public void FindAllCorrectlyFindsBookGivenTitleSpecification() {
        Page<Book> bookPage = bookRepository.findAll(BookSpecs.containsKeywordInTitle("Sleep"), request);
        assertThat(bookPage.getContent().size(), is(1));
        assertThat(bookPage.getContent().get(0), allOf(
                hasProperty("id", is(2L)),
                hasProperty("title", is("Doctor Sleep")),
                hasProperty("author", is("Stephen King"))
        ));
    }

    @Test
    public void FindAllReturnsEmptyPageGivenUnExistingTitle() {
        Page<Book> bookPage = bookRepository.findAll(BookSpecs.containsKeywordInTitle("Dummy Book"), request);
        assertThat(bookPage.getTotalElements(), is(0L));
    }

    @Test
    public void FindInOrdersReturnsCorrectBookGivenItsId() {
        Book book = bookRepository.findInOrders(1L);
        assertThat(book, allOf(
                hasProperty("id", is(1L)),
                hasProperty("title", is("Harry Potter")),
                hasProperty("author", is("J.Rowling"))
        ));
    }

    @Test
    public void FindInOrdersReturnsNullGivenIdOfNotOrderedBook() {
        Book book = bookRepository.findInOrders(2L);
        assertNull(book);
    }

    @Test
    public void FindInOrdersReturnsNullGivenUnexistingId() {
        Book book = bookRepository.findInOrders(3L);
        assertNull(book);
    }

    @Test
    public void FindInOrdersReturnsNullGivenNull() {
        assertNull(bookRepository.findInOrders(null));
    }
}
