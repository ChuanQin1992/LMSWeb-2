<%@include file="include.html"%>
<%@ page import="com.gcit.training.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.training.lms.service.ConnectionUtil"%>
<%@ page import="com.gcit.training.lms.entity.Author"%>
<%@ page import="com.gcit.training.lms.entity.Publisher"%>
<%@ page import="com.gcit.training.lms.entity.Genre"%>
<%@ page import="com.gcit.training.lms.dao.AuthorDAO"%>
<%@ page import="com.gcit.training.lms.dao.PublisherDAO"%>
<%@ page import="com.gcit.training.lms.dao.GenreDAO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.sql.Connection"%>


<%
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

<form action="addBook" method="post">
	${result}
	<h2>Enter Book details below:</h2>
	Book Title: 
	<input type="text" name="title">
	<button type="submit" class="btn btn-sm btn-primary">Add Book</button>

    Pick Authors: 

	<select multiple="multiple" name="authorIDs">
	<%
					for (Author author : authors) {
			
	%>
		<option value=<%=author.getAuthorId()%>><%=author.getAuthorName()%></option>
	<%
	}
	%>
	</select>
	Pick Publisher:
	<select name="publisherID">
	<%
		for (Publisher publisher : publishers) {	
	%>
		<option value="<%=publisher.getPublisherId()%>"><%=publisher.getPublisherName()%></option>
	<%
	}
	%>
	</select>
    </select>
	<br /> Pick Genres: 
	<select multiple="multiple" name="genreIDs">
	<%
		for (Genre genre : genres) {
			
	%>
		<option value=<%=genre.getGenreId()%>><%=genre.getGenreName()%></option>
	<%
	}
	%>
	</select>
	

</form>
