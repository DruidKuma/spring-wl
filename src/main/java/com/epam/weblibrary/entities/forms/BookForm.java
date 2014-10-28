package com.epam.weblibrary.entities.forms;

import com.epam.weblibrary.entities.book.Book;
import lombok.Data;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import javax.validation.Valid;

/**
 * Form for editing and adding books
 * @author Iurii Miedviediev
 * @version 1.0 Build 30.09.2014
 */
@Data
public class BookForm {

    @Valid
    Book book;

    CommonsMultipartFile cover;
}
