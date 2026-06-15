package com.library.dao.impl;

import com.library.config.DBConnection;
import com.library.dao.IssuedBookDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IssuedBookDAOImpl implements IssuedBookDAO {

    @Override
    public void issueBookTransaction(int bookId, int memberId) {
        String insertIssue = "INSERT INTO issued_books (book_id, member_id) VALUES (?, ?)";
        String updateBook = "UPDATE books SET available_copies = available_copies - 1 WHERE book_id = ?";

        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            try (PreparedStatement stmt1 = conn.prepareStatement(insertIssue);
                 PreparedStatement stmt2 = conn.prepareStatement(updateBook)) {

                stmt1.setInt(1, bookId);
                stmt1.setInt(2, memberId);
                stmt1.executeUpdate();

                stmt2.setInt(1, bookId);
                stmt2.executeUpdate();

                conn.commit();
                System.out.println("Transaction Completed: Book issued safely!");

            } catch (SQLException e) {
                if (conn != null) {
                    System.out.println("Transaction encountered issues. Rolling back changes completely...");
                    conn.rollback(); // 3. Undo operations back to safety state if anything crashed
                }
                throw e;
            }
        } catch (SQLException e) {
            System.err.println("Transaction Execution Error: " + e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }

    @Override
    public void returnBookTransaction(int bookId, int issueId) {

    }
}