package com.epam.weblibrary.repositories.book;

import com.epam.weblibrary.entities.book.Book;
import com.epam.weblibrary.entities.book.Book_;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Criteria specifications for Books
 * @author Iurii Miedviediev
 * @version 1.0 Build 29.09.2014
 */
public class BookSpecs {

    /**
     * Check book's author to contain given word
     * @param keyword given word
     * @return specification
     */
    public static Specification<Book> containsKeywordInAuthor(final String keyword) {
        return new Specification<Book>() {
            @Override
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                return cb.like(cb.upper(root.get(Book_.author)), "%" + keyword.toUpperCase() + "%");
            }
        };
    }

    /**
     * Check book's title to contain given word
     * @param keyword given word
     * @return specification
     */
    public static Specification<Book> containsKeywordInTitle(final String keyword) {
        return new Specification<Book>() {
            @Override
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                return cb.like(cb.upper(root.get(Book_.title)), "%" + keyword.toUpperCase() + "%");
            }
        };
    }
}


