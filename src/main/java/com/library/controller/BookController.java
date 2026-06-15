package com.library.controller;

import com.library.model.Book;
import com.library.service.BookService;
import com.library.service.IssuedBookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService = new BookService();
    private final IssuedBookService issuedBookService = new IssuedBookService();

    // 1. Endpoint to View All Books: GET http://localhost:8080/api/books
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // 2. Endpoint to Add a New Book: POST http://localhost:8080/api/books
    @PostMapping
    public String addBook(@RequestBody Book book) {
        bookService.registerNewBook(book.getTitle(), book.getAuthor(), book.getIsbn(), book.getTotalCopies());
        return "Success: Book cataloged successfully inside PostgreSQL!";
    }

    // 3. Endpoint to Search Books: GET http://localhost:8080/api/books/search?query=Java
    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam String query) {
        // We call the DAO directly or return the list from a modified service method
        return new com.library.dao.impl.BookDAOImpl().searchBooks(query);
    }

    // 4. Endpoint to Issue a Book: POST http://localhost:8080/api/books/issue?bookId=1&memberId=1
    @PostMapping("/issue")
    public String issueBook(@RequestParam int bookId, @RequestParam int memberId) {
        issuedBookService.issueABook(bookId, memberId);
        return "Transaction Processed: Check console logs or database for status verification.";
    }
}