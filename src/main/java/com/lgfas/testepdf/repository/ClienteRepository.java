package com.lgfas.testepdf.repository;

import com.lgfas.testepdf.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}