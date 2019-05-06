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

import qlbhxh.model.Company;
import qlbhxh.model.Staff;
import qlbhxh.model.Transaction;
import qlbhxh.model.User;
import qlbhxh.model.UserType;

/**
 * This class queries transactions from database
 */
@Repository
public class TransactionDAO {
	@Autowired
	private DataSource dataSource;
	
	private Connection conn;
	
	/**
	 * This function gets all transaction from database
	 * @param days - number of day to look back
	 * @return list of transactions
	 */
	public List<Transaction> getAllTransaction(int days)
	{
		List<Transaction> result = new ArrayList<>();
		try {
			//get database connection
			conn = dataSource.getConnection();
			
			//sql query command
			String sql = "select * from transaction"
					+ " left join company on transaction.businessId = company.id"
					+ " left join user on transaction.userId = user.id"
					+ " left join staff on transaction.staffId = staff.id"
					+ " left join usertype on user.typeId = usertype.id"
					+ " where datediff(curdate(), transaction.date) <= ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, days);
			
			//get results
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				//get transaction info
				Transaction t = new Transaction();
				t.setAmount(rs.getDouble("amount"));
				t.setDate(rs.getDate("date"));
				t.setDuration(rs.getInt("duration"));
				t.setID(rs.getInt("transaction.id"));
				
				//get user info
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
				UserType type = new UserType();
				type.setName(rs.getString("usertype.name"));
				u.setType(type);
				t.setUser(u);
				
				//get staff
				Staff staff = new Staff();
				staff.setName(rs.getString("staff.name"));
				t.setStaff(staff);
				
				//get company
				Company com = new Company();
				com.setCompanyName(rs.getString("company.name"));
				t.setCompany(com);
				
				//add this transaction to return list
				result.add(t);
			}
			
			//close the connection and return
			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * This function gets all transaction by one user
	 * @param insuranceId: insurance id of user
	 * @return list of user transactions
	 */
	public List<Transaction> getTransactionByUser(String insuranceId)
	{
		List<Transaction> result = new ArrayList<>();
		try {
			//get connection
			conn = dataSource.getConnection();
			
			//sql query command 
			String sql = "select * from transaction"
					+ " left join company on transaction.businessId = company.id"
					+ " left join user on transaction.userId = user.id"
					+ " left join staff on transaction.staffId = staff.id"
					+ " left join usertype on user.typeId = usertype.id"
					+ " where user.insuranceId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, insuranceId);
			
			//get results
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				//get transaction info
				Transaction t = new Transaction();
				t.setAmount(rs.getDouble("amount"));
				t.setDate(rs.getDate("date"));
				t.setDuration(rs.getInt("duration"));
				t.setID(rs.getInt("transaction.id"));
				
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
				
				//get user type
				UserType type = new UserType();
				type.setName(rs.getString("usertype.name"));
				u.setType(type);
				t.setUser(u);
				
				//get staff
				Staff staff = new Staff();
				staff.setName(rs.getString("staff.name"));
				t.setStaff(staff);
				
				//get company
				Company com = new Company();
				com.setCompanyName(rs.getString("company.name"));
				t.setCompany(com);
				
				//add transaction to result
				result.add(t);
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
