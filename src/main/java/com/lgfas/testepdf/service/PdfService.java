package com.lgfas.testepdf.service;

import com.lgfas.testepdf.model.Cliente;
import com.lgfas.testepdf.model.HistoricoConsumo;
import com.lgfas.testepdf.repository.ClienteRepository;
import com.lgfas.testepdf.repository.HistoricoConsumoRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PdfService {

    private final ClienteRepository clienteRepository;
    private final HistoricoConsumoRepository historicoConsumoRepository;

    public PdfService(ClienteRepository clienteRepository, HistoricoConsumoRepository historicoConsumoRepository) {
        this.clienteRepository = clienteRepository;
        this.historicoConsumoRepository = historicoConsumoRepository;
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

    public void processarDados(String textoPdf, Long clienteId) {
        // Regex para capturar os valores de PONTA, FORA PONTA, PONTA/TOT, FORA PONTA
        Pattern pattern = Pattern.compile("\\w{3}\\s+(\\d{1,3}(?:,\\d{2})*)\\s+(\\d{1,3}(?:,\\d{2})*)\\s+\\d{1,2},\\d{2}\\s+(\\d{1,3}(?:,\\d{3})*,\\d{2})\\s+(\\d{1,3}(?:,\\d{3})*,\\d{2})");

        Matcher matcher = pattern.matcher(textoPdf);

        // Recuperar o cliente pelo ID
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        // Loop para capturar todos os conjuntos de valores na tabela
        while (matcher.find()) {
            try {
                Double demandaPonta = Double.parseDouble(matcher.group(1).replace(",", "."));
                Double demandaForaPonta = Double.parseDouble(matcher.group(2).replace(",", "."));
                Double consumoPonta = Double.parseDouble(matcher.group(3).replace(".", "").replace(",", "."));
                Double consumoForaPonta = Double.parseDouble(matcher.group(4).replace(".", "").replace(",", "."));

                System.out.println("PONTA: " + demandaPonta);
                System.out.println("FORA PONTA: " + demandaForaPonta);
                System.out.println("PONTA/TOT: " + consumoPonta);
                System.out.println("FORA PONTA: " + consumoForaPonta);

                // Criar um novo registro de histórico de consumo
                HistoricoConsumo historicoConsumo = new HistoricoConsumo();
                historicoConsumo.setDemandaPonta(demandaPonta);
                historicoConsumo.setDemandaForaPonta(demandaForaPonta);
                historicoConsumo.setConsumoPonta(consumoPonta);
                historicoConsumo.setConsumoForaPonta(consumoForaPonta);
                historicoConsumo.setCliente(cliente); // Associar ao cliente

                // Salvar o histórico no banco de dados
                System.out.println("Salvando os dados no banco...");
                historicoConsumoRepository.save(historicoConsumo);

            } catch (NumberFormatException e) {
                System.out.println("Erro ao converter valores: " + e.getMessage());
            }
        }
    }
}


