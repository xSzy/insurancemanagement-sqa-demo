package qlbhxh.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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

import qlbhxh.dao.CommonDAO;
import qlbhxh.dao.InsuranceDAO;
import qlbhxh.model.Insurance;
import qlbhxh.model.Transaction;

/*
 * This class provide services for insurance operators
 */
@Service
public class InsuranceService {
	@Autowired
	private InsuranceDAO insuranceDAO;
	
	@Autowired
	private CommonDAO commonDAO;

	/**
	 * This function gets all debt user in days period
	 * @param daysInDebt number of days to search
	 * @return list of debt user
	 */
	public List<Insurance> getDebtList(int daysInDebt) {
		return insuranceDAO.getDebtList(daysInDebt);
	}
	
	/**
	 * This function exports debt list to xls file
	 * @param daysInDebt number of days to search
	 * @return xls file name
	 */
	public String exportDebtList(int daysInDebt)
	{
		List<Insurance> listDebt = this.getDebtList(daysInDebt);
		String fileName = createDebtListReport(listDebt);
		return fileName;
	}
	
	/**
	 * This function export general report to xls file
	 * @return xls file name
	 */
	public String exportGeneralReport()
	{
		List<Insurance> listDebt = this.getDebtList(0);
		//get number of user
		int numberOfUser = commonDAO.getNumberOfUser();
		//get number of debt
		int numberOfDebt = commonDAO.getNumberOfDebtUser();
		//create file
		String fileName = createGeneralReport(numberOfUser, numberOfDebt, listDebt);
		return fileName;
	}
	
	/**
	 * This function creates the xls report file for general report
	 * @param numberOfUser: number of user
	 * @param numberOfDebt: number of debt
	 * @param listDebt: list of all debt user
	 * @return xls file name
	 */
	private String createGeneralReport(int numberOfUser, int numberOfDebt, List<Insurance> listDebt) {
		
		//create new xls workbook
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		//create new xls sheet
		HSSFSheet sheet = workbook.createSheet();
		
		//initialize members
		int rownum = 0;
		Cell cell;
		Row row;
		
		//create cell style
		HSSFCellStyle style = createStyleForTitle(workbook);
		
		//create some patterns for string format
		String pattern = "###,###,###";
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		//FILE CONTENT
		//create general information rows
		row = sheet.createRow(rownum);
		cell = row.createCell(0, CellType.STRING);
		cell.setCellValue("Số lượng người dùng");
		cell.setCellStyle(style);
		
		cell = row.createCell(2, CellType.STRING);
		cell.setCellValue(numberOfUser);
		
		rownum++;
		
		row = sheet.createRow(rownum);
		cell = row.createCell(0, CellType.STRING);
		cell.setCellValue("Số lượng người chưa đóng");
		cell.setCellStyle(style);
		
		cell = row.createCell(2, CellType.STRING);
		cell.setCellValue(numberOfDebt);
		
		rownum++;
		
		row = sheet.createRow(rownum);
		cell = row.createCell(0, CellType.STRING);
		cell.setCellValue("Số lượng người đã đóng");
		cell.setCellStyle(style);
		
		cell = row.createCell(2, CellType.STRING);
		cell.setCellValue(numberOfUser - numberOfDebt);
		
		rownum++;
		
		row = sheet.createRow(rownum);
		cell = row.createCell(0, CellType.STRING);
		cell.setCellValue("Tỉ lệ nợ");
		cell.setCellStyle(style);
		
		cell = row.createCell(2, CellType.STRING);
		float percentage = numberOfUser <= 0 ? 0 : (float) numberOfDebt / (float)numberOfUser * 100;
		cell.setCellValue(percentage+"%");
		
		rownum++;
		
		row = sheet.createRow(rownum);
		cell = row.createCell(0, CellType.STRING);
		cell.setCellValue("Tỉ lệ đóng");
		cell.setCellStyle(style);
		
		cell = row.createCell(2, CellType.STRING);
		cell.setCellValue((100-percentage)+"%");
		
		rownum++;
		
		rownum++;
		
		//creating title row
		row = sheet.createRow(rownum);
		
		cell = row.createCell(0, CellType.STRING);
		cell.setCellValue("Mã BHXH");
		cell.setCellStyle(style);
		
		cell = row.createCell(1, CellType.STRING);
		cell.setCellValue("Họ và tên");
		cell.setCellStyle(style);
		
		cell = row.createCell(2, CellType.STRING);
		cell.setCellValue("Địa chỉ");
		cell.setCellStyle(style);
		
		cell = row.createCell(3, CellType.STRING);
		cell.setCellValue("Ngày sinh");
		cell.setCellStyle(style);
		
		cell = row.createCell(4, CellType.STRING);
		cell.setCellValue("Giới tính");
		cell.setCellStyle(style);
		
		cell = row.createCell(5, CellType.STRING);
		cell.setCellValue("Diện người dùng");
		cell.setCellStyle(style);

		cell = row.createCell(6, CellType.STRING);
		cell.setCellValue("Ngày hết hạn");
		cell.setCellStyle(style);
		
		cell = row.createCell(7, CellType.STRING);
		cell.setCellValue("Số tháng nợ");
		cell.setCellStyle(style);
		
		rownum++;
		
		//writing each row
		for(Insurance insurance : listDebt)
		{
			row = sheet.createRow(rownum);
			
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue(insurance.getUser().getInsuranceId());
			//cell.setCellStyle(style);
			
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue(insurance.getUser().getName());
			//cell.setCellStyle(style);
			
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue(insurance.getUser().getAddress());
			//cell.setCellStyle(style);
			
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue(sdf.format(insurance.getUser().getDob()));
			//cell.setCellStyle(style);
			
			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue(insurance.getUser().getSex() == true ? "Nam" : "Nữ");
			//cell.setCellStyle(style);
			
			cell = row.createCell(5, CellType.STRING);
			cell.setCellValue(insurance.getUser().getType().getName());
			//cell.setCellStyle(style);
			
			cell = row.createCell(6, CellType.STRING);
			cell.setCellValue(sdf.format(insurance.getDateEnd()));
			//cell.setCellStyle(style);
			
			cell = row.createCell(7, CellType.STRING);
			cell.setCellValue(insurance.getDaysInDebt()/12);
			//cell.setCellStyle(style);
			
			rownum++;
		}
		
		//create file
		String fileName = "E:\\generalreport.xls";
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

	/**
	 * This function creates the xls file for debt list report
	 * @param listDebt: list of debt user
	 * @return xls file name
	 */
	public String createDebtListReport(List<Insurance> listDebt)
	{
		//create new workbook
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		//create new sheet
		HSSFSheet sheet = workbook.createSheet();
		
		//initialize members
		int rownum = 0;
		Cell cell;
		Row row;
		
		//create style
		HSSFCellStyle style = createStyleForTitle(workbook);
		
		//create format patterns
		String pattern = "###,###,###";
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		//file content
		//creating title row
		row = sheet.createRow(rownum);
		
		cell = row.createCell(0, CellType.STRING);
		cell.setCellValue("Mã BHXH");
		cell.setCellStyle(style);
		
		cell = row.createCell(1, CellType.STRING);
		cell.setCellValue("Họ và tên");
		cell.setCellStyle(style);
		
		cell = row.createCell(2, CellType.STRING);
		cell.setCellValue("Địa chỉ");
		cell.setCellStyle(style);
		
		cell = row.createCell(3, CellType.STRING);
		cell.setCellValue("Ngày sinh");
		cell.setCellStyle(style);
		
		cell = row.createCell(4, CellType.STRING);
		cell.setCellValue("Giới tính");
		cell.setCellStyle(style);
		
		cell = row.createCell(5, CellType.STRING);
		cell.setCellValue("Diện người dùng");
		cell.setCellStyle(style);

		cell = row.createCell(6, CellType.STRING);
		cell.setCellValue("Ngày hết hạn");
		cell.setCellStyle(style);
		
		cell = row.createCell(7, CellType.STRING);
		cell.setCellValue("Số tháng nợ");
		cell.setCellStyle(style);
		
		rownum++;
		
		//writing each row
		for(Insurance insurance : listDebt)
		{
			row = sheet.createRow(rownum);
			
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue(insurance.getUser().getInsuranceId());
			//cell.setCellStyle(style);
			
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue(insurance.getUser().getName());
			//cell.setCellStyle(style);
			
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue(insurance.getUser().getAddress());
			//cell.setCellStyle(style);
			
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue(sdf.format(insurance.getUser().getDob()));
			//cell.setCellStyle(style);
			
			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue(insurance.getUser().getSex() == true ? "Nam" : "Nữ");
			//cell.setCellStyle(style);
			
			cell = row.createCell(5, CellType.STRING);
			cell.setCellValue(insurance.getUser().getType().getName());
			//cell.setCellStyle(style);
			
			cell = row.createCell(6, CellType.STRING);
			cell.setCellValue(sdf.format(insurance.getDateEnd()));
			//cell.setCellStyle(style);
			
			cell = row.createCell(7, CellType.STRING);
			cell.setCellValue(insurance.getDaysInDebt()/12);
			//cell.setCellStyle(style);
			
			rownum++;
		}
		
		//create file
		String fileName = "E:\\listdebt.xls";
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
	
	private HSSFCellStyle createBoldStyle(HSSFWorkbook workbook) {
		HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
	}
}
