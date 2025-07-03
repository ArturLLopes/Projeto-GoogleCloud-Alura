package com.projeto.alura.Escola.controller;

import com.projeto.alura.Escola.dto.MatriculaDTO;
import com.projeto.alura.Escola.entity.Aluno;
import com.projeto.alura.Escola.entity.Curso;
import com.projeto.alura.Escola.entity.Matricula;
import com.projeto.alura.Escola.repository.AlunoRepository;
import com.projeto.alura.Escola.repository.CursoRepository;
import com.projeto.alura.Escola.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaRepository matriculaRepo;

    @Autowired
    private AlunoRepository alunoRepo;

    @Autowired
    private CursoRepository cursoRepo;

    @PostMapping
    public Matricula criar(@RequestBody MatriculaDTO dto) {
        Aluno aluno = alunoRepo.findById(dto.alunoId()).orElseThrow();
        Curso curso = cursoRepo.findById(dto.cursoId()).orElseThrow();

        Matricula matricula = new Matricula();
        matricula.setAluno(aluno);
        matricula.setCurso(curso);
        return matriculaRepo.save(matricula);
    }

    @GetMapping("/aluno/{nome}")
    public Map<String, Object> listarCursosDeAluno(@PathVariable String nome) {
        Aluno aluno = alunoRepo.findByNomeContainingIgnoreCase(nome).stream().findFirst().orElseThrow();

        List<String> cursos = new ArrayList<>();
        for (Matricula m : aluno.getMatriculas()) {
            cursos.add(m.getCurso().getNome());
        }

        return Map.of("aluno", aluno.getNome(), "cursos", cursos);
    }

    @GetMapping("/curso/{codigo}")
    public Map<String, Object> listarAlunosDeCurso(@PathVariable String codigo) {
        Curso curso = cursoRepo.findByCodigo(codigo);
        List<String> alunos = new ArrayList<>();
        for (Matricula m : curso.getMatriculas()) {
            alunos.add(m.getAluno().getNome());
        }
        return Map.of("curso", curso.getNome(), "alunos", alunos);
    }
}
