package com.lgfas.testepdf.service;

import com.lgfas.testepdf.model.Pdf;
import com.lgfas.testepdf.repository.PdfRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        System.out.println("Texto extraído do pdf: ");
        System.out.println(text);
        return text;
    }

    public void processarDados(String textoPdf) {
        // Regex para capturar os valores
        Pattern pattern = Pattern.compile("(\\d{1,3}(?:\\.\\d{3})*,\\d{2})\\s+(\\d{1,3}(?:\\.\\d{3})*,\\d{2})\\s+(\\d{1,3}(?:\\.\\d{3})*,\\d{2})\\s+(\\d{1,3}(?:\\.\\d{3})*,\\d{2})");

        // Variáveis para armazenar os valores extraídos
        Double ponta = null;
        Double foraPonta = null;
        Double pontaTot = null;
        Double foraPontaTot = null;

        // Capturar os valores
        Matcher matcher = pattern.matcher(textoPdf);
        if (matcher.find()) {
            ponta = Double.parseDouble(matcher.group(1).replace(".", "").replace(",", "."));
            foraPonta = Double.parseDouble(matcher.group(2).replace(".", "").replace(",", "."));
            pontaTot = Double.parseDouble(matcher.group(3).replace(".", "").replace(",", "."));
            foraPontaTot = Double.parseDouble(matcher.group(4).replace(".", "").replace(",", "."));

            System.out.println("PONTA: " + ponta);
            System.out.println("FORA PONTA: " + foraPonta);
            System.out.println("PONTA/TOT: " + pontaTot);
            System.out.println("FORA PONTA/TOT: " + foraPontaTot);
        } else {
            System.out.println("Nenhum valor encontrado.");
        }

        // Salvar os dados no banco de dados
        Pdf pdf = new Pdf();
        pdf.setDemandaPonta(ponta);
        pdf.setDemandaForaPonta(foraPonta);
        pdf.setConsumoPonta(pontaTot);
        pdf.setConsumoForaPonta(foraPontaTot);

        pdfRepository.save(pdf);
    }
}

