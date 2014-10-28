package com.epam.weblibrary.controllers;

import com.epam.weblibrary.entities.user.UserCredentials;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;

/**
 * Controller for all general and static pages
 * @author Iurii Miedviediev
 * @version 1.0 Build 10.09.14
 */
@Controller
public class HomeController {

    /**
     * Get the index page
     * @param user
     * @return name of the view
     */
    @RequestMapping(value = "/")
    public String getHomePage(@AuthenticationPrincipal UserCredentials user) {

        //if user already authenticated, redirect to the books view
        if(user != null) {
            return "redirect:books";
        }
        return "other/index";
    }

    /**
     * Get the about page
     * @return name of the view
     */
    @RequestMapping(value = "/about")
    public String getAboutPage() {
        return "other/about";
    }

    /**
     * Get the technologies page
     * @return name of the view
     */
    @RequestMapping(value = "/technologies")
    public String getUsedTechnologiesPage() {
        return "other/technologies";
    }

    /**
     * Get the contact page
     * @return name of the view
     */
    @RequestMapping(value = "/contact")
    public String getContactPage() {
        return "other/contact";
    }

    /**
     * Handler for redirecting page to sender after changing the language
     * @param request consist header which refer to the sender
     * @return redirect to sender string
     */
    @RequestMapping(value = "/changelang")
    public String returnToSender(HttpServletRequest request, @RequestParam String lang) {
        if(lang.length() != 2 || Character.isDigit(lang.charAt(0)) || Character.isDigit(lang.charAt(1))) {
            return "redirect:/changelang?lang=en";
        }
        if(request.getHeader("referer") == null) return "redirect:/";
        return "redirect:" + request.getHeader("referer");
    }
}
