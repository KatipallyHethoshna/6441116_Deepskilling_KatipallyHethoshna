package com.library.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.library.service.BookService;

public class LibraryManagementApplication {
    public static void main(String[] args) {
        // Load Spring context from XML
        ApplicationContext context = 
            new ClassPathXmlApplicationContext("applicationContext.xml");

        // Get the BookService bean
        BookService service = context.getBean(BookService.class);

        // Call the method
        service.serve();
    }
}
