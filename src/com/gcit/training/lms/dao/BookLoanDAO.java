package com.gcit.training.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.gcit.training.lms.entity.BookCopy;
import com.gcit.training.lms.entity.BookLoan;
import com.gcit.training.lms.entity.Book;
import com.gcit.training.lms.entity.Branch;
import com.gcit.training.lms.entity.Borrower;

public class BookLoanDAO extends AbstractDAO{
	
	public BookLoanDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void create(BookLoan bl) throws SQLException {	
		save("insert into tbl_book_copies (bookId,branchId,cardNo,dateOut,dueDate) values (?)", 
				new Object[] { bl.getBook().getBookId(),bl.getBranch().getBranchId(),bl.getBorrower().getCardNo(),bl.getDateOut(),bl.getDueDate()});	
	}
	
	public void update(BookLoan bl) throws SQLException {

		save("update tbl_book_loans set dateOut = ?,dueDate=?,dateIn=? where bookId = ? and branchId= ? and cardNo=?", 
				new Object[]{bl.getDateOut(),bl.getDueDate(),bl.getDateIn(),bl.getBook().getBookId(),bl.getBranch().getBranchId(),bl.getBorrower().getCardNo()});
	}	
	
	public void delete(BookLoan bl) throws SQLException {
		save ("delete from tbl_book_loans where bookId = ? and branchId=? and cardNo=?", 
				new Object[]{bl.getBook().getBookId(),bl.getBranch().getBranchId()});

	}	
	
	
	

	@Override
	protected List<?> processResult(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
