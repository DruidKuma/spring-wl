package com.epam.weblibrary.repositories.order;

import com.epam.weblibrary.entities.order.Order;
import com.epam.weblibrary.entities.order.OrderStatus;
import com.epam.weblibrary.entities.user.User;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

/**
 * Tests for Order Repository
 * @author Iurii Miedviediev
 * @version 1.0 Build 15.10.14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/test-context.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:config/test-dataset.xml")
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void FindByUserReturnsCorrectUserOrdersGivenActiveUser() {
        User user = new User();
        user.setId(1L);
        List<Order> orderList = orderRepository.findByUser(user);
        assertThat(orderList.size(), is(1));
        assertThat(orderList.get(0), allOf(
                hasProperty("id", is(1L)),
                hasProperty("status", is(OrderStatus.WAITING))
        ));
    }

    @Test
    public void FindByUserReturnsEmptyListGivenUserWithoutOrders() {
        User user = new User();
        user.setId(2L);
        List<Order> orderList = orderRepository.findByUser(user);
        assertThat(orderList.size(), is(0));
    }

    @Test
    public void FindByUserReturnsEmptyListGivenNull() {
        List<Order> orderList = orderRepository.findByUser(null);
        assertThat(orderList.size(), is(0));
    }
}
