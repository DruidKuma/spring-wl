package com.epam.weblibrary.service.book;

import com.epam.weblibrary.entities.book.Book;
import com.epam.weblibrary.entities.book.Genre;
import com.epam.weblibrary.entities.user.User;
import com.epam.weblibrary.repositories.book.BookRepository;
import com.epam.weblibrary.repositories.book.CoverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import java.util.List;
import java.util.Locale;
import static com.epam.weblibrary.repositories.book.BookSpecs.containsKeywordInAuthor;
import static com.epam.weblibrary.repositories.book.BookSpecs.containsKeywordInTitle;

/**
 * Book service layer
 * @author Iurii Miedviediev
 * @version 1.0 Build 11.09.2014
 */
@Service
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {

    //max books on one page
    private static final int PER_PAGE = 9;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CoverRepository coverRepository;

    @Override
    public void setRepo(BookRepository repo) {
        this.bookRepository = repo;
    }

    @Override
    public Book find(Long id) {
        return bookRepository.findOne(id);
    }

    @Override
    public Book save(Book book) {
        //if book has no cover, set default cover
        if(book.getCover() == null) {
            book.setCover(coverRepository.findOne(1L));
        }
        return bookRepository.saveAndFlush(book);
    }

    @Override
    public void delete(Long id) {
        bookRepository.delete(id);
        bookRepository.flush();
    }

    @Override
    public Page<Book> findAll(Integer pageNumber) {
        PageRequest request = new PageRequest(pageNumber - 1, PER_PAGE, Sort.Direction.DESC, "title");
        return bookRepository.findAvailable(request);
    }

    @Override
    public Integer countOrderedBooks(User user) {
        return bookRepository.findOrderedBooks(user).size();
    }

    @Override
    public List<Genre> findAllGenres() {
        return bookRepository.findAllGenres();
    }

    @Override
    public Page<Book> findAllByGenre(Genre genre, Integer page) {
        PageRequest request = new PageRequest(page - 1, PER_PAGE, Sort.Direction.DESC, "title");
        return bookRepository.findByGenre(genre, request);
    }

    @Override
    public Page<Book> findBooksByCriteria(String criteria, String search, Integer page) {
        PageRequest request = new PageRequest(page - 1, PER_PAGE, Sort.Direction.ASC, criteria);
        if("author".equals(criteria)) {
            return bookRepository.findAll(containsKeywordInAuthor(search), request);
        } else {
            return bookRepository.findAll(containsKeywordInTitle(search), request);
        }
    }

    @Override
    public boolean checkAvailability(Book book) {
        return bookRepository.findInOrders(book.getId()) != null;
    }

    @Override
    public void checkCover(CommonsMultipartFile cover, BindingResult errors, Locale locale, MessageSource messageSource) {

        //if file is not empty
        if(!cover.isEmpty()) {

            //check the file to have only JPG or PNG extension
            if(!MediaType.IMAGE_JPEG.toString().equals(cover.getContentType()) &&
                    !MediaType.IMAGE_PNG.toString().equals(cover.getContentType())) {
                errors.addError(new ObjectError("coverFormat", messageSource.getMessage("book.cover.type", null, locale)));
            }

            //check the file not to exceed 100kb
            if(cover.getSize() > 100000) {
                errors.addError(new ObjectError("coverSize", messageSource.getMessage("book.cover.size", null, locale)));
            }
        }
    }
}
