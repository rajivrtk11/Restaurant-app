package com.restaurant.services;

import com.restaurant.entity.SalesReport;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ExcelService {

    public List<SalesReport> readExcel(MultipartFile file) {
        List<SalesReport> salesReportList = new ArrayList<>();

        try{
            InputStream inputStream = file.getInputStream();
            Workbook workbook = WorkbookFactory.create(inputStream);

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            // Skip header row
            if (rowIterator.hasNext()) rowIterator.next();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                SalesReport employee = new SalesReport();
                employee.setSegment(row.getCell(0).getStringCellValue());
                employee.setCountry(row.getCell(1).getStringCellValue());
                employee.setProduct(row.getCell(2).getStringCellValue());
                employee.setDiscountBand(row.getCell(3).getStringCellValue());
                employee.setUnitsSold(getCellValue(row.getCell(4)));
                employee.setManufacturingPrice(getCellValue(row.getCell(5)));
                employee.setSalePrice(getCellValue(row.getCell(6)));
                employee.setGrossSales(getCellValue(row.getCell(7)));
                employee.setDiscounts(getCellValue(row.getCell(8)));
                employee.setSales(getCellValue(row.getCell(9)));
                employee.setCogs(getCellValue(row.getCell(10)));
                employee.setProfit(getCellValue(row.getCell(11)));
                employee.setDate(getCellValue(row.getCell(12)));
                employee.setMonthNumber(getCellValue(row.getCell(13)));
                employee.setMonthName(getCellValue(row.getCell(14)));
                employee.setYear(getCellValue(row.getCell(15)));

                salesReportList.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return salesReportList;
    }

    private String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString(); // Convert date to string
                }
                return String.valueOf((long) cell.getNumericCellValue()); // Convert numeric to string
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return "";
        }
    }
}

