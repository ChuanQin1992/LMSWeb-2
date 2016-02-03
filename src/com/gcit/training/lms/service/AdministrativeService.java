package com.gcit.training.lms.service;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.training.lms.dao.AuthorDAO;
import com.gcit.training.lms.dao.BookCopyDAO;
import com.gcit.training.lms.dao.BookDAO;
import com.gcit.training.lms.dao.BookLoanDAO;
import com.gcit.training.lms.entity.Author;
import com.gcit.training.lms.entity.Book;
import com.gcit.training.lms.entity.BookCopy;
import com.gcit.training.lms.entity.BookLoan;
import com.mysql.jdbc.StringUtils;

public class AdministrativeService {
	
	public void addAuthor(Author author) throws SQLException{
		ConnectionUtil connUtil = new ConnectionUtil();
		Connection connection = connUtil.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(connection);
			adao.create(author);
			connection.commit();
		}catch (Exception e){
			e.printStackTrace();
			connection.rollback();
		}finally{
			connection.close();
		}
		
	}
	
	public List<Author> getAllAuthors(int pageNo, int pageSize, String searchString) throws SQLException{
		ConnectionUtil connUtil = new ConnectionUtil();
		Connection connection = connUtil.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(connection);
			if(StringUtils.isNullOrEmpty(searchString)){
				return adao.readAll(pageNo, pageSize);
			}else{
				return adao.readByName(searchString, pageNo);
			}
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}finally{
			connection.close();
		}
	}

	public void deleteAuthor(Integer authorId) throws SQLException {
		Author author = new Author();
		author.setAuthorId(authorId);
		ConnectionUtil connUtil = new ConnectionUtil();
		Connection connection = connUtil.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(connection);
			adao.delete(author);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	}
	
	public void editAuthor(String authorName, Integer authorId) throws SQLException {
		Author author = new Author();
		author.setAuthorId(authorId);
		author.setAuthorName(authorName);
		ConnectionUtil connUtil = new ConnectionUtil();
		Connection connection = connUtil.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(connection);
			adao.update(author);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	}

	public List<Author> searchAuthors(String searchString, Integer pageNo) throws SQLException {
		ConnectionUtil connUtil = new ConnectionUtil();
		Connection connection = connUtil.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(connection);
			return adao.readByName(searchString, pageNo);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}finally{
			connection.close();
		}
	}
	
	public List<Book> getAllBooks(int pageNo, int pageSize, String searchString) throws SQLException{
		ConnectionUtil connUtil = new ConnectionUtil();
		Connection connection = connUtil.getConnection();
		try{
			BookDAO bdao = new BookDAO(connection);
			if(StringUtils.isNullOrEmpty(searchString)){
				return bdao.readAll(pageNo, pageSize);
			}else{
				return bdao.readByName(searchString, pageNo);
			}
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}finally{
			connection.close();
		}
	}
	
	public void addBook(Book book) throws SQLException{
		ConnectionUtil connUtil = new ConnectionUtil();
		Connection connection = connUtil.getConnection();
		try{
			BookDAO bdao = new BookDAO(connection);
			bdao.create(book);
			connection.commit();
		}catch (Exception e){
			e.printStackTrace();
			connection.rollback();
		}finally{
			connection.close();
		}
		
	}
	
	public void editBook(String title, Integer bookId) throws SQLException {
		Book book  = new Book();
		book.setBookId(bookId);
		book.setTitle(title);
		ConnectionUtil connUtil = new ConnectionUtil();
		Connection connection = connUtil.getConnection();
		try{
			BookDAO bdao = new BookDAO(connection);
			bdao.update(book);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	}

	public void addBookCopy(BookCopy bookcopy) throws SQLException {
		ConnectionUtil connUtil = new ConnectionUtil();
		Connection connection = connUtil.getConnection();
		try{
			BookCopyDAO bcdao = new BookCopyDAO(connection);
			bcdao.create(bookcopy);
			connection.commit();
		}catch (Exception e){
			e.printStackTrace();
			connection.rollback();
		}finally{
			connection.close();
		}
		
	}

	public void addBookLoan(BookLoan bookloan) throws SQLException {
		ConnectionUtil connUtil = new ConnectionUtil();
		Connection connection = connUtil.getConnection();
		try{
			BookLoanDAO bldao = new BookLoanDAO(connection);
			bldao.create(bookloan);
			connection.commit();
		}catch (Exception e){
			e.printStackTrace();
			connection.rollback();
		}finally{
			connection.close();
		}
		
	}
	
	

}
