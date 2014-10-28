package com.epam.weblibrary.entities.book;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

/**
 * Entity for book cover
 * @author Iurii Miedviediev
 * @version 1.0 Build 27.09.2014
 */
@Entity(name = "book_covers")
@Data
@NoArgsConstructor
public class BookCover {

    @Id
    @GeneratedValue
    @Column(name = "cover_id")
    private Long id;

    @Basic
    private byte[] image;

    public BookCover(byte[] image) {
        this.image = image;
    }
}
