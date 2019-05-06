package qlbhxh.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import qlbhxh.model.Fund;
import qlbhxh.model.InsuranceConfig;
import qlbhxh.services.ConfigService;
import qlbhxh.services.FundService;

@Controller
public class MainController {
	@Autowired
	private ConfigService configService;
	
	@Autowired
	private FundService fundService;
	
	@RequestMapping(value = "/")
	public String test(HttpSession session, ModelMap model)
	{
		return "index";
	}
	
	@RequestMapping(value = {"/login"})
	public String login(HttpSession session, ModelMap model)
	{
		return "login";
	}
	
	@RequestMapping(value = {"/index"})
	public String index(HttpSession session, ModelMap model)
	{
		return "index";
	}
	
	@RequestMapping(value = {"/list"})
	public String danhsach(HttpSession session, ModelMap model)
	{
		return "list";
	}
	
	@RequestMapping(value = {"/reports"})
	public String xuatbaocao(HttpSession session, ModelMap model)
	{
		return "reports";
	}
	
	@RequestMapping(value = {"/configuration"})
	public String cauhinh(HttpSession session, ModelMap model)
	{
		//get fund and configs to fill in the input boxes
		Fund fund = fundService.getFundConfig();
		InsuranceConfig config = configService.getConfig();
		model.addAttribute("fund", fund);
		model.addAttribute("config", config);
		return "configuration";
	}
}
