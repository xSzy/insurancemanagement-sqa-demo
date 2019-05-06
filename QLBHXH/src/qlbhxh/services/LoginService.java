package qlbhxh.services;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import qlbhxh.dao.LoginDAO;
import qlbhxh.model.Staff;
import qlbhxh.model.StaffAccount;

/**
 * This class provide services for login operators
 */
@Service
public class LoginService {
	
	@Autowired
	LoginDAO logindao;
	
	/**
	 * This class validate login attempt
	 * @param username
	 * @param password
	 * @return Staff object if login is valid, null otherwise
	 */
	public Staff login(String username, String password)
	{
		return logindao.doLogin(username, password);
	}
}
