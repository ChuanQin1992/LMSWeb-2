package com.gcit.training.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lms.entity.BookCopy;
import com.gcit.training.lms.entity.Book;
import com.gcit.training.lms.entity.Branch;


public class BookCopyDAO extends AbstractDAO{

	public BookCopyDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void create(BookCopy bc) throws SQLException {
		
		save("insert into tbl_book_copies (bookId,branchId,noOfCopies) values (?,?,?)", 
				new Object[] { bc.getBook().getBookId(),bc.getBranch().getBranchId(),bc.getNoOfCopies() });
	}
	
	public void update(BookCopy bc) throws SQLException {

		save("update tbl_book_copies set noOfCopies = ? where bookId = ? and branchId= ?", 
				new Object[]{bc.getNoOfCopies(),bc.getBook().getBookId(),bc.getBranch().getBranchId()});
	}	
	
	public void delete(BookCopy bc) throws SQLException {
		save ("delete from tbl_book_copies where bookId = ? and branchId=?", 
				new Object[]{bc.getBook().getBookId(),bc.getBranch().getBranchId()});

	}
	
	@SuppressWarnings("unchecked")
	public BookCopy readOne(int bookId,int branchId) throws SQLException {
		List<BookCopy> list= (List<BookCopy>) read("select * from tbl_book_copies where bookId = ? and branchId=?",
				new Object[]{bookId,branchId});
		
        if(list!=null&&list.size()>0){
        	return list.get(0);
        }
        else{
        	return null;
        }

	}
	
	@Override
	protected List<?> processResult(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		List<BookCopy> aList=new ArrayList<BookCopy>();
		
		while(rs.next()){
			BookCopy bc=new BookCopy ();
			
			Book b=new Book();
			b.setBookId(rs.getInt("bookId"));
			bc.setBook(b);
			
			
			Branch br=new Branch();
			br.setBranchId(rs.getInt("branchId"));
			bc.setBranch(br);
			
			bc.setNoOfCopies(rs.getInt("noOfCopies"));
				
			aList.add(bc);
			
		}
		return aList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BookCopy> readAll() throws SQLException {
		return (List<BookCopy>) read("select * from tbl_book_copies", null);
	}

	
	@SuppressWarnings("unchecked")
	public List<BookCopy> readByBookId(int bookId) throws SQLException {
		System.out.println("entry bookcopy");
		return (List<BookCopy>) read(
				"select * from tbl_book_copies where bookId= ?",
				new Object[] {bookId});

	}
	
}
