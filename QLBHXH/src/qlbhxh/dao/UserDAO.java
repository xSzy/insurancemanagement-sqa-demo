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

import qlbhxh.model.User;
import qlbhxh.model.UserType;

/**
 * This class queries user information from database
 */
@Repository
public class UserDAO {
	@Autowired
	private DataSource dataSource;
	
	private Connection conn;
	
	/**
	 * This function gets all user and return in a list
	 * @param limit - number of user
	 * @return list of users
	 */
	public List<User> getAllUser(String type)
	{
		List<User> result = new ArrayList<>();
		try {
			//sql command
			String sql = "select * from user "
					+ "left join usertype on user.typeId = usertype.id "
					+ "left join insurance on user.id = insurance.userId "
					+ "where insurance.type = ?";
			
			//get database connection
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, type);
			
			//execute query
			ResultSet rs = ps.executeQuery();
			
			//read results
			while(rs.next())
			{
				//read user information
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
				
				//get user type
				UserType utype = new UserType();
				utype.setName(rs.getString("usertype.name"));
				u.setType(utype);
				
				//add user to return list
				result.add(u);
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
	
	/**
	 * This function get user by their insuranceId
	 * @param insuranceId
	 * @return user with insuranceId
	 */
	public User getUserByInsuranceId(String insuranceId)
	{
		User u = null;
		try {
			String sql = "select * from user left join usertype on user.typeId = usertype.id where user.insuranceId = ?";
			
			//get connection
			conn = dataSource.getConnection();
			
			//prepare sql query
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, insuranceId);
			
			//read the result
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				//read user information
				u = new User();
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
				
				//get user type
				UserType type = new UserType();
				type.setName(rs.getString("usertype.name"));
				u.setType(type);
			}
			//close the connection and return the user
			conn.close();
			return u;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
