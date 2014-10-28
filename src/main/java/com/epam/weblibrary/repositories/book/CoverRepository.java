package com.epam.weblibrary.repositories.book;

import com.epam.weblibrary.entities.book.BookCover;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Book covers
 * @author Iurii Miedviediev
 * @version 1.0 Build 29.09.2014
 */
public interface CoverRepository extends JpaRepository<BookCover, Long> {
}
