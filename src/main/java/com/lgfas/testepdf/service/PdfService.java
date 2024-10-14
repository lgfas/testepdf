package com.lgfas.testepdf.service;

import com.lgfas.testepdf.model.Pdf;
import com.lgfas.testepdf.repository.PdfRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

@Service
public class PdfService {

    private final PdfRepository pdfRepository;

    public PdfService(PdfRepository pdfRepository) {
        this.pdfRepository = pdfRepository;
    }

    public String extractPdfText(MultipartFile file) throws IOException {
        PDDocument document = PDDocument.load(file.getInputStream());
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(document);
        document.close();
        return text;
    }

    public void processarDados(String textoPdf) throws ParseException {
        // Regex para capturar o consumo médio diário
        Pattern pattern = Pattern.compile("Consumo Médio Diário \\(KWh\\):\\s*(\\d+\\.\\d{3},\\d{2})");
        Matcher matcher = pattern.matcher(textoPdf);

        if (matcher.find()) {
            String valorTexto = matcher.group(1);  // Captura o valor numérico
            System.out.println("Valor encontrado: " + valorTexto);

            // Converter o valor de string para Double (considerando o formato PT-BR)
            Double consumoMedioDiario = converterParaDouble(valorTexto);

            // Salvar no banco de dados
            Pdf pdf = new Pdf();
            pdf.setConsumoMedioDiario(consumoMedioDiario);
            pdfRepository.save(pdf);

            System.out.println("Valor salvo: " + consumoMedioDiario);
        }
    }

    // Método auxiliar para converter string de valor no formato PT-BR (ex: "2.445,33") para Double
    private Double converterParaDouble(String valorTexto) throws ParseException {
        NumberFormat format = NumberFormat.getInstance(new Locale("pt", "BR"));
        Number number = format.parse(valorTexto);
        return number.doubleValue();
    }
}

