package com.epam.weblibrary.repositories.user;

import com.epam.weblibrary.entities.user.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for User Credentials
 * @author Iurii Miedviediev
 * @version 1.0 Build 27.09.2014
 */
public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Long> {

    /**
     * Find user credentials by given login
     * @param login
     * @return credentials with given login or null if such doesn't exist
     */
    UserCredentials findUserByLogin(String login);
}
