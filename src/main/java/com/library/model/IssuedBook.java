package com.library.model;

import java.time.LocalDate;

public class IssuedBook {
    private int issueId;
    private int bookId;
    private int memberId;
    private LocalDate issueDate;
    private LocalDate returnDate; // Remains null until they bring the book back
    private double fineAmount;

    // Situation A: Used when a member is checking out a book today
    public IssuedBook(int bookId, int memberId) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.issueDate = LocalDate.now(); // Sets the issue date to today's date automatically
        this.fineAmount = 0.0;
    }

    // Situation B: Used when loading past history/records out of the Database
    public IssuedBook(int issueId, int bookId, int memberId, LocalDate issueDate, LocalDate returnDate, double fineAmount) {
        this.issueId = issueId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.fineAmount = fineAmount;
    }

    // Getters and Setters
    public int getIssueId() { return issueId; }
    public void setIssueId(int issueId) { this.issueId = issueId; }

    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public int getMemberId() { return memberId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }

    public LocalDate getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDate issueDate) { this.issueDate = issueDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    public double getFineAmount() { return fineAmount; }
    public void setFineAmount(double fineAmount) { this.fineAmount = fineAmount; }
}