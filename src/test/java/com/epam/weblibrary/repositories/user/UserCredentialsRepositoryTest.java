package com.epam.weblibrary.repositories.user;

import com.epam.weblibrary.entities.user.Roles;
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
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;

/**
 * Tests for User Cresentials Repository
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
public class UserCredentialsRepositoryTest {

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    @Test
    public void FindUserByLoginCorrectlyFindsExistingUser() {
        UserCredentials userCredentials = userCredentialsRepository.findUserByLogin("user");
        assertThat(userCredentials, allOf(
                hasProperty("id", is(1L)),
                hasProperty("login", is("user")),
                hasProperty("password", is("123123")),
                hasProperty("role", is(Roles.ROLE_USER))
        ));
    }

    @Test
    public void FindUserByLoginReturnsNullGivenUnExistingUser() {
        assertNull(userCredentialsRepository.findUserByLogin("blah-blah"));
    }

    @Test
    public void FindUserByLoginReturnsNullGivenNull() {
        assertNull(userCredentialsRepository.findUserByLogin(null));
    }
}
