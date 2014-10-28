package com.epam.weblibrary.service.user;

import com.epam.weblibrary.entities.user.User;
import com.epam.weblibrary.entities.user.UserCredentials;
import java.util.List;

/**
 * Interface for user service layer
 * @author Iurii Miedviediev
 * @version 1.0 Build 12.09.2014
 */
public interface UserService {

    /**
     * Find User by id
     * @param id user id
     * @return User with given id or null if doesn't exist
     */
    User find(Long id);

    /**
     * Find User Credentials by given login
     * @param login user login
     * @return user credentials with given login or null if not found
     */
    UserCredentials findUserByLogin(String login);

    /**
     * Register new user in the library
     * @param user
     * @return registered user
     */
    User addUser(User user);

    /**
     * Find all users in the library
     * @return list of all library users
     */
    List<User> findAll();

    /**
     * Delete user by given id
     * @param id user id
     */
    void delete(Long id);

    /**
     * Save edited user
     * @param user
     */
    void save(User user);

    /**
     * Find User info by given credentials
     * @param principal user credentials
     * @return found user or null if doesn't exist
     */
    User findUserByCredentials(UserCredentials principal);

    /**
     * Find User by given email
     * @param email
     * @return User with given email or null if doesn't exist
     */
    User findUserByEmail(String email);
}
