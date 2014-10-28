package com.epam.weblibrary.service.order;

import com.epam.weblibrary.entities.book.Book;
import com.epam.weblibrary.entities.order.Order;
import com.epam.weblibrary.entities.user.User;

import java.util.List;

/**
 * Interface for order service layer
 * @author Iurii Miedviediev
 * @version 1.0 Build 17.09.2014
 */
public interface OrderService {

    /**
     * Find all orders in the library
     * @return list of all orders
     */
    List<Order> findAll();

    /**
     * Find all orders created by the given user
     * @param user
     * @return list of all orders created by the user
     */
    List<Order> findByUser(User user);

    /**
     * Create new order
     * @param cart shopping cart with order items
     * @param user user who ordered the books
     * @return created order
     */
    Order createOrder(List<Book> cart, User user);

    /**
     * Find order by id
     * @param id order id
     * @return found order or null if doesn't exist
     */
    Order find(Long id);

    /**
     * Generate PDF Document for the order
     * @param id order id
     * @return PDF document in bytes
     */
    byte[] createQrPdfDocument(Long id);

    /**
     * Save the order
     * @param order
     */
    void save(Order order);

    /**
     * Charge money from the given card (token)
     * @param token user's card data
     * @param totalPrice money to charge
     * @return true, if transaction is successful, false otherwise
     */
    boolean chargeMoneyFromCard(String token, Integer totalPrice);

    /**
     * Delete the order from the DB
     * @param id order id
     */
    void delete(Long id);
}
