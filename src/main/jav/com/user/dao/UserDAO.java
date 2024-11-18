package com.library.dao;

import com.library.model.Model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for handling operations related to users, books, and borrow history.
 */
public class LibraryDAO {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/Library-system";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "password";

    /**
     * Establishes a connection to the database.
     *
     * @return Connection object
     * @throws SQLException if there is an error establishing the connection
     */
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

    // ======= User DAO Methods =======

    public void addUser(User user) throws SQLException {
        String query = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        try (Connection connection = connect(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.executeUpdate();
        }
    }

    public User getUserById(int id) throws SQLException {
        String query = "SELECT * FROM users WHERE id = ?";
        try (Connection connection = connect(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new User(resultSet.getInt("id"), resultSet.getString("username"),
                                resultSet.getString("password"), resultSet.getString("role"));
            }
        }
        return null;
    }

    public List<User> getAllUsers() throws SQLException {
        String query = "SELECT * FROM users";
        List<User> users = new ArrayList<>();

        try (Connection connection = connect(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                users.add(new User(resultSet.getInt("id"), resultSet.getString("username"),
                                   resultSet.getString("password"), resultSet.getString("role")));
            }
        }
        return users;
    }

    public void updateUser(User user) throws SQLException {
        String query = "UPDATE users SET username = ?, password = ?, role = ? WHERE id = ?";
        try (Connection connection = connect(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.setInt(4, user.getId());
            preparedStatement.executeUpdate();
        }
    }

    public void deleteUser(int id) throws SQLException {
        String query = "DELETE FROM users WHERE id = ?";
        try (Connection connection = connect(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    // ======= Book DAO Methods =======

    public void addBook(Book book) throws SQLException {
        String query = "INSERT INTO books (title, author, available) VALUES (?, ?, ?)";
        try (Connection connection = connect(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setBoolean(3, book.isAvailable());
            preparedStatement.executeUpdate();
        }
    }

    public Book getBookById(int id) throws SQLException {
        String query = "SELECT * FROM books WHERE id = ?";
        try (Connection connection = connect(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Book(resultSet.getInt("id"), resultSet.getString("title"),
                                resultSet.getString("author"), resultSet.getBoolean("available"));
            }
        }
        return null;
    }

    public List<Book> getAllBooks() throws SQLException {
        String query = "SELECT * FROM books";
        List<Book> books = new ArrayList<>();

        try (Connection connection = connect(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                books.add(new Book(resultSet.getInt("id"), resultSet.getString("title"),
                                   resultSet.getString("author"), resultSet.getBoolean("available")));
            }
        }
        return books;
    }

    public void updateBook(Book book) throws SQLException {
        String query = "UPDATE books SET title = ?, author = ?, available = ? WHERE id = ?";
        try (Connection connection = connect(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setBoolean(3, book.isAvailable());
            preparedStatement.setInt(4, book.getId());
            preparedStatement.executeUpdate();
        }
    }

    public void deleteBook(int id) throws SQLException {
        String query = "DELETE FROM books WHERE id = ?";
        try (Connection connection = connect(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    // ======= Borrow History DAO Methods =======

    public void addBorrowHistory(BorrowHistory history) throws SQLException {
        String query = "INSERT INTO borrow_history (user_id, book_id, borrow_date, return_date) VALUES (?, ?, ?, ?)";
        try (Connection connection = connect(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, history.getUserId());
            preparedStatement.setInt(2, history.getBookId());
            preparedStatement.setDate(3, new java.sql.Date(history.getBorrowDate().getTime()));
            preparedStatement.setDate(4, history.getReturnDate() != null
                    ? new java.sql.Date(history.getReturnDate().getTime())
                    : null);
            preparedStatement.executeUpdate();
        }
    }

    public List<BorrowHistory> getBorrowHistoryByUserId(int userId) throws SQLException {
        String query = "SELECT * FROM borrow_history WHERE user_id = ?";
        List<BorrowHistory> historyList = new ArrayList<>();

        try (Connection connection = connect(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                historyList.add(new BorrowHistory(resultSet.getInt("id"), resultSet.getInt("user_id"),
                                                  resultSet.getInt("book_id"), resultSet.getDate("borrow_date"),
                                                  resultSet.getDate("return_date")));
            }
        }
        return historyList;
    }

    public void updateReturnDate(int id, Date returnDate) throws SQLException {
        String query = "UPDATE borrow_history SET return_date = ? WHERE id = ?";
        try (Connection connection = connect(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDate(1, new java.sql.Date(returnDate.getTime()));
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        }
    }
}
