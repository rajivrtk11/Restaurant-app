package com.restaurant.controllers;

import com.restaurant.entity.Invoice;
import com.restaurant.repositories.InvoiceRepository;
import com.restaurant.services.PdfInvoiceService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/invoices")
@Data
public class PdfInvoiceController {
    private final PdfInvoiceService pdfInvoiceService;
    private final InvoiceRepository invoiceRepository;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadPdf(@RequestParam("file") MultipartFile file) {
        Invoice invoice = pdfInvoiceService.extractInvoiceData(file);
        if (invoice != null) {
            invoiceRepository.save(invoice);
            return ResponseEntity.ok("Invoice data extracted and saved successfully!");
        } else {
            return ResponseEntity.badRequest().body("Failed to extract invoice data.");
        }
    }
}

