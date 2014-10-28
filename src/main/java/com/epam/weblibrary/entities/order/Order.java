package com.epam.weblibrary.entities.order;

import com.epam.weblibrary.entities.book.Book;
import com.epam.weblibrary.entities.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Entity for orders
 * @author Iurii Miedviediev
 * @version 1.0 Build 11.09.2014
 */
@Entity(name = "orders")
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Book> items = new ArrayList();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "creation_date")
    private Date dateOfCreation;

    @Lob @Basic(fetch=FetchType.LAZY)
    private byte[] qrcode;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.WAITING;

    public Order(List<Book> items, Date dateOfCreation, User user) {
        for(Book item : items) {
            this.items.add(item);
        }
        this.dateOfCreation = dateOfCreation;
        this.user = user;
    }
}
