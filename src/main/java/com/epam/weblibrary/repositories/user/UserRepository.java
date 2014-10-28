package com.epam.weblibrary.repositories.user;

import com.epam.weblibrary.entities.user.User;
import com.epam.weblibrary.entities.user.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User Repository
 * @author Iurii Miedviediev
 * @version 1.0 Build 10.09.2014
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find User given his/her credentials
     * @param credentials user credentials
     * @return found user or null if not found
     */
    User findUserByCredentials(UserCredentials credentials);

    /**
     * Find user by his/her email (ignoring case)
     * @param email user's email
     * @return found user or null if not found
     */
    User findUserByEmailIgnoreCase(String email);
}
