package com.epam.weblibrary.service.user;

import com.epam.weblibrary.entities.user.UserCredentials;
import com.epam.weblibrary.repositories.user.UserCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Spring Security service for authorization
 * @author Iurii Miedviediev
 * @version 1.0 Build 12.09.2014
 */
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    @Override
    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException {

        UserCredentials principal = userCredentialsRepository.findUserByLogin(login);

        if(principal == null) {
            throw new UsernameNotFoundException("User with login " + login + " not found.");
        }

        return principal;
    }
}
