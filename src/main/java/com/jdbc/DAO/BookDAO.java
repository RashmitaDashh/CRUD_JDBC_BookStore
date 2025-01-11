package com.jdbc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.model.Book;
import com.jdbc.util.DatabaseUtil;

public class BookDAO {
	public List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        Connection conn = DatabaseUtil.getConnection();
        String sql = "SELECT * FROM books";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setPrice(rs.getDouble("price"));
            books.add(book);
        }

        rs.close();
        stmt.close();
        conn.close();
        return books;
    }
	
	
	public Book getBookById(int id) throws SQLException {
	    Book book = null;
	    String sql = "SELECT * FROM books WHERE id = ?";
	    Connection conn = DatabaseUtil.getConnection();
	    PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, id);
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                book = new Book();
	                book.setId(rs.getInt("id"));
	                book.setTitle(rs.getString("title"));
	                book.setAuthor(rs.getString("author"));
	                book.setPrice(rs.getDouble("price"));
	            }
	        }
	    return book;
	}

    public void addBook(Book book) throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "INSERT INTO books (title, author, price) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, book.getTitle());
        pstmt.setString(2, book.getAuthor());
        pstmt.setDouble(3, book.getPrice());
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }

    public void updateBook(Book book) throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "UPDATE books SET title = ?, author = ?, price = ? WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, book.getTitle());
        pstmt.setString(2, book.getAuthor());
        pstmt.setDouble(3, book.getPrice());
        pstmt.setInt(4, book.getId());
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }

    public void deleteBook(int id) throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "DELETE FROM books WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }
}
