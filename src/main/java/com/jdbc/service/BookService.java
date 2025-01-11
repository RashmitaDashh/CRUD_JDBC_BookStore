package com.jdbc.service;

import java.sql.SQLException;
import java.util.List;

import com.jdbc.DAO.BookDAO;
import com.jdbc.model.Book;

public class BookService {
	private final BookDAO bookDAO = new BookDAO();

    public List<Book> getAllBooks() throws SQLException {
        return bookDAO.getAllBooks();
    }
    
    public Book getBookById(int id) throws SQLException{
    	return bookDAO.getBookById(id);
    }

    public void addBook(Book book) throws SQLException {
        bookDAO.addBook(book);
    }

    public void updateBook(Book book) throws SQLException {
        bookDAO.updateBook(book);
    }

    public void deleteBook(int id) throws SQLException {
        bookDAO.deleteBook(id);
    }
}
