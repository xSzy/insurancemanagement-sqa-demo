package qlbhxh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import qlbhxh.model.Staff;
import qlbhxh.model.StaffAccount;

/**
 * This class handle login requests
 */
@Repository
public class LoginDAO {
	@Autowired
	private DataSource dataSource;
	
	private Connection conn;
	
	/**
	 * This class check if a login is valid or not
	 * @param username
	 * @param password
	 * @return Staff object of a valid login, or null if invalid
	 */
	public Staff doLogin(String username, String password)
    {
        try
        {
        	//get the connection
        	conn = dataSource.getConnection();
        	
        	//sql command to check how many accounts available
            String sql = "select count(*) from staffaccount where username = ? and password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            
            //get results
            ResultSet rs = ps.executeQuery();
            
            if(rs.next())
            {
                int count = rs.getInt(1);
                //if there is no result, then login is invalid
                if(count == 0)
                {
                    Staff s = new Staff();
                    s.setName("0");
                    conn.close();
                    return s;
                }
            }
            //sql command
            sql = "select * from staff join staffaccount"
                    + " on staff.id = staffaccount.accountId"
                    + " where staffaccount.username = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            
            //execute query
            rs = ps.executeQuery();
            Staff staff = new Staff();
            
            //get result
            if(rs.next())
            {
                staff.setAddress(rs.getString("address"));
                staff.setDateOfBirth(rs.getDate("dob"));
                staff.setID(rs.getInt("staff.id"));
                staff.setName(rs.getString("name"));
                staff.setPhone(rs.getString("phonenumber"));
                staff.setSex(rs.getBoolean("sex"));
                StaffAccount account = new StaffAccount();
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                staff.setAccount(account);
            }
            
            //close the connection and return the object
            conn.close();
            return staff;
        }
        catch(SQLException ex)
        {
            try
            {
                conn.close();
            }
            catch(SQLException ex1)
            {
            }
            return null;
        }
    }
}
