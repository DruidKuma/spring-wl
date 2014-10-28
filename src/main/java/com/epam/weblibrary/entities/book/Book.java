package com.epam.weblibrary.entities.book;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Entity for books
 * @author Iurii Miedviediev
 * @version 1.0 Build 08.09.2014
 */
@Entity(name = "books")
@Table(indexes = {@Index(name = "book", columnList="book_id", unique = true)})
@Data
@EqualsAndHashCode(exclude={"cover"})
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "book_id")
    private Long id;

    @Length(min = 5, max = 60)
    @Pattern(regexp = "^\\w++(?:[.,_:()'\\s-]{0,2}(?![.\\s-])|\\w++)*$")
    private String title;

    @Length(min = 5, max = 40)
    @Pattern(regexp = "^\\w++(?:[.,_:()'\\s-]{0,2}(?![.\\s-])|\\w++)*$")
    private String author;

    @Range(min = 1, max = 2014)
    @NotNull
    private Integer year;

    @Range(max = 10000)
    private Long price;

    @Lob
    @Length(max = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private BookCover cover;

    public Book(String title, String author, Integer year, String description, Long price, Genre genre) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.description = description;
        this.genre = genre;
        this.price = price;
    }
}
