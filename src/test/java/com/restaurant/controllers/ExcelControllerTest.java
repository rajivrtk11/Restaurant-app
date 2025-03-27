package com.restaurant.controllers;

import com.restaurant.entity.SalesReport;
import com.restaurant.repositories.SalesReportRepository;
import com.restaurant.services.ExcelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExcelControllerTest {

    @Mock
    private ExcelService excelService;

    @Mock
    private SalesReportRepository salesRepository;

    @Mock
    private MultipartFile multipartFile;

    @InjectMocks
    private ExcelController excelController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // ✅ Initializes @Mock and @InjectMocks properly
    }

    @Test
    void testUploadExcel_Success() {
        SalesReport salesReport = new SalesReport();
        salesReport.setSegment("Consumer");
        salesReport.setCountry("USA");
        salesReport.setProduct("Laptop");
        List<SalesReport> salesReports = List.of(salesReport);

        when(excelService.readExcel(multipartFile)).thenReturn(salesReports);

        ResponseEntity<String> response = excelController.uploadExcel(multipartFile);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Excel data imported successfully!", response.getBody());

        verify(excelService, times(1)).readExcel(multipartFile);
        verify(salesRepository, times(1)).saveAll(salesReports);
    }

    @Test
    void testUploadExcel_EmptyFile() {
        when(excelService.readExcel(multipartFile)).thenReturn(Collections.emptyList());

        ResponseEntity<String> response = excelController.uploadExcel(multipartFile);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Excel data imported successfully!", response.getBody());

        verify(excelService, times(1)).readExcel(multipartFile);
        verify(salesRepository, times(1)).saveAll(Collections.emptyList());
    }

    @Test
    void testUploadExcel_ExceptionHandling() {
        when(excelService.readExcel(multipartFile)).thenThrow(new RuntimeException("File processing error"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            excelController.uploadExcel(multipartFile);
        });

        assertEquals("File processing error", exception.getMessage());

        verify(salesRepository, never()).saveAll(anyList());
    }

    @Test
    void testEquals_SameObject() {
        assertEquals(excelController, excelController);
    }

    @Test
    void testEquals_DifferentObjectsSameData() {
        ExcelController controller2 = new ExcelController(excelService, salesRepository);
        assertEquals(excelController, controller2);
    }

    @Test
    void testEquals_DifferentObjectsDifferentData() {
        ExcelService differentExcelService = mock(ExcelService.class);
        SalesReportRepository differentRepo = mock(SalesReportRepository.class);
        ExcelController controller2 = new ExcelController(differentExcelService, differentRepo);
        assertNotEquals(excelController, controller2);
    }

    @Test
    void testEquals_NullObject() {
        assertNotEquals(null, excelController);
    }

    @Test
    void testEquals_DifferentClass() {
        assertNotEquals(excelController, new Object());
    }

    @Test
    void testHashCode_SameObjects() {
        ExcelController controller2 = new ExcelController(excelService, salesRepository);
        assertEquals(excelController.hashCode(), controller2.hashCode());
    }

    @Test
    void testHashCode_DifferentObjects() {
        ExcelService differentExcelService = mock(ExcelService.class);
        SalesReportRepository differentRepo = mock(SalesReportRepository.class);
        ExcelController controller2 = new ExcelController(differentExcelService, differentRepo);
        assertNotEquals(excelController.hashCode(), controller2.hashCode());
    }

    @Test
    void testToString() {
        excelController.toString();
    }
}
