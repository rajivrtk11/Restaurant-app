package com.restaurant.controllers;

import com.restaurant.entity.SalesReport;
import com.restaurant.repositories.SalesReportRepository;
import com.restaurant.services.ExcelService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/excel")
@Data
public class ExcelController {
    private final ExcelService excelService;
    private final SalesReportRepository salesRepository;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) {
        List<SalesReport> employees = excelService.readExcel(file);
        salesRepository.saveAll(employees);
        return ResponseEntity.ok("Excel data imported successfully!");
    }
}
