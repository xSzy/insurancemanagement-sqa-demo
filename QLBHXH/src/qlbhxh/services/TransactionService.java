package qlbhxh.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import qlbhxh.dao.TransactionDAO;
import qlbhxh.dao.UserDAO;
import qlbhxh.model.Transaction;
import qlbhxh.model.User;

/*
 * This class provide services for transaction operators
 */
@Service
public class TransactionService {
	@Autowired
	private TransactionDAO transactionDAO;
	
	@Autowired
	private UserDAO userDAO;

	/**
	 * This function returns all transactions in a fixed days period
	 * @param days - number of days to look back
	 * @return list of all transaction
	 */
	public List<Transaction> getAllTransaction(int days) {
		return transactionDAO.getAllTransaction(days);
	}

	/**
	 * This function gets all transaction made by user with insuranceId
	 * @param insuranceId
	 * @return xls file name
	 */
	public String getTransactionByUser(String insuranceId) {
		//check if any user has that insuranceId
		User u = userDAO.getUserByInsuranceId(insuranceId);
		if(u == null)
			return null;	//user not found
		
		List<Transaction> result = transactionDAO.getTransactionByUser(insuranceId);
		if(result.size() == 0)
			return "0";		//no transaction found
		
		String fileName = this.createTransactionXlsReport(result);
		
		return fileName;
	}
	
	/**
	 * This function create xls file for user transaction report
	 * @param listTransaction : list transaction of that user
	 * @return xls file name
	 */
	public String createTransactionXlsReport(List<Transaction> listTransaction)
	{
		//create new workbook
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		//create new sheet
		HSSFSheet sheet = workbook.createSheet();
		
		//initialize members
		int rownum = 0;
		Cell cell;
		Row row;
		
		//initialize styles
		HSSFCellStyle style = createStyleForTitle(workbook);
		
		//string pattern for dates and numbers
		String pattern = "###,###,###";
		DecimalFormat decimalFormat = new DecimalFormat(pattern);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		//write user's information
		row = sheet.createRow(rownum);
		cell = row.createCell(0, CellType.STRING);
		cell.setCellValue("Họ và tên:");
		
		cell = row.createCell(2, CellType.STRING);
		cell.setCellValue(listTransaction.get(0).getUser().getName());
		rownum++;
		
		row = sheet.createRow(rownum);
		cell = row.createCell(0, CellType.STRING);
		cell.setCellValue("Mã BHXH:");
		
		cell = row.createCell(2, CellType.STRING);
		cell.setCellValue(listTransaction.get(0).getUser().getInsuranceId());
		rownum++;
		
		row = sheet.createRow(rownum);
		cell = row.createCell(0, CellType.STRING);
		cell.setCellValue("Địa chỉ:");
		
		cell = row.createCell(2, CellType.STRING);
		cell.setCellValue(listTransaction.get(0).getUser().getAddress());
		rownum++;
		
		row = sheet.createRow(rownum);
		cell = row.createCell(0, CellType.STRING);
		cell.setCellValue("Ngày sinh:");
		
		cell = row.createCell(2, CellType.STRING);
		cell.setCellValue(sdf.format(listTransaction.get(0).getUser().getDob()));
		rownum++;
		
		row = sheet.createRow(rownum);
		cell = row.createCell(0, CellType.STRING);
		cell.setCellValue("Số điện thoại:");
		
		cell = row.createCell(2, CellType.STRING);
		cell.setCellValue(listTransaction.get(0).getUser().getPhone());
		rownum++;
		
		row = sheet.createRow(rownum);
		cell = row.createCell(0, CellType.STRING);
		cell.setCellValue("Giới tính:");
		
		cell = row.createCell(2, CellType.STRING);
		cell.setCellValue(listTransaction.get(0).getUser().getSex() == true ? "Nam" : "Nữ");
		rownum++;
		
		row = sheet.createRow(rownum);
		cell = row.createCell(0, CellType.STRING);
		cell.setCellValue("Số CMND:");
		
		cell = row.createCell(2, CellType.STRING);
		cell.setCellValue(listTransaction.get(0).getUser().getIdentityNumber());
		rownum++;
		
		row = sheet.createRow(rownum);
		cell = row.createCell(0, CellType.STRING);
		cell.setCellValue("Diện người dùng:");
		
		cell = row.createCell(2, CellType.STRING);
		cell.setCellValue(listTransaction.get(0).getUser().getType().getName());
		rownum++;
		
		row = sheet.createRow(rownum);
		cell = row.createCell(0, CellType.STRING);
		cell.setCellValue("Mã số thuế:");
		
		cell = row.createCell(2, CellType.STRING);
		cell.setCellValue(listTransaction.get(0).getUser().getTaxCode());
		rownum++;
		
		rownum++;
		
		//writing title
		row = sheet.createRow(rownum);
		
		cell = row.createCell(0, CellType.STRING);
		cell.setCellValue("Mã giao dịch");
		cell.setCellStyle(style);
		
		cell = row.createCell(1, CellType.STRING);
		cell.setCellValue("Mã BHXH");
		cell.setCellStyle(style);
		
		cell = row.createCell(2, CellType.STRING);
		cell.setCellValue("Họ và tên");
		cell.setCellStyle(style);
		
		cell = row.createCell(3, CellType.STRING);
		cell.setCellValue("Đơn vị đóng");
		cell.setCellStyle(style);
		
		cell = row.createCell(4, CellType.STRING);
		cell.setCellValue("Ngày đóng");
		cell.setCellStyle(style);
		
		cell = row.createCell(5, CellType.STRING);
		cell.setCellValue("Số tháng đóng");
		cell.setCellStyle(style);
		
		cell = row.createCell(6, CellType.STRING);
		cell.setCellValue("Số tiền đóng");
		cell.setCellStyle(style);
		
		cell = row.createCell(7, CellType.STRING);
		cell.setCellValue("Nhân viên giao dịch");
		cell.setCellStyle(style);
		
		rownum++;
		
		//writing each row
		for(Transaction trans : listTransaction)
		{
			row = sheet.createRow(rownum);
			
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue(trans.getID());
			//cell.setCellStyle(style);
			
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue(trans.getUser().getInsuranceId());
			//cell.setCellStyle(style);
			
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue(trans.getUser().getName());
			//cell.setCellStyle(style);
			
			cell = row.createCell(3, CellType.STRING);
			if(trans.getCompany().getCompanyName() == null)
				cell.setCellValue("Tự đóng");
			else
				cell.setCellValue(trans.getCompany().getCompanyName());
			//cell.setCellStyle(style);
			
			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue(sdf.format(trans.getDate()));
			//cell.setCellStyle(style);
			
			cell = row.createCell(5, CellType.STRING);
			cell.setCellValue(trans.getDuration());
			//cell.setCellStyle(style);
			
			cell = row.createCell(6, CellType.STRING);
			cell.setCellValue(decimalFormat.format(trans.getAmount()) + " VND");
			//cell.setCellStyle(style);
			
			cell = row.createCell(7, CellType.STRING);
			cell.setCellValue(trans.getStaff().getName());
			//cell.setCellStyle(style);
			
			rownum++;
		}
		
		//create file
		String fileName = "E:\\" + "listtransaction" + listTransaction.get(0).getUser().getInsuranceId() + ".xls";
		File file = new File(fileName);
		try {
			FileOutputStream fos = new FileOutputStream(file);
			workbook.write(fos);
			fos.close();
			workbook.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return fileName;
	}

	private HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
		HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setFillBackgroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        return style;
	}
	
}
