package com.library.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.library.entity.Book;
import com.library.repository.BookRepository;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /* ---------- CREATE ---------- */
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book saved = bookRepository.save(book);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    /* ---------- READ ALL ---------- */
    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /* ---------- READ ONE ---------- */
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /* ---------- UPDATE ---------- */
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id,
                                           @RequestBody Book updated) {
        return bookRepository.findById(id)
            .map(existing -> {
                existing.setTitle(updated.getTitle());
                existing.setAuthor(updated.getAuthor());
                return ResponseEntity.ok(bookRepository.save(existing));
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /* ---------- DELETE ---------- */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        return bookRepository.findById(id)
            .map(b -> {
                bookRepository.delete(b);
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
