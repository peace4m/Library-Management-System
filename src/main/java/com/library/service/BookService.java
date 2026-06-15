package com.library.service;

import com.library.dao.BookDAO;
import com.library.dao.impl.BookDAOImpl;
import com.library.model.Book;
import java.util.List;

public class BookService {
    private final BookDAO bookDAO = new BookDAOImpl();

    public void registerNewBook(String title, String author, String isbn, int totalCopies) {

        if (title == null || title.trim().isEmpty() || author == null || author.trim().isEmpty() || isbn == null || isbn.trim().isEmpty()) {
            System.out.println("Validation Error: Book details cannot be left completely blank!");
            return;
        }
        if (totalCopies <= 0) {
            System.out.println("Validation Error: Total stock copies must be at least 1!");
            return;
        }

        Book book = new Book(title.trim(), author.trim(), isbn.trim(), totalCopies);
        bookDAO.addBook(book);
    }

    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    public void searchForBooks(String query) {
        if (query == null || query.trim().isEmpty()) {
            System.out.println("Please enter a valid search parameter.");
            return;
        }
        List<Book> results = bookDAO.searchBooks(query.trim());
        if (results.isEmpty()) {
            System.out.println("No records matched your search criteria.");
        } else {
            System.out.println("\n--- Search Results ---");
            for (Book b : results) {
                System.out.println("ID: " + b.getBookId() + " | Title: " + b.getTitle() + " | Author: " + b.getAuthor() + " | ISBN: " + b.getIsbn() + " | Available Stock: " + b.getAvailableCopies() + "/" + b.getTotalCopies());
            }
        }
    }
}