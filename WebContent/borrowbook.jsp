<%@ page import="com.gcit.training.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.training.lms.service.ConnectionUtil"%>
<%@ page import="com.gcit.training.lms.entity.Author"%>
<%@ page import="com.gcit.training.lms.entity.Branch"%>
<%@ page import="com.gcit.training.lms.entity.Book"%>
<%@ page import="com.gcit.training.lms.entity.BookCopy"%>
<%@ page import="com.gcit.training.lms.entity.Publisher"%>
<%@ page import="com.gcit.training.lms.entity.Genre"%>
<%@ page import="com.gcit.training.lms.dao.AuthorDAO"%>
<%@ page import="com.gcit.training.lms.dao.BookCopyDAO"%>
<%@ page import="com.gcit.training.lms.dao.BookDAO"%>
<%@ page import="com.gcit.training.lms.dao.GenreDAO"%>
<%@ page import="com.gcit.training.lms.dao.PublisherDAO"%>
<%@ page import="com.gcit.training.lms.dao.BranchDAO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.sql.Connection"%>

<%
	AdministrativeService adminService = new AdministrativeService();
	Integer bookId = Integer.parseInt(request.getParameter("bookId"));
/* 	Author author=new Author();
	author.setAuthorId(authorId); */
	

    ConnectionUtil connUtil = new ConnectionUtil();
    Connection connection = connUtil.getConnection();
    BookDAO bdao=new BookDAO(connection);
    BookCopyDAO bcdao=new BookCopyDAO(connection);
    
    Book book=bdao.readOne(bookId);
    

	List<BookCopy> bookcopies=bcdao.readByBookId(bookId);
	
	System.out.println(bookcopies.size());
	System.out.println(bookcopies.get(1).getBranch().getBranchId());

	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date date = new Date();
	System.out.println(dateFormat.format(date)); 

		
%>
	
%>
<div class="modal-content">
	<form action="borrowBook" method="post">
	${result}
		<h2>Enter Book details below:</h2>
		
		Card No:<input type="text" name="cardNo">

		Book Title : <%=book.getTitle()%> <input type="hidden" name="bookId" value=<%=bookId%>>
		
		Branch Available:
	<select name="branchID">
	<%

		for (BookCopy bookcopy : bookcopies) {
		    int branchId;
		    String branchName;
			branchId=bookcopy.getBranch().getBranchId();
			BranchDAO brdao=new BranchDAO(connection);			
            branchName=brdao.readOne(branchId).getBranchName();
            
	%>
		<option value="<%=branchId%>"><%=branchName%></option>
	
	<%
	}
	%>
	</select>
	    Due Date: <input type="date" name="dueDate" >
	    Today's Date: <input type="date" name="dateOut">

		<button type="submit" class="btn btn-sm btn-primary">Borrow Book</button>
	</form>
</div>