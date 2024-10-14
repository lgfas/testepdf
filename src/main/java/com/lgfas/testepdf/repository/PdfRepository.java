package com.lgfas.testepdf.repository;

import com.lgfas.testepdf.model.Pdf;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PdfRepository extends JpaRepository<Pdf, Long> {
}
