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

import qlbhxh.model.Transaction;
import qlbhxh.services.TransactionService;

@Controller
public class TransactionController {
	@Autowired
	private TransactionService transactionService;
	
	@RequestMapping(value = "/transactionlist", method = RequestMethod.GET)
	public String getTransaction(@RequestParam int days, Model model, RedirectAttributes redirect, HttpSession session)
	{
		List<Transaction> result = transactionService.getAllTransaction(days);
		model.addAttribute("listTransaction", result);
		return "transactionlist";
	}
	
	@RequestMapping(value = "/transactionlistexport", method = RequestMethod.GET)
	public String getTransactionByUser(@RequestParam String insuranceId, Model model, RedirectAttributes redirect, HttpSession session, HttpServletResponse response)
	{
		String fileName = transactionService.getTransactionByUser(insuranceId);
		if(fileName == null)
		{
			//user not found
			String msg = "User not found";
			model.addAttribute("msg", msg);
			model.addAttribute("insuranceId", insuranceId);
			return "/reports";
		}
		if(fileName.equals("0"))
		{
			//no transaction found
			String msg = "No transaction found";
			model.addAttribute("msg", msg);
			model.addAttribute("insuranceId", insuranceId);
			return "/reports";
		}
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
}
