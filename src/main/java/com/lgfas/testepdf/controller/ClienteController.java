package com.lgfas.testepdf.controller;

import com.lgfas.testepdf.model.Cliente;
import com.lgfas.testepdf.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;


    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Endpoint para cadastrar um novo cliente
    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarCliente(@RequestBody Cliente cliente) {
        // Chama o serviço para cadastrar o cliente
        clienteService.cadastrarCliente(cliente);

        return ResponseEntity.ok("Cliente cadastrado com sucesso!");
    }
}
