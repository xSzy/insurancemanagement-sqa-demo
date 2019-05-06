package qlbhxh.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import qlbhxh.model.Fund;
import qlbhxh.model.InsuranceConfig;
import qlbhxh.services.ConfigService;
import qlbhxh.services.FundService;

@Controller
public class ConfigController {
	@Autowired
	private ConfigService configService;
	
	@Autowired
	private FundService fundService;
	
	@RequestMapping(value = "/saveConfig", method = RequestMethod.GET)
	public String test(@RequestParam float interestRate, @RequestParam int minSal, @RequestParam int maxSal,
			@RequestParam double ODTSrequired, @RequestParam double ODTScompany, @RequestParam double ODTSwilling,
			@RequestParam double TNLDrequired, @RequestParam double TNLDcompany, @RequestParam double TNLDwilling,
			@RequestParam double HTTTrequired, @RequestParam double HTTTcompany, @RequestParam double HTTTwilling,
			HttpSession session, ModelMap model)
	{
		
		//create new configs
		InsuranceConfig config = new InsuranceConfig();
		config.setInterestRate(interestRate);
		config.setMaximumIncome(maxSal);
		config.setMinimumIncome(minSal);
		Fund fund = new Fund();
		fund.setRequiredODTS(ODTSrequired);
		fund.setCompanyODTS(ODTScompany);
		fund.setWillingODTS(ODTSwilling);
		fund.setRequiredTNLD(TNLDrequired);
		fund.setCompanyTNLD(TNLDcompany);
		fund.setWillingTNLD(TNLDwilling);
		fund.setRequiredHTTT(HTTTrequired);
		fund.setCompanyHTTT(HTTTcompany);
		fund.setWillingHTTT(HTTTwilling);
		
		//update
		boolean result1 = configService.updateConfig(config);
		boolean result2 = fundService.updateFundConfig(fund);
		
		if(result1 || result2)
			model.addAttribute("result", true);	//update successful
		else
			model.addAttribute("result", false);	//update failed
		
		fund = fundService.getFundConfig();
		config = configService.getConfig();
		model.addAttribute("fund", fund);
		model.addAttribute("config", config);
		
		return "/configuration";
	}
}
