package com.jdbc.controller;

import com.jdbc.model.Book;
import com.jdbc.service.BookService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/books")
public class BookController extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String path = req.getPathInfo();
    	System.out.println(path);
    	try {
            if (path == null || "/".equals(path)) {
                // Default behavior: List all books
                List<Book> books = bookService.getAllBooks();
                req.setAttribute("books", books);
                req.getRequestDispatcher("/books.jsp").forward(req, resp);
            } else if ("/edit".equals(path)) {
                // Edit book behavior
                String bookId = req.getParameter("id");
                if (bookId != null) {
                    Book book = bookService.getBookById(Integer.parseInt(bookId));
                    if (book != null) {
                        req.setAttribute("book", book);
                        req.getRequestDispatcher("/edit-book.jsp").forward(req, resp);
                    } else {
                        req.setAttribute("errorMessage", "Book not found!");
                        req.getRequestDispatcher("/error.jsp").forward(req, resp);
                    }
                } else {
                    req.setAttribute("errorMessage", "Invalid book ID!");
                    req.getRequestDispatcher("/error.jsp").forward(req, resp);
                }
            } else {
                // Handle unsupported paths
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found");
            }
        } catch (SQLException e) {
            throw new ServletException("Error processing request", e);
        }
    }
    	
		/*
		 * try { List<Book> books = bookService.getAllBooks(); req.setAttribute("books",
		 * books); req.getRequestDispatcher("/books.jsp").forward(req, resp); } catch
		 * (SQLException e) { throw new ServletException(e); }
		 */

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String path = req.getPathInfo(); // Retrieves the part of the URL after /books/
    	System.out.println(path);

        try {
            if ("/add".equals(path)) {
                Book book = new Book();
                book.setTitle(req.getParameter("title"));
                book.setAuthor(req.getParameter("author"));
                book.setPrice(Double.parseDouble(req.getParameter("price")));
                bookService.addBook(book);
            } else if ("/edit".equals(path)) {
                Book book = new Book();
                String bookId = req.getParameter("id");
                
                if (bookId != null) {
                	book = bookService.getBookById(Integer.parseInt(bookId));
                    if (book != null) {
                        req.setAttribute("book", book);
                        req.getRequestDispatcher("/edit-book.jsp").forward(req, resp);
                    } else {
                        req.setAttribute("errorMessage", "Book not found!");
                        req.getRequestDispatcher("/error.jsp").forward(req, resp);
                    }
                } else {
                    req.setAttribute("errorMessage", "Invalid book ID!");
                    req.getRequestDispatcher("/error.jsp").forward(req, resp);
                }
                
            } else if ("/update".equals(path)) {
                String bookId = req.getParameter("id");
                if (bookId != null) {
                    Book book = new Book(); 
                    
                    book.setId(Integer.parseInt(bookId));
                    book.setTitle(req.getParameter("title"));
                    book.setAuthor(req.getParameter("author"));
                    book.setPrice(Double.parseDouble(req.getParameter("price")));
                    bookService.updateBook(book);
                }  
             } else if ("/delete".equals(path)) {
              
                	int id = Integer.parseInt(req.getParameter("id"));
                	bookService.deleteBook(id);
             }
            resp.sendRedirect(req.getContextPath() + "/books");
        } catch (Exception e) {
        	req.setAttribute("errorMessage", "Error retrieving books: " + e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}