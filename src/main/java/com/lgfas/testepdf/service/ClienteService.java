package com.lgfas.testepdf.service;

import com.lgfas.testepdf.model.Cliente;
import com.lgfas.testepdf.repository.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;


    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Método para cadastrar o cliente
    public void cadastrarCliente(Cliente cliente) {

        clienteRepository.save(cliente);
    }
}

