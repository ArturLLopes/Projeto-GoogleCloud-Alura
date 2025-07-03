package com.projeto.alura.Escola.repository;

import com.projeto.alura.Escola.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    Curso findByCodigo(String codigo);
}