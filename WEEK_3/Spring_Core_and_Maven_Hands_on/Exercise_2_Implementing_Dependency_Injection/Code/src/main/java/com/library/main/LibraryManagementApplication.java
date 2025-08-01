package com.library.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.library.service.BookService;

public class LibraryManagementApplication {
    public static void main(String[] args) {
        // Load Spring application context
        ApplicationContext context =
            new ClassPathXmlApplicationContext("applicationContext.xml");

        // Retrieve the BookService bean
        BookService service = (BookService) context.getBean("bookService");

        // Call service method
        service.showService();
    }
}
