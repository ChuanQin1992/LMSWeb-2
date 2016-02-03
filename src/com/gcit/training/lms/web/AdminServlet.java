package com.gcit.training.lms.web;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.training.lms.entity.Author;
import com.gcit.training.lms.entity.Book;
import com.gcit.training.lms.entity.BookCopy;
import com.gcit.training.lms.entity.BookLoan;
import com.gcit.training.lms.entity.Borrower;
import com.gcit.training.lms.entity.Branch;
import com.gcit.training.lms.entity.Genre;
import com.gcit.training.lms.entity.Publisher;
import com.gcit.training.lms.service.AdministrativeService;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet({"/addAuthor", "/deleteAuthor", "/pageAuthor", "/searchAuthor","/editAuthor","/addBook","/editBook","/addBookCopy","/borrowBook"})
public class AdminServlet extends HttpServlet {
	AdministrativeService adminService = new AdministrativeService();
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),  request.getRequestURI().length());
		System.out.println(reqUrl);
		switch (reqUrl) {
		case "/deleteAuthor":
			try {
				deleteAuthor(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/pageAuthor":
			try {
				readAllAuthors(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/searchAuthor":
			try {
				readAllAuthors(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

	private void readAllAuthors(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/viewauthor.jsp");
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		String searchString = "";
		if(request.getParameter("searchString")!=null)
			searchString = request.getParameter("searchString");
		
		List<Author> authors = adminService.getAllAuthors(pageNo, 10, searchString);
		request.setAttribute("authors", authors);
		//rd.forward(request, response);
		StringBuilder str = new StringBuilder();
		str.append("<thead><tr><th>#</th><th>Author Name</th><th>Edit</th><th>Delete</th></tr></thead><tbody>");
		for(Author author: authors){
			str.append("<tr><td>"+author.getAuthorId()+"</td><td>"+author.getAuthorName()+"</td><td><button type='button' class='btn btn-sm btn-primary'>Edit Author</button><td><button type='button' class='btn btn-sm btn-danger' onclick='javascript:location.href='deleteAuthor?authorId="+author.getAuthorId()+"';'>Delete Author</button></tr>");
		}
		response.getWriter().append(str.toString());
	}

	private void deleteAuthor(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/viewauthor.jsp");
		Integer authorId = Integer.parseInt(request.getParameter("authorId"));
		System.out.println(authorId);
		
		try {
			adminService.deleteAuthor(authorId);
			request.setAttribute("result", "Author Deleted Sucessfully.");
			rd.forward(request, response);
		} catch (ServletException e) {
			request.setAttribute("result", "Author Delete Failed.");
			e.printStackTrace();
			rd.forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),  request.getRequestURI().length());
		System.out.println(reqUrl);
		switch (reqUrl) {
		case "/addAuthor":
			addAuthor(request, response);
			break;
		case "/searchAuthor":
			try {
				readAllAuthors(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/editAuthor":
			try {
				editAuthor(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/addBook":
			addBook(request, response);
			break;	
		case "/editBook":
			try {
			  editBook(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/addBookCopy":
			addBookCopy(request, response);
			break;
		case "/borrowBook":
			try {
				borrowBook(request, response);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
		
	}

	private void borrowBook(HttpServletRequest request, HttpServletResponse response) throws ParseException, ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/borrowerviewbook.jsp");
		int bookId=Integer.parseInt(request.getParameter("bookId"));
		int branchId=Integer.parseInt(request.getParameter("branchID"));
		int cardNo=Integer.parseInt(request.getParameter("cardNo"));
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dueDate=new Date();
		dueDate=null;
		dueDate=dateFormat.parse(request.getParameter("dueDate").toString());
		
		Date dateOut=dateFormat.parse(request.getParameter("dateOut"));
		
	    Book book=new Book();
	    book.setBookId(bookId);
	    
	    Branch branch=new Branch();
	    branch.setBranchId(branchId);
	    
	    Borrower borrower=new Borrower();
	    borrower.setCardNo(cardNo);
	    
	    BookLoan bookloan=new BookLoan();
	    bookloan.setBook(book);
	    bookloan.setBorrower(borrower);;
	    bookloan.setBranch(branch);
	    bookloan.setDueDate(dueDate);
	    bookloan.setDateOut(dateOut);
	    
	  		try {
	  			adminService.addBookLoan(bookloan);
	  			request.setAttribute("result", "Book Borrow Added Sucessfully!");
	  			rd.forward(request, response);
	  		} catch (SQLException e) {
	  			e.printStackTrace();
	  			rd = getServletContext().getRequestDispatcher("/borrowbook.jsp");
	  			request.setAttribute("result", "Book Borrow Add Failed!");
	  			rd.forward(request, response);
	  		}
	  	    
		
		
		
	}

	private void addBookCopy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/viewbook.jsp");
		int noOfCopies=Integer.parseInt(request.getParameter("noOfCopies"));
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		int branchId=Integer.parseInt(request.getParameter("branchID"));
		
	    Book book=new Book();
	    book.setBookId(bookId);
	    
	    Branch branch=new Branch();
	    branch.setBranchId(branchId);
	    
	    BookCopy bookcopy=new BookCopy();
	    bookcopy.setBook(book);
	    bookcopy.setBranch(branch);
	    bookcopy.setNoOfCopies(noOfCopies);
	    
		try {
			adminService.addBookCopy(bookcopy);
			request.setAttribute("result", "Book Copy Added Sucessfully!");
			rd.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			rd = getServletContext().getRequestDispatcher("/addbookcopy.jsp");
			request.setAttribute("result", "Book Copy Add Failed!");
			rd.forward(request, response);
		}
	    
	    
		
		
	}

	private void editBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/viewbook.jsp");
		String title = request.getParameter("title");
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));

		Book book=new Book();
		book.setTitle(title);
		

		try {
			adminService.editBook(title,bookId);
			request.setAttribute("result", "Author Edit Sucessfully.");
			rd.forward(request, response);
		} catch (ServletException e) {
			request.setAttribute("result", "Author Edit Failed.");
			e.printStackTrace();
			rd.forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/librarian.jsp");
		String title = request.getParameter("title");
		//System.out.println(request.getParameter("publisherID"));
		int pubId=Integer.parseInt(request.getParameter("publisherID"));
		String[] authorIds = request.getParameterValues("authorIDs");
		String[] genreIds = request.getParameterValues("genreIDs");
		
		if (title.length() < 1 || title.length() > 45) {
			rd = getServletContext().getRequestDispatcher("/addauthor.jsp");
			request.setAttribute("result", "Book title cannot be empty or more than 45 chars!");
			rd.forward(request, response);
		}
		Book book = new Book();
		Publisher publisher=new Publisher();
		publisher.setPublisherId(pubId);
		book.setTitle(title);
		book.setPublisher(publisher);
		
		if (authorIds != null) {
		    for(String item: authorIds){
		    	Author author=new Author();
		    	author.setAuthorId(Integer.parseInt(item));
		    	book.addAuthor(author);
		        //System.out.println("Key: " + item);
		    }
		}
	
		if (genreIds != null) {
		    for(String item: genreIds){
		    	Genre genre=new Genre();
		    	genre.setGenreId(Integer.parseInt(item));
		    	book.addGenre(genre);
		        //System.out.println("Key: " + item);
		    }
		}
		
		try {
			adminService.addBook(book);
			request.setAttribute("result", "Book Added Sucessfully!");
			rd.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			rd = getServletContext().getRequestDispatcher("/addbook.jsp");
			request.setAttribute("result", "Book Add Failed!");
			rd.forward(request, response);
		}
	}	

	private void editAuthor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/viewauthor.jsp");
		String authorName = request.getParameter("authorName");
		System.out.println(authorName);
		Integer authorId = Integer.parseInt(request.getParameter("authorId"));

		
		Author author = new Author();
		author.setAuthorName(authorName);
		author.setAuthorId(authorId);
		try {
			adminService.editAuthor(authorName,authorId);
			request.setAttribute("result", "Author Edit Sucessfully.");
			rd.forward(request, response);
		} catch (ServletException e) {
			request.setAttribute("result", "Author Edit Failed.");
			e.printStackTrace();
			rd.forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private void addAuthor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin.jsp");
		String authorName = request.getParameter("authorName");
		if (authorName.length() < 1 || authorName.length() > 40) {
			rd = getServletContext().getRequestDispatcher("/addauthor.jsp");
			request.setAttribute("result", "Author Name cannot be more than 45 chars!");
			rd.forward(request, response);
		}
		Author author = new Author();
		author.setAuthorName(authorName);
		try {
			adminService.addAuthor(author);
			request.setAttribute("result", "Author Added Sucessfully!");
			rd.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			rd = getServletContext().getRequestDispatcher("/addauthor.jsp");
			request.setAttribute("result", "Author Add Failed!");
			rd.forward(request, response);
		}
	}

}
