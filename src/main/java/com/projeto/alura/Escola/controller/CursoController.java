package com.projeto.alura.Escola.controller;

import com.projeto.alura.Escola.dto.CursoDTO;
import com.projeto.alura.Escola.entity.Curso;
import com.projeto.alura.Escola.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepo;

    @GetMapping
    public List<Curso> listar() {
        return cursoRepo.findAll();
    }

    @GetMapping("/{codigo}")
    public Curso buscarPorCodigo(@PathVariable String codigo) {
        return cursoRepo.findByCodigo(codigo);
    }

    @PostMapping
    public Curso criar(@RequestBody CursoDTO dto) {
        Curso curso = new Curso();
        curso.setNome(dto.nome());
        curso.setCodigo(dto.codigo());
        curso.setDescricao(dto.descricao());
        return cursoRepo.save(curso);
    }

    @PutMapping("/{codigo}")
    public Curso atualizar(@PathVariable String codigo, @RequestBody CursoDTO dto) {
        Curso curso = cursoRepo.findByCodigo(codigo);
        curso.setNome(dto.nome());
        curso.setDescricao(dto.descricao());
        return cursoRepo.save(curso);
    }
}
