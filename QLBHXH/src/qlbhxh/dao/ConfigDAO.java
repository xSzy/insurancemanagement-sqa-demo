package qlbhxh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import qlbhxh.model.InsuranceConfig;

/**
 * This class queries configurations from database
 */
@Repository
public class ConfigDAO {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private Connection conn;
	
	/**
	 * This class get all configs in database.
	 * @return all configs
	 */
	public InsuranceConfig getConfig()
	{
		InsuranceConfig config = null;
		try {
			//get database connection
			conn = dataSource.getConnection();
			
			//sql query command
			String sql = "select * from config";
			PreparedStatement ps = conn.prepareCall(sql);
			
			//get the result
			ResultSet rs = ps.executeQuery();
			
			//read config into object
			if(rs.next())
			{
				config = new InsuranceConfig();
				config.setID(rs.getInt("id"));
				config.setInterestRate(rs.getFloat("interestRate"));
				config.setMaximumIncome(rs.getInt("maximumIncome"));
				config.setMinimumIncome(rs.getInt("minimumIncome"));
			}
			
			//close the connection
			conn.close();
			return config;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * This function update configs in database
	 * @param new configs
	 * @return true if successful, false otherwise
	 */
	public boolean updateConfig(InsuranceConfig config)
	{
		//get number of affected row
		int rowAffected = jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				String sql = "update config set interestRate = ?, minimumIncome = ?, maximumIncome = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setFloat(1, config.getInterestRate());
				ps.setInt(2, config.getMinimumIncome());
				ps.setInt(3, config.getMaximumIncome());
				return ps;
			}
		});
		
		return rowAffected > 0 ? true : false;
	}
}
