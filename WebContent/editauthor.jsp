<%@ page import="com.gcit.training.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.training.lms.service.ConnectionUtil"%>
<%@ page import="com.gcit.training.lms.entity.Author"%>
<%@ page import="com.gcit.training.lms.dao.AuthorDAO"%>
<%@ page import="java.util.List"%>

<%
	AdministrativeService adminService = new AdministrativeService();
	Integer authorId = Integer.parseInt(request.getParameter("authorId"));
	System.out.println(authorId);
/* 	Author author=new Author();
	author.setAuthorId(authorId); */
	
	
%>
<div class="modal-content">
	<form action="editAuthor" method="post">
	${result}
		<h2>Enter Author details below:</h2>

		Author Name: <input type="text" name="authorName"> <input type="text" name="authorId" value=<%=authorId%>>
		<button type="submit" class="btn btn-sm btn-primary">Edit Author</button>
	</form>
</div>