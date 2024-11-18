package com.user.model;

import java.util.Date;

/**
 * Represents the data models used in the Library Management System.
 */
public class User {

    public static class User {
        private int id;
        private String username;
        private String password;
        private String role;

        // Constructors
        public User() {}

        public User(int id, String username, String password, String role) {
            this.id = id;
            this.username = username;
            this.password = password;
            this.role = role;
        }

        // Getters and Setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        // Override toString for easy debugging
        @Override
        public String toString() {
            return "User [id=" + id + ", username=" + username + ", role=" + role + "]";
        }
    }

    /**
     * Represents a Book entity with attributes for ID, title, author, and availability.
     */
    public static class Book {
        private int id;
        private String title;
        private String author;
        private boolean available;

        // Constructors
        public Book() {}

        public Book(int id, String title, String author, boolean available) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.available = available;
        }

        // Getters and Setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }

        // Override toString for easy debugging
        @Override
        public String toString() {
            return "Book [id=" + id + ", title=" + title + ", author=" + author + ", available=" + available + "]";
        }
    }

    /**
     * Represents a BorrowHistory entity with attributes for ID, user ID, book ID, borrow date, and return date.
     */
    public static class BorrowHistory {
        private int id;
        private int userId;
        private int bookId;
        private Date borrowDate;
        private Date returnDate;

        // Constructors
        public BorrowHistory() {}

        public BorrowHistory(int id, int userId, int bookId, Date borrowDate, Date returnDate) {
            this.id = id;
            this.userId = userId;
            this.bookId = bookId;
            this.borrowDate = borrowDate;
            this.returnDate = returnDate;
        }

        // Getters and Setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getBookId() {
            return bookId;
        }

        public void setBookId(int bookId) {
            this.bookId = bookId;
        }

        public Date getBorrowDate() {
            return borrowDate;
        }

        public void setBorrowDate(Date borrowDate) {
            this.borrowDate = borrowDate;
        }

        public Date getReturnDate() {
            return returnDate;
        }

        public void setReturnDate(Date returnDate) {
            this.returnDate = returnDate;
        }

        // Override toString for easy debugging
        @Override
        public String toString() {
            return "BorrowHistory [id=" + id + ", userId=" + userId + ", bookId=" + bookId +
                   ", borrowDate=" + borrowDate + ", returnDate=" + returnDate + "]";
        }
    }
}
