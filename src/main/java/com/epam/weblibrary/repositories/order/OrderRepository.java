package com.epam.weblibrary.repositories.order;

import com.epam.weblibrary.entities.order.Order;
import com.epam.weblibrary.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repository for orders
 * @author Iurii Miedviediev
 * @version 1.0 Build 11.09.2014
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Find all active orders, created by given user
     * @param user
     * @return list of all user's active orders
     */
    List<Order> findByUser(User user);
}
