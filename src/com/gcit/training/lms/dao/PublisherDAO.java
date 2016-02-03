package com.gcit.training.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lms.entity.Publisher;

public class PublisherDAO extends AbstractDAO {

	public PublisherDAO(Connection conn) {
		super(conn);
	}

	public void create(Publisher p) throws SQLException {
		save("insert into tbl_publisher (publisherName, publisherAddress) values (?)",
				new Object[] { p.getPublisherName(), p.getPublisherAddress() });
	}

	public void update(Publisher p) throws SQLException {
		save("update tbl_publisher set publisherName = ?, publisherAddress = ? where publisherId = ?",
				new Object[] { p.getPublisherName(), p.getPublisherAddress(),
						p.getPublisherId() });
	}

	public void delete(Publisher p) throws SQLException {
		save("delete from tbl_publisher where publisherId = ?",
				new Object[] { p.getPublisherId() });
	}

	public Publisher readOne(int publisherId) throws SQLException {
		List<Publisher> pubList = (List<Publisher>) read(
				"select * from tbl_publisher where publisherId = ?",
				new Object[] { publisherId });
		
		if(pubList != null && pubList.size() > 0) {
			return pubList.get(0);
		} else {
			return null;
		}
	}

	public List<Publisher> readAll() throws SQLException {
		return (List<Publisher>) read("select * from tbl_publisher", null);
	}

	public List<Publisher> readByName(String searchString) throws SQLException {
		String qString = "%" + searchString + "%";
		return (List<Publisher>) read(
				"select * from tbl_publisher where publisherName like ?",
				new Object[] { qString });
	}

	@Override
	protected List<Publisher> processResult(ResultSet rs) throws SQLException {
		List<Publisher> aList = new ArrayList<Publisher>();
		while (rs.next()) {
			Publisher a = new Publisher();
			a.setPublisherId(rs.getInt("publisherId"));
			a.setPublisherName(rs.getString("publisherName"));

			aList.add(a);
		}

		return aList;
	}

}
