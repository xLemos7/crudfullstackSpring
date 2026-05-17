package com.exemplo.professorservice.service;

import com.exemplo.professorservice.model.Professor;
import com.exemplo.professorservice.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Camada de negocio do microservico de professores.
 */
@Service
public class ProfessorService {


    private final ProfessorRepository repository;

    public ProfessorService(ProfessorRepository repository) {
        this.repository = repository;
    }

    public List<Professor> listarTodas() {
        return repository.findAll();
    }

    public Optional<Professor> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public List<Professor> listarAtivas() {
        return repository.findByAtivo(true);
    }

    public List<Professor> listarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    public Professor salvar(Professor professor) {
        return repository.save(professor);
    }

    public Professor atualizar(Long id, Professor dados) {
        Professor existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor nao encontrada: " + id));
        existente.setNome(dados.getNome());
        existente.setIdade(dados.getIdade());
        existente.setEmail(dados.getEmail());
        existente.setArea(dados.getArea());
        existente.setAtivo(dados.isAtivo());
        return repository.save(existente);
    }

    public void desativar(Long id) {
        Professor existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor nao encontrada: " + id));
        existente.setAtivo(false);
        repository.save(existente);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
