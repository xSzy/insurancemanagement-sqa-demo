package qlbhxh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * This class queries common statics from database
 */
@Repository
public class CommonDAO {
	
	@Autowired
	private DataSource dataSource;
	
	private Connection conn;
	
	/**
	 * This function get the number of users who are using insurances.
	 * @return the number of users who are using insurances.
	 */
	public int getNumberOfUser()
	{
		try {
			//get database connection
			conn = dataSource.getConnection();
			
			//prepare sql query command
			String sql = "select count(*) from insurance";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			//execute query
			ResultSet rs = ps.executeQuery();
			
			//read the value
			int count = 0;
			if(rs.next())
				count = rs.getInt(1);
			
			//close connection
			conn.close();
			
			return count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * This function get the number of users who have not paid their insurances.
	 * @return the number of users who have not paid their insurances
	 */
	public int getNumberOfDebtUser()
	{
		try {
			//get database connection
			conn = dataSource.getConnection();
			
			//prepare sql query command
			String sql = "select count(*) from insurance where datediff(endDate, sysdate()) < 0";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			//get query result
			ResultSet rs = ps.executeQuery();
			
			//read the value
			int count = 0;
			if(rs.next())
				count = rs.getInt(1);
			
			//close the connection
			conn.close();
			return count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
}
