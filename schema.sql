-- Create the database
CREATE DATABASE LibraryManagement;
USE LibraryManagement;

-- Create the 'users' table
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('user', 'admin') DEFAULT 'user' NOT NULL
);

-- Create the 'books' table
CREATE TABLE books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    available BOOLEAN DEFAULT TRUE NOT NULL
);

-- Create the 'borrow_history' table
CREATE TABLE borrow_history (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    book_id INT NOT NULL,
    borrow_date DATE NOT NULL,
    return_date DATE DEFAULT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE
);

-- Insert sample data into 'users' table
INSERT INTO users (username, password, role)
VALUES 
('admin', 'admin123', 'admin'),
('john_doe', 'password123', 'user');

-- Insert sample data into 'books' table
INSERT INTO books (title, author, available)
VALUES
('The Catcher in the Rye', 'J.D. Salinger', TRUE),
('To Kill a Mockingbird', 'Harper Lee', TRUE),
('1984', 'George Orwell', TRUE);

-- Insert sample data into 'borrow_history' table
INSERT INTO borrow_history (user_id, book_id, borrow_date)
VALUES 
(2, 1, '2024-11-01'),
(2, 2, '2024-11-10');
