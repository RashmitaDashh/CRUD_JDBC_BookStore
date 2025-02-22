<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ page import="java.util.List"%>
<%@ page import="com.jdbc.model.Book"%>

<%@ page isELIgnored="false"%>
<html>
<head>
<meta charset="UTF-8">
<title>Books</title>
</head>
<body>
	<h1>Book List</h1>

	<!----------------------------------------------------------------- Add book ------------------------------------------------------->
	<a href="add-book.jsp">Add New Book</a>
	<table border="1">
		<thead>
			<tr>
				<th>ID</th>
				<th>Title</th>
				<th>Author</th>
				<th>Price</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<%
			List<Book> books = (List<Book>) request.getAttribute("books");
			if (books != null) {
				for (Book book : books) {
			%>
			<tr>
				<td><%=book.getId()%></td>
				<td><%=book.getTitle()%></td>
				<td><%=book.getAuthor()%></td>
				<td><%=book.getPrice()%></td>
				<td>
					<!------------------------------------------- Update Book ------------------------------------------------------------->
					<a href="<%= request.getContextPath() %>/books/edit?id=<%= book.getId() %>">Edit</a>
					
 					
 					<!---------------------------------------- Delete Book ---------------------------------------------->
					<form action="<%=request.getContextPath()%>/books/delete"
						method="post" style="display: inline;">
						<input type="hidden" name="action" value="delete"> <input
							type="hidden" name="id" value="<%=book.getId()%>">
						<button type="submit" onclick="return confirm('Are you sure you want to delete this book?')">Delete</button>
					</form>
				</td>
			</tr>
			<%
			}
			}
			%>
		</tbody>
	</table>
</body>
</html>
