package com.library.dao;

public interface IssuedBookDAO {
    void issueBookTransaction(int bookId, int memberId);
    void returnBookTransaction(int bookId, int issueId);
}