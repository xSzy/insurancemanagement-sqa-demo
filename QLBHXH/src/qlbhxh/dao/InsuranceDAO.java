package qlbhxh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import qlbhxh.model.Insurance;
import qlbhxh.model.User;
import qlbhxh.model.UserType;

/**
 * This class queries insurance information from database
 */
@Repository
public class InsuranceDAO {
	@Autowired
	private DataSource dataSource;
	
	private Connection conn;
	
	/**
	 * This class get all debt user and parse to a list
	 * @param days : the minimum days in debt in order to be in this list
	 * @return list of all debt insurance
	 */
	public List<Insurance> getDebtList(int days)
	{
		if(days <= 0)
			days = 1;
		List<Insurance> result = new ArrayList<>();
		try {
			//get connection
			conn = dataSource.getConnection();
			
			//sql command
			String sql = "select *, datediff(curdate(), insurance.endDate) from insurance "
					+ "left join user on insurance.userId = user.id "
					+ "left join usertype on user.typeId = usertype.id "
					+ "where datediff(curdate(), insurance.endDate) >= ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, days);
			
			//get result
			ResultSet rs = ps.executeQuery();
			
			//add results to the list
			while(rs.next())
			{
				//get insurance information
				Insurance i = new Insurance();
				i.setDateEnd(rs.getDate("endDate"));
				i.setDateStart(rs.getDate("startDate"));
				i.setID(rs.getInt("insurance.id"));
				i.setType(rs.getString("insurance.type"));

				//get user information
				User u = new User();
				u.setAddress(rs.getString("address"));
				u.setDob(rs.getDate("dob"));
				u.setID(rs.getInt("user.id"));
				u.setIdentityNumber(rs.getString("identitynumber"));
				u.setIncome(rs.getFloat("income"));
				u.setName(rs.getString("user.name"));
				u.setPhone(rs.getString("phonenumber"));
				u.setSex(rs.getBoolean("user.sex"));
				u.setTaxCode(rs.getString("user.taxcode"));
				u.setInsuranceId(rs.getString("user.insuranceId"));
				
				//get usertype
				UserType type = new UserType();
				type.setName(rs.getString("usertype.name"));
				u.setType(type);
				
				i.setUser(u);
				i.setDaysInDebt(rs.getInt("datediff(curdate(), insurance.endDate)"));
				
				//add to list
				result.add(i);
			}
			
			//close the connection and return the list
			conn.close();
			return result;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
