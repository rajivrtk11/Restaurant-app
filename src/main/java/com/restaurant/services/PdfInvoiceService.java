package com.restaurant.services;

import com.restaurant.entity.Invoice;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PdfInvoiceService {

    public Invoice extractInvoiceData(MultipartFile file) {
        try {
            PDDocument document = PDDocument.load(file.getInputStream());
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String pdfText = pdfStripper.getText(document);
            document.close();

            return parseInvoiceText(pdfText);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Invoice parseInvoiceText(String text) {
        Invoice invoice = new Invoice();

        // Example: Extract invoice number (e.g., "Invoice No: INV-12345")
        Pattern invoicePattern = Pattern.compile("Invoice No:\\s*(\\S+)");
        Matcher invoiceMatcher = invoicePattern.matcher(text);
        if (invoiceMatcher.find()) {
            invoice.setInvoiceNumber(invoiceMatcher.group(1));
        }

        // Example: Extract customer name (e.g., "Customer: John Doe")
        Pattern customerPattern = Pattern.compile("Customer:\\s*(.+)");
        Matcher customerMatcher = customerPattern.matcher(text);
        if (customerMatcher.find()) {
            invoice.setCustomerName(customerMatcher.group(1));
        }

        // Example: Extract date (e.g., "Date: 2024-03-25")
        Pattern datePattern = Pattern.compile("Date:\\s*(\\d{4}-\\d{2}-\\d{2})");
        Matcher dateMatcher = datePattern.matcher(text);
        if (dateMatcher.find()) {
            invoice.setDate(dateMatcher.group(1));
        }

        // Example: Extract total amount (e.g., "Total: $1234.56")
        Pattern amountPattern = Pattern.compile("Total:\\s*\\$?(\\d+\\.\\d{2})");
        Matcher amountMatcher = amountPattern.matcher(text);
        if (amountMatcher.find()) {
            invoice.setTotalAmount(Double.parseDouble(amountMatcher.group(1)));
        }

        return invoice;
    }
}

