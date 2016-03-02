package com.epam.weblibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Druidkuma on 3/3/16.
 */
@SpringBootApplication
@ComponentScan("com.epam.weblibrary")
public class WebLibraryApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebLibraryApplication.class, args);
    }
}
