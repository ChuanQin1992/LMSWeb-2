package com.gcit.training.lms.entity;

import java.util.ArrayList;
import java.util.List;

public class Branch {
	
	private int branchId;
	private String branchName;
	private String branchAddress;
	private List<Book> books;
	
	
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchAddress() {
		return branchAddress;
	}
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}
	public void addBook(Book b) {
		if(this.books == null)
			this.books = new ArrayList<Book>();
		this.books.add(b);
	}
	
	

}
