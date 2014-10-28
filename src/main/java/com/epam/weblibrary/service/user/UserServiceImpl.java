package com.epam.weblibrary.service.user;

import com.epam.weblibrary.entities.user.Roles;
import com.epam.weblibrary.entities.user.User;
import com.epam.weblibrary.entities.user.UserCredentials;
import com.epam.weblibrary.repositories.user.UserCredentialsRepository;
import com.epam.weblibrary.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly=true)
public class UserServiceImpl implements UserService {
     
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User find(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public UserCredentials findUserByLogin(String login) {
        return userCredentialsRepository.findUserByLogin(login);
    }

    @Override
    public User addUser(User user) {

        //set default role
        user.getCredentials().setRole(Roles.ROLE_USER);

        //hash user's password
        String password = user.getCredentials().getPassword();
        String hashedPassword = passwordEncoder.encode(password);
        user.getCredentials().setPassword(hashedPassword);

        return userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
        userRepository.flush();
    }

    @Override
    public void save(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public User findUserByCredentials(UserCredentials principal) {
        return userRepository.findUserByCredentials(principal);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmailIgnoreCase(email);
    }
}