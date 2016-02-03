package com.gcit.training.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lms.entity.Book;

public class BookDAO extends AbstractDAO{

	
	public BookDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void create(Book b) throws SQLException {
		save("insert into tbl_book (title,pubId) values (?,?)",
				new Object[]{b.getTitle(),b.getPublisher().getPublisherId()});
		List<Book> books=(List<Book>) read("select * from tbl_book where title=? and pubId=?",
				new Object[]{b.getTitle(),b.getPublisher().getPublisherId()});
		int id=books.get(0).getBookId();
		
		for(int i=0;i<b.getAuthors().size();i++)
		  save("insert into tbl_book_authors (bookId,authorId) values(?,?)",
				new Object[]{id,b.getAuthors().get(i).getAuthorId()});
		for(int i=0;i<b.getGenres().size();i++)
			  save("insert into tbl_book_genres (bookId,genreId) values(?,?)",
					new Object[]{id,b.getGenres().get(i).getGenreId()});
		
	}

	public void update(Book b) throws SQLException {
		save("update tbl_book set title = ? where bookId = ?",
				new Object[]{b.getTitle(),b.getBookId()});

	}	

	public void delete(Book b) throws SQLException {
		save("delete from tbl_book where bookId = ?",
				new Object[]{b.getBookId()});
	}

	@SuppressWarnings("unchecked")
	public Book readOne(int bookId) throws SQLException {
		List<Book> list= (List<Book>) read("select * from tbl_book where bookId = ?",new Object[]{bookId});
		
        if(list!=null&&list.size()>0){
        	return list.get(0);
        }
        else{
        	return null;
        }

	}
	
	@SuppressWarnings("unchecked")
	public List<Book> readAll(int pageNo, int pageSize) throws SQLException {
		return (List<Book>) read("select * from tbl_book", null);
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> readByName(String searchString,int pageNo) throws SQLException {
		setPageNo(pageNo);
		String qString = "%" + searchString + "%";
		return (List<Book>) read(
				"select * from tbl_book where title like ?",
				new Object[] { qString });

	}

	@Override
	protected List<?> processResult(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		List<Book> aList=new ArrayList<Book>();
		
		while(rs.next()){
			Book a=new Book ();
			a.setBookId(rs.getInt("bookId"));
			a.setTitle(rs.getString("title"));
			
			aList.add(a);
			
		}
		return aList;
	}
}
