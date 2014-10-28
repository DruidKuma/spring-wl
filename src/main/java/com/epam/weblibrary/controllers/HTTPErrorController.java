package com.epam.weblibrary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for all HTTP errors
 * @author Iurii Miedviediev
 * @version 1.0 Build 29.09.14
 */
@Controller
public class HTTPErrorController {

    /**
     * Handler for 404 Not Found
     * @param model
     * @return name of the view
     */
    @RequestMapping(value="/errors/404")
    public String handle404(Model model) {
        model.addAttribute("title", "Error 404: Not Found");
        model.addAttribute("error", "Sorry, there is no such page on this server");
        return "other/error";
    }

    /**
     * Handler for 403 Forbidden
     * @param model
     * @return name of the view
     */
    @RequestMapping(value="/errors/403")
    public String handle403(Model model) {
        model.addAttribute("title", "Error 403: Forbidden");
        model.addAttribute("error", "You have no access to this page");
        return "other/error";
    }

    /**
     * Handler for 400 Bad Request
     * @param model
     * @return name of the view
     */
    @RequestMapping(value="/errors/400")
    public String handle400(Model model) {
        model.addAttribute("title", "Error 400: Bad Request");
        model.addAttribute("error", "Probably, you have sent wrong request to the server");
        return "other/error";
    }

    /**
     * Handler for 502 Bad Gateway
     * @param model
     * @return name of the view
     */
    @RequestMapping(value="/errors/502")
    public String handle502(Model model) {
        model.addAttribute("title", "Error 502: Bad Gateway");
        model.addAttribute("error", "Received an invalid response from the server");
        return "other/error";
    }

    /**
     * Handler for 500 Internal Server Error
     * @param model
     * @return name of the view
     */
    @RequestMapping(value="/errors/500")
    public String handle500(Model model) {
        model.addAttribute("title", "Error 500: Internal Server Error");
        model.addAttribute("error", "Oops!.. Something went wrong");
        return "other/error";
    }

    /**
     * Handler for 503 Service Unavailable
     * @param model
     * @return name of the view
     */
    @RequestMapping(value="/errors/503")
    public String handle503(Model model) {
        model.addAttribute("title", "Error 503: Service Unavailable");
        model.addAttribute("error", "Sorry, server is temporarily unavailable");
        return "other/error";
    }

    /**
     * Handler for 504 Gateway Timeout
     * @param model
     * @return name of the view
     */
    @RequestMapping(value="/errors/504")
    public String handle504(Model model) {
        model.addAttribute("title", "Error 504: Gateway Timeout");
        model.addAttribute("error", "Did not receive a timely response from the server");
        return "other/error";
    }
}
