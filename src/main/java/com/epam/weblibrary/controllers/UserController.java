package com.epam.weblibrary.controllers;

import com.epam.weblibrary.entities.forms.RegistrationForm;
import com.epam.weblibrary.entities.user.User;
import com.epam.weblibrary.entities.user.UserCredentials;
import com.epam.weblibrary.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.Locale;

/**
 * Controller for all functions and features related to login and registration
 * @author Iurii Miedviediev
 * @version 1.0 Build 10.09.14
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    //SLF4J logger
    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    /**
     * Get the page for registration
     * @param principal current user
     * @param form form to populate
     * @param result possible errors
     * @return name of the view
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegisterPage(@AuthenticationPrincipal UserCredentials principal,
                                        @ModelAttribute RegistrationForm form, BindingResult result) {

        //if user is authenticated, redirect to the books page
        if(principal != null) {
            return "redirect:/books";
        }
        return "user/register";
    }

    /**
     * Register a new user in the library
     * @param registrationForm populated form
     * @param result possible errors
     * @return name of the view or redirect back to the registration page
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String postRegisterPage(@ModelAttribute @Valid RegistrationForm registrationForm,
                                   BindingResult result, Locale locale) {

        //if user with entered login already exists
        if(userService.findUserByLogin(registrationForm.getCredentials().getLogin()) != null) {
            result.addError(new ObjectError("existLogin", messageSource.getMessage("register.error.exist", null, locale)));
        }

        //if user with entered email already exists
        if(userService.findUserByEmail(registrationForm.getUser().getEmail()) != null) {
            result.addError(new ObjectError("existEmail", messageSource.getMessage("register.error.email", null, locale)));
        }

        //if entered passwords don't match
        if(!registrationForm.getCredentials().getPassword().equals(registrationForm.getPassRepeat())) {
            result.addError(new ObjectError("notMatch", messageSource.getMessage("register.error.pass", null, locale)));
        }

        if (result.hasErrors()) {
            return "user/register";
        } else {
            User user = registrationForm.getUser();
            user.setCredentials(registrationForm.getCredentials());
            userService.addUser(user);
            LOGGER.info("New User with id: " + user.getId() + " was created");
            return "redirect:login?register";
        }
    }

    /**
     * Get the login page
     * Spring Security see this
     * @param error if user entered wrong credentials
     * @param logout if user has just logged out
     * @param register id user has just registered
     * @param user current user
     * @return name of the view
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLoginForm(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            @RequestParam(value = "register", required = false) String register,
            @AuthenticationPrincipal User user, Locale locale) {

        ModelAndView model = new ModelAndView();

        //if user is already authenticated, redirect to books page
        if(user != null) {
            model.setViewName("redirect:/books");
            return model;
        }

        //populate the messages
        if (error != null) {
            model.addObject("error", messageSource.getMessage("login.error", null, locale));
        } else if (logout != null) {
            model.addObject("msg", messageSource.getMessage("login.logout", null, locale));
        } else if (register != null) {
            model.addObject("msg", messageSource.getMessage("login.register", null, locale));
        }

        model.setViewName("user/login");
        return model;
    }
}
