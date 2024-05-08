package com.excel.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ExcelService {

	private String filePath = "C:\\Users\\siva s\\OneDrive\\Desktop\\TestExcel.xlsx";

	public List<String[]> getData() {
		List<String[]> data = new ArrayList<>();
		File file = new File(filePath);
		try (FileInputStream fis = new FileInputStream(file)) {
			Workbook workbook = new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheetAt(0);
			for (Row row : sheet) {
				int lastColumn = row.getLastCellNum();
				String[] rowData = new String[lastColumn];
				for (int col = 0; col < lastColumn; col++) {
					Cell cell = row.getCell(col, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
					rowData[col] = getStringValueFromCell(cell);
				}
				data.add(rowData);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Failed to read Excel file: " + e.getMessage());
			return Collections.emptyList(); // Ensure not returning null
		}
		return data;
	}

	private String getStringValueFromCell(Cell cell) {
		switch (cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue();
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				return new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue());
			} else {
				return new DecimalFormat("0").format(cell.getNumericCellValue());
			}
		case BOOLEAN:
			return String.valueOf(cell.getBooleanCellValue());
		case FORMULA:
			return cell.getCellFormula();
		default:
			return "";
		}
	}
}