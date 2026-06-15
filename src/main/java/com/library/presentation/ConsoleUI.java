package com.library.presentation;

import com.library.model.Book;
import com.library.service.BookService;
import com.library.service.IssuedBookService;
import java.util.Scanner;

public class ConsoleUI {
    private final BookService bookService = new BookService();
    private final IssuedBookService issuedBookService = new IssuedBookService();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("\n=================================");
            System.out.println("    LIBRARY MANAGEMENT SYSTEM    ");
            System.out.println("=================================");
            System.out.println("1. Add a Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search for a Book");
            System.out.println("4. Issue a Book (Transaction)");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a valid menu number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author name: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter unique ISBN code: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Enter total number of stock copies: ");
                    int totalCopies = Integer.parseInt(scanner.nextLine());

                    bookService.registerNewBook(title, author, isbn, totalCopies);
                    break;
                case 2:
                    System.out.println("\n--- All Cataloged Books ---");
                    for (Book b : bookService.getAllBooks()) {
                        System.out.println("ID: " + b.getBookId() + " | " + b.getTitle() + " by " + b.getAuthor() + " [ISBN: " + b.getIsbn() + " | Stock Available: " + b.getAvailableCopies() + "/" + b.getTotalCopies() + "]");
                    }
                    break;
                case 3:
                    System.out.print("Enter book title or author name to search: ");
                    String query = scanner.nextLine();
                    bookService.searchForBooks(query);
                    break;
                case 4: // New operational block!
                    System.out.print("Enter Book ID to issue: ");
                    int bookId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Member ID receiving the book: ");
                    int memberId = Integer.parseInt(scanner.nextLine());
                    issuedBookService.issueABook(bookId, memberId);
                    break;
                case 5:
                    System.out.println("Exiting System... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid selection option. Try again.");
            }
        }
    }
}