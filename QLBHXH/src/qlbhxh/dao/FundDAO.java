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

import qlbhxh.model.Fund;
import qlbhxh.model.InsuranceConfig;


/**
 * This class queries fund statics from database
 */
@Repository
public class FundDAO {
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private Connection conn;
	
	/**
	 * This class gets all fund's information from database
	 * @return fund's information
	 */
	public Fund getFundConfig()
	{
		Fund fund = null;
		try {
			//get database connection
			conn = dataSource.getConnection();
			
			//sql querry command
			String sql = "select * from fund";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			//get result
			ResultSet rs = ps.executeQuery();
			fund = new Fund();
			
			//read it to fund object
			while(rs.next())
			{
				String type = rs.getString("name");
				if(type.equals("Quỹ ốm đau và thai sản"))
				{
					fund.setRequiredODTS(rs.getDouble("requiredValueForUser"));
					fund.setCompanyODTS(rs.getDouble("requiredValueForCompany"));
					fund.setWillingODTS(rs.getDouble("willingValue"));
				}
				else if(type.equals("Quỹ tai nạn lao động, bệnh nghề nghiệp"))
				{
					fund.setRequiredTNLD(rs.getDouble("requiredValueForUser"));
					fund.setCompanyTNLD(rs.getDouble("requiredValueForCompany"));
					fund.setWillingTNLD(rs.getDouble("willingValue"));
				}
				else if(type.equals("Quỹ hưu trí và tử tuất"))
				{
					fund.setRequiredHTTT(rs.getDouble("requiredValueForUser"));
					fund.setCompanyHTTT(rs.getDouble("requiredValueForCompany"));
					fund.setWillingHTTT(rs.getDouble("willingValue"));
				}
			}
			
			//close the connection
			conn.close();
			return fund;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return null if there are exceptions
		return null;
	}
	
	/**
	 * This function updates fund configs in database
	 * @param fund - new fund configs
	 * @return true if success, false otherwise
	 */
	public boolean updateFundConfig(Fund fund)
	{
		//get number of row affected
		int affected1 = jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				String sql = "update fund set requiredValueForUser = ?, requiredValueForCompany = ?, willingValue = ? where id = 1";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setDouble(1, fund.getRequiredODTS());
				ps.setDouble(2, fund.getCompanyODTS());
				ps.setDouble(3, fund.getWillingODTS());
				return ps;
			}
		});
		//get number of row affected
		int affected2 = jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				String sql = "update fund set requiredValueForUser = ?, requiredValueForCompany = ?, willingValue = ? where id = 2";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setDouble(1, fund.getRequiredTNLD());
				ps.setDouble(2, fund.getCompanyTNLD());
				ps.setDouble(3, fund.getWillingTNLD());
				return ps;
			}
		});
		//get number of row affected
		int affected3 = jdbcTemplate.update(new PreparedStatementCreator() {
	
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				String sql = "update fund set requiredValueForUser = ?, requiredValueForCompany = ?, willingValue = ? where id = 3";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setDouble(1, fund.getRequiredHTTT());
				ps.setDouble(2, fund.getCompanyHTTT());
				ps.setDouble(3, fund.getWillingHTTT());
				return ps;
			}
		});
		//return true if at least one fund is affected
		return affected1 + affected2 + affected3 > 0 ? true : false;
	}
}
