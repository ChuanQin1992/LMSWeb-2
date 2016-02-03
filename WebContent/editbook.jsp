<%@ page import="com.gcit.training.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.training.lms.service.ConnectionUtil"%>
<%@ page import="com.gcit.training.lms.entity.Author"%>
<%@ page import="com.gcit.training.lms.entity.Publisher"%>
<%@ page import="com.gcit.training.lms.entity.Genre"%>
<%@ page import="com.gcit.training.lms.dao.AuthorDAO"%>
<%@ page import="com.gcit.training.lms.dao.GenreDAO"%>
<%@ page import="com.gcit.training.lms.dao.PublisherDAO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.sql.Connection"%>

<%
	AdministrativeService adminService = new AdministrativeService();
	Integer bookId = Integer.parseInt(request.getParameter("bookId"));
/* 	Author author=new Author();
	author.setAuthorId(authorId); */
	

    ConnectionUtil connUtil = new ConnectionUtil();
    Connection connection = connUtil.getConnection();
	AuthorDAO adao=new AuthorDAO(connection);
	PublisherDAO pdao=new PublisherDAO(connection);
	GenreDAO gdao=new GenreDAO(connection);
	List<Author> authors = null;
	List<Publisher> publishers=null;
	List<Genre> genres=null;
	
	authors = adao.readAll();
	publishers=pdao.readAll();
	genres=gdao.readAll();
	
		
%>
	
%>
<div class="modal-content">
	<form action="editBook" method="post">
	${result}
		<h2>Enter Book details below:</h2>

		Book Title : <input type="text" name="title"> <input type="text" name="bookId" value=<%=bookId%>>

		<button type="submit" class="btn btn-sm btn-primary">Edit Author</button>
	</form>
</div>