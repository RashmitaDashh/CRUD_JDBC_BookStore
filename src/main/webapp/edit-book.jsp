<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="com.jdbc.model.Book" %>
<html>
<head>
    <title>Edit Book</title>
</head>
<body>
<h1>Edit Book</h1>
    
    <% 
        String bookId = request.getParameter("id");
        Book book = null;
        
        if (bookId != null) {
            // Get the book from request (this would be set by the servlet)
            book = (Book) request.getAttribute("book");
        }
    %>

    <% if (book != null) { %>
        <form action="<%= request.getContextPath() %>/books/update" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value="<%= book.getId() %>">
            
            <label for="title">Title:</label>
            <input type="text" name="title" id="title" value="<%= book.getTitle() %>" required><br><br>
            
            <label for="author">Author:</label>
            <input type="text" name="author" id="author" value="<%= book.getAuthor() %>" required><br><br>
            
            <label for="price">Price:</label>
            <input type="text" name="price" id="price" value="<%= book.getPrice() %>" required><br><br>
            
            <button type="submit">Update Book</button>
        </form>
    <% } else { %>
        <p>Book not found!</p>
    <% } %>

    <br>
    <a href="<%= request.getContextPath() %>/books">Back to Book List</a>
</body>
</html>
