package com.library.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.library.service.BookService;

public class LibraryManagementApplication {
    public static void main(String[] args) {
        // Load Spring container
        ApplicationContext context =
            new ClassPathXmlApplicationContext("applicationContext.xml");

        // Fetch the proxied BookService bean
        BookService service = context.getBean(BookService.class);

        // Invoke methods to trigger AOP advice
        service.issueBook();
        service.returnBook();
    }
}
