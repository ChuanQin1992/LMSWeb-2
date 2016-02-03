<%@ page import="com.gcit.training.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.training.lms.service.ConnectionUtil"%>
<%@ page import="com.gcit.training.lms.entity.Author"%>
<%@ page import="com.gcit.training.lms.entity.Publisher"%>
<%@ page import="com.gcit.training.lms.entity.Genre"%>
<%@ page import="com.gcit.training.lms.entity.Branch"%>
<%@ page import="com.gcit.training.lms.dao.AuthorDAO"%>
<%@ page import="com.gcit.training.lms.dao.GenreDAO"%>
<%@ page import="com.gcit.training.lms.dao.PublisherDAO"%>
<%@ page import="com.gcit.training.lms.dao.BranchDAO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.sql.Connection"%>

<%
	AdministrativeService adminService = new AdministrativeService();
	Integer bookId = Integer.parseInt(request.getParameter("bookId"));
	String title=request.getParameter("title");
/* 	Author author=new Author();
	author.setAuthorId(authorId); */
	

    ConnectionUtil connUtil = new ConnectionUtil();
    Connection connection = connUtil.getConnection();

	BranchDAO bdao=new BranchDAO(connection);
	

	List<Branch> branchs=null;
	
	branchs=bdao.readAll();
	
		
%>
	
%>
<div class="modal-content">
	<form action="addBookCopy" method="post">
	${result}
		<h2>Add Book copies below:</h2>

        <input type="text" name="bookId" value=<%=bookId%>>
		
		Pick a Branch:
		<select name="branchID">
	<%
		for (Branch branch : branchs) {	
	%>
		<option value="<%=branch.getBranchId()%>"><%=branch.getBranchName()%></option>
	<%
	}
	%>
	</select>

		No of Copies: <input type="text" name="noOfCopies">

		<button type="submit" class="btn btn-sm btn-primary">Add Book Copy</button>
	</form>
</div>