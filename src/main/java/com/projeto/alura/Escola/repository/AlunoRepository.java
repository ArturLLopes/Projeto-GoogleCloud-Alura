package com.projeto.alura.Escola.repository;

import com.projeto.alura.Escola.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    List<Aluno> findByNomeContainingIgnoreCase(String nome);
    Aluno findByEmail(String email);
}