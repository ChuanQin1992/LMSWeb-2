package com.gcit.training.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDAO {
	
	private int pageNo = -1;
	private int pageSize = 10;
	
	public Connection connection = null;
	
	/**
	 * @return the pageNo
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * @param pageNo the pageNo to set
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public AbstractDAO(Connection conn){
		this.connection = conn;
	}
	
	public Connection getConnection(){
		return connection;
	}


	public void save(String query, Object[] values) throws SQLException {
		
		Connection conn = getConnection();
		
		PreparedStatement stmt = conn.prepareStatement(query);
		

		int count = 1;
		for(Object obj : values) {
			stmt.setObject(count, obj);
			count++;
		}
		
		System.out.println(stmt);
		
		stmt.executeUpdate();
	}

	protected List<?> read(String sql, Object[] values) throws SQLException {
		Connection conn = getConnection();
		int pageNo = getPageNo();
		if(pageNo >-1){
			int start = (pageNo-1)*10;
			if(start > 0){
				sql+= " LIMIT "+start+", "+getPageSize();
			}else{
				sql+= " LIMIT "+getPageSize();
			}
		}

		PreparedStatement stmt = conn.prepareStatement(sql);		

		if(values != null) {
			int count = 1;
			for(Object obj : values) {
				stmt.setObject(count, obj);
				count++;
			}
		}
		
		ResultSet rs = stmt.executeQuery();
		return processResult(rs);
	}
	
	protected abstract List<?> processResult(ResultSet rs) throws SQLException;
}
