package com.library.service;

import com.library.dao.BookDAO;
import com.library.dao.IssuedBookDAO;
import com.library.dao.impl.BookDAOImpl;
import com.library.dao.impl.IssuedBookDAOImpl;
import com.library.model.Book;

public class IssuedBookService {

    private final IssuedBookDAO issuedBookDAO = new IssuedBookDAOImpl();
    private final BookDAO bookDAO = new BookDAOImpl();

    public void issueABook(int bookId, int memberId) {

        Book book = bookDAO.getBookById(bookId);
        if (book == null) {
            System.out.println("Operation Failed: No book found in the system with ID " + bookId);
            return;
        }


        if (book.getAvailableCopies() <= 0) {
            System.out.println("Operation Failed: '" + book.getTitle() + "' is out of stock! All copies are currently checked out.");
            return;
        }


        System.out.println("Validating request... Processing database transaction...");
        issuedBookDAO.issueBookTransaction(bookId, memberId);
    }
}