package qlbhxh.services;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import qlbhxh.dao.UserDAO;
import qlbhxh.model.User;

/**
 * This class provide services for configuration operators
 */
@Service
public class UserService {
	@Autowired
	private UserDAO userDAO;

	/**
	 * This function returns number of users
	 * @param limit - number of users
	 * @return list of users
	 */
	public List<User> getAllUser(String type) {
		return userDAO.getAllUser(type);
	}
	
	
}
