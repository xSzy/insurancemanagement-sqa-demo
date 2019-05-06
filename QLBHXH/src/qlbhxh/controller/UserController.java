package qlbhxh.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import qlbhxh.model.User;
import qlbhxh.services.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/userlist", method = RequestMethod.GET)
	public String showUserList(@RequestParam int limit, Model model, RedirectAttributes redirect, HttpSession session)
	{
		List<User> result = userService.getAllUser(limit);
		model.addAttribute("listUser", result);
		return "userlist";
	}
}
