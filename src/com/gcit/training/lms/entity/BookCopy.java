package com.gcit.training.lms.entity;


import com.gcit.training.lms.entity.Book;
import com.gcit.training.lms.entity.Branch;

public class BookCopy {
	
	private Book book;
	private Branch branch;
	private int noOfCopies;
	
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
	public int getNoOfCopies() {
		return noOfCopies;
	}
	public void setNoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}

	
}
