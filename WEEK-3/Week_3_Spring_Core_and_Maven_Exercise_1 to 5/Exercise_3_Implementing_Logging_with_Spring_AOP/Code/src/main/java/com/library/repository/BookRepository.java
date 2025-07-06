package com.library.repository;

public class BookRepository {
    public void displayBookData() {
        System.out.println("Fetching book data from repository...");
        try {
            Thread.sleep(1000); // Simulate some delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
