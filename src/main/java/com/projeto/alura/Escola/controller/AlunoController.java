package com.projeto.alura.Escola.controller;

import com.projeto.alura.Escola.dto.AlunoDTO;
import com.projeto.alura.Escola.entity.Aluno;
import com.projeto.alura.Escola.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepo;

    @GetMapping
    public List<Aluno> listar() {
        return alunoRepo.findAll();
    }

    @GetMapping("/{id}")
    public Aluno buscarPorId(@PathVariable Long id) {
        return alunoRepo.findById(id).orElseThrow();
    }

    @GetMapping("/nome/{nome}")
    public List<Aluno> buscarPorNome(@PathVariable String nome) {
        return alunoRepo.findByNomeContainingIgnoreCase(nome);
    }

    @GetMapping("/email/{email}")
    public Aluno buscarPorEmail(@PathVariable String email) {
        return alunoRepo.findByEmail(email);
    }

    @PostMapping
    public Aluno criar(@RequestBody AlunoDTO dto) {
        Aluno aluno = new Aluno();
        aluno.setNome(dto.nome());
        aluno.setEmail(dto.email());
        aluno.setTelefone(dto.telefone());
        return alunoRepo.save(aluno);
    }

    @PutMapping("/{id}")
    public Aluno atualizar(@PathVariable Long id, @RequestBody AlunoDTO dto) {
        Aluno aluno = alunoRepo.findById(id).orElseThrow();
        aluno.setNome(dto.nome());
        aluno.setEmail(dto.email());
        aluno.setTelefone(dto.telefone());
        return alunoRepo.save(aluno);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        alunoRepo.deleteById(id);
    }
}
