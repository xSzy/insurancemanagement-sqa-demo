package qlbhxh.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import qlbhxh.model.Insurance;
import qlbhxh.services.InsuranceService;

@Controller
public class InsuranceController {
	@Autowired
	private InsuranceService insuranceService;
	
	@RequestMapping(value = "/debtlist", method = RequestMethod.GET)
	public String getDebtList(@RequestParam int daysInDebt, Model model, RedirectAttributes redirect, HttpSession session)
	{
		List<Insurance> result = insuranceService.getDebtList(daysInDebt);
		model.addAttribute("listDebt", result);
		return "debtlist";
	}
	
	@RequestMapping(value = "/debtlistexport", method = RequestMethod.GET)
	public String downloadDebtList(@RequestParam int daysInDebt, Model model, RedirectAttributes redirect, HttpSession session, HttpServletResponse response)
	{
		String fileName = insuranceService.exportDebtList(daysInDebt);
		System.out.println("File created: " + fileName);
		try {
			//create file download
			response.setContentType("application/vnd.ms-excel");
			InputStream is = new FileInputStream(fileName);
			IOUtils.copy(is, response.getOutputStream());
			response.getOutputStream().flush();
			response.getOutputStream().close();
			response.flushBuffer();
			System.out.println("File pushed");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/reports";
	}
	
	@RequestMapping(value = "/generalreportexport", method = RequestMethod.GET)
	public String downloadGeneralReport(Model model, RedirectAttributes redirect, HttpSession session, HttpServletResponse response)
	{
		String fileName = insuranceService.exportGeneralReport();
		System.out.println("File created: " + fileName);
		try {
			response.setContentType("application/vnd.ms-excel");
			InputStream is = new FileInputStream(fileName);
			IOUtils.copy(is, response.getOutputStream());
			response.getOutputStream().flush();
			response.getOutputStream().close();
			response.flushBuffer();
			System.out.println("File pushed");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/reports";
	}
}
