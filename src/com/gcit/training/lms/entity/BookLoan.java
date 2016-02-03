package com.gcit.training.lms.entity;

import java.util.Date;

import com.gcit.training.lms.entity.Book;
import com.gcit.training.lms.entity.Branch;
import com.gcit.training.lms.entity.Borrower;


public class BookLoan {
	
	private Book book;
	private Branch branch;
	private Borrower borrower;
	
	private Date dateOut;
	private Date dueDate;
	private Date dateIn;
	
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	public Borrower getBorrower() {
		return borrower;
	}
	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}
	public Date getDateOut() {
		return dateOut;
	}
	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Date getDateIn() {
		return dateIn;
	}
	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}
	

}
