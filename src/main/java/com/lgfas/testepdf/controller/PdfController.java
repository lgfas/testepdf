package com.lgfas.testepdf.controller;

import com.lgfas.testepdf.service.PdfService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;

@RestController
@RequestMapping("/pdf")
public class PdfController {

    private final PdfService pdfService;

    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @PostMapping("/upload-pdf")
    public ResponseEntity<String> uploadPdf(@RequestParam("file") MultipartFile file, Long clienteId) throws IOException, ParseException {
        String textoPdf = pdfService.extractPdfText(file);
        pdfService.processarDados(textoPdf, clienteId);
        return ResponseEntity.ok("Dados do PDF processados e salvos com sucesso");
    }
}
