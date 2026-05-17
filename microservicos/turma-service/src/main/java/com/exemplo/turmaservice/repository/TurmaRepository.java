package com.exemplo.turmaservice.repository;

import com.exemplo.turmaservice.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio JPA do microservico de turmas.
 */
@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {

    List<Turma> findByAtivo(boolean ativo);

    List<Turma> findByNomeContainingIgnoreCase(String nome);
}
