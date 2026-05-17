package com.exemplo.cursoservice.service;

import com.exemplo.cursoservice.model.Curso;
import com.exemplo.cursoservice.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Camada de negocio do microservico de cursos.
 */
@Service
public class CursoService {


    private final CursoRepository repository;

    public CursoService(CursoRepository repository) {
        this.repository = repository;
    }

    public List<Curso> listarTodas() {
        return repository.findAll();
    }

    public Optional<Curso> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public List<Curso> listarAtivas() {
        return repository.findByAtivo(true);
    }

    public List<Curso> listarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    public Curso salvar(Curso curso) {
        return repository.save(curso);
    }

    public Curso atualizar(Long id, Curso dados) {
        Curso existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso nao encontrada: " + id));
        existente.setNome(dados.getNome());
        existente.setCargaHoraria(dados.getCargaHoraria());
        existente.setAtivo(dados.isAtivo());
        return repository.save(existente);
    }

    public void desativar(Long id) {
        Curso existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso nao encontrada: " + id));
        existente.setAtivo(false);
        repository.save(existente);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
