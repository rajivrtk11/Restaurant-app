package com.restaurant.services;

import com.restaurant.entity.SalesReport;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExcelServiceTest {

    private ExcelService excelService;

    @Mock
    private MultipartFile multipartFile;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        excelService = new ExcelService();
    }

    @Test
    void testReadExcel_Success() throws Exception {
        // Create a sample Excel file
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sales Data");

        // Create header row
        Row headerRow = sheet.createRow(0);
        String[] headers = {
                "Segment", "Country", "Product", "Discount Band", "Units Sold",
                "Manufacturing Price", "Sale Price", "Gross Sales", "Discounts",
                "Sales", "COGS", "Profit", "Date", "Month Number", "Month Name", "Year"
        };
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }

        // Create sample data row
        Row dataRow = sheet.createRow(1);
        dataRow.createCell(0).setCellValue("Consumer");
        dataRow.createCell(1).setCellValue("USA");
        dataRow.createCell(2).setCellValue("Laptop");
        dataRow.createCell(3).setCellValue("Low");
        dataRow.createCell(4).setCellValue(500);
        dataRow.createCell(5).setCellValue(200);
        dataRow.createCell(6).setCellValue(400);
        dataRow.createCell(7).setCellValue(200000);
        dataRow.createCell(8).setCellValue(5000);
        dataRow.createCell(9).setCellValue(195000);
        dataRow.createCell(10).setCellValue(120000);
        dataRow.createCell(11).setCellValue(75000);
        dataRow.createCell(12).setCellValue("2024-03-27");
        dataRow.createCell(13).setCellValue(3);
        dataRow.createCell(14).setCellValue("March");
        dataRow.createCell(15).setCellValue(2024);

        // Write to output stream
        workbook.write(outputStream);
        workbook.close();
        byte[] excelBytes = outputStream.toByteArray();
        InputStream inputStream = new ByteArrayInputStream(excelBytes);

        // Mock MultipartFile behavior
        when(multipartFile.getInputStream()).thenReturn(inputStream);

        // Call the method under test
        List<SalesReport> result = excelService.readExcel(multipartFile);

        // Assertions
        assertNotNull(result);
        assertEquals(1, result.size()); // Only 1 row of data (excluding header)
        SalesReport salesReport = result.get(0);

        assertEquals("Consumer", salesReport.getSegment());
        assertEquals("USA", salesReport.getCountry());
        assertEquals("Laptop", salesReport.getProduct());
        assertEquals("Low", salesReport.getDiscountBand());
        assertEquals("500", salesReport.getUnitsSold());
        assertEquals("200", salesReport.getManufacturingPrice());
        assertEquals("400", salesReport.getSalePrice());
        assertEquals("200000", salesReport.getGrossSales());
        assertEquals("5000", salesReport.getDiscounts());
        assertEquals("195000", salesReport.getSales());
        assertEquals("120000", salesReport.getCogs());
        assertEquals("75000", salesReport.getProfit());
        assertEquals("2024-03-27", salesReport.getDate());
        assertEquals("3", salesReport.getMonthNumber());
        assertEquals("March", salesReport.getMonthName());
        assertEquals("2024", salesReport.getYear());

        // Verify that getInputStream() was called on the mock file
        verify(multipartFile, times(1)).getInputStream();
    }

    @Test
    void testReadExcel_ExceptionHandling() throws Exception {
        // Mock MultipartFile to throw an IOException when getInputStream() is called
        when(multipartFile.getInputStream()).thenThrow(new IOException("File read error"));

        // Call the method under test
        List<SalesReport> result = excelService.readExcel(multipartFile);

        // Assertions
        assertNotNull(result);
        assertTrue(result.isEmpty()); // Should return an empty list on exception

        // Verify that getInputStream() was called
        verify(multipartFile, times(1)).getInputStream();
    }
}
