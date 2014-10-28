package com.epam.weblibrary.repositories.user;

import com.epam.weblibrary.entities.user.User;
import com.epam.weblibrary.entities.user.UserCredentials;
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

import static junit.framework.TestCase.assertNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

/**
 * Tests for User Repository
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
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void FindUserByCredentialsReturnsCorrectUserGivenExistingCredentials() {
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setId(1L);
        User user = userRepository.findUserByCredentials(userCredentials);
        assertThat(user, allOf(
                hasProperty("id", is(1L)),
                hasProperty("discount", is(0.0F)),
                hasProperty("email", is("user@mail.ru"))
        ));
    }

    @Test
    public void FindUserByCredentialsReturnsNullUserGivenUnExistingCredentials() {
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setId(5L);
        User user = userRepository.findUserByCredentials(userCredentials);
        assertNull(user);
    }

    @Test
    public void FindUserByCredentialsReturnsNullUserGivenNull() {
        User user = userRepository.findUserByCredentials(null);
        assertNull(user);
    }

    @Test
    public void FindUserByEmailIgnoreCaseReturnsCorrectUserGivenExistingMail() {
        User user = userRepository.findUserByEmailIgnoreCase("user@mail.ru");
        assertThat(user, allOf(
                hasProperty("id", is(1L)),
                hasProperty("discount", is(0.0F)),
                hasProperty("email", is("user@mail.ru"))
        ));
    }

    @Test
    public void FindUserByEmailIgnoreCaseReturnsCorrectUserGivenMailWithDifferentCaseLetters() {
        User user = userRepository.findUserByEmailIgnoreCase("uSer@mAil.RU");
        assertThat(user, allOf(
                hasProperty("id", is(1L)),
                hasProperty("discount", is(0.0F)),
                hasProperty("email", is("user@mail.ru"))
        ));
    }

    @Test
    public void FindUserByEmailIgnoreCaseReturnsNullGivenUnExistingMail() {
        User user = userRepository.findUserByEmailIgnoreCase("user-non-exist@mail.ru");
        assertNull(user);
    }

    @Test
    public void FindUserByEmailIgnoreCaseReturnsNullGivenNull() {
        User user = userRepository.findUserByEmailIgnoreCase(null);
        assertNull(user);
    }
}
