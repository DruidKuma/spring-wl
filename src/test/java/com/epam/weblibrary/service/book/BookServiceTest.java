package com.epam.weblibrary.service.book;

import com.epam.weblibrary.entities.book.Book;
import com.epam.weblibrary.entities.book.Genre;
import com.epam.weblibrary.repositories.book.BookRepository;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for Book Service Layer
 * @author Iurii Miedviediev
 * @version 1.0 Build 15.10.14
 */
public class BookServiceTest {

    private BookRepository bookRepoMock;
    private BookService bookService;

    @Before
    public void setUp() {
        bookRepoMock = mock(BookRepository.class);
        bookService = new BookServiceImpl();
        bookService.setRepo(bookRepoMock);
    }

    @Test
     public void FindWorksProperlyForExistingId() {
        Book book = new Book("Test Title", "Test Author", 2014, "Test Description", 99L, Genre.Fantasy);
        book.setId(1L);
        when(bookRepoMock.findOne(1L)).thenReturn(book);
        assertEquals(book, bookService.find(1L));
    }

    @Test
    public void FindWorksProperlyForUnExistingId() {
        when(bookRepoMock.findOne(2L)).thenReturn(null);
        assertNull(bookService.find(2L));
    }

    @Test
    public void FindWorksProperlyForNull() {
        when(bookRepoMock.findOne((Long)null)).thenReturn(null);
        assertNull(bookService.find(null));
    }
}
