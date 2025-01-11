<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add book</title>
</head>
<body>
<h1>Add New Book</h1>
    
    <form action="<%= request.getContextPath() %>/books/add" method="post">
        <input type="hidden" name="action" value="add">
        
        <label for="title">Title:</label>
        <input type="text" name="title" id="title" required><br><br>
        
        <label for="author">Author:</label>
        <input type="text" name="author" id="author" required><br><br>
        
        <label for="price">Price:</label>
        <input type="text" name="price" id="price" required><br><br>
        
        <button type="submit">Add Book</button>
    </form>
    
    <br>
    <a href="<%= request.getContextPath() %>/books">Back to Book List</a>
</body>
</html>