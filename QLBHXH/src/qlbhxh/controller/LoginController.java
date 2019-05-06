package qlbhxh.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import qlbhxh.model.Staff;
import qlbhxh.model.StaffAccount;
import qlbhxh.services.LoginService;

@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public String validLogin(@ModelAttribute StaffAccount account, Model model, RedirectAttributes redirect, HttpSession session)
	{
		Staff s = loginService.login(account.getUsername(), account.getPassword());
		String msg = null;
		//failed login
		if(s == null)
		{
			msg = "Có lỗi xảy ra khi kết nối tới máy chủ, xin hãy thử lại sau!";
		}
		else if(s.getName().equals("0"))
		{
			msg = "Sai thông tin đăng nhập, xin hãy kiểm tra lại!";	//invalid login
		}
		else	//success
		{
			model.addAttribute("staff", s);
			session.setAttribute("staff", s);
			return "index";
		}
		model.addAttribute("errorMsg", msg);
		return "login";
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(Model model, RedirectAttributes redirect, HttpSession session)
	{
		session.removeAttribute("staff");
		return "index";
	}
}
