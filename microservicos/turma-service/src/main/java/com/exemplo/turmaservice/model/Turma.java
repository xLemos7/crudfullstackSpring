package com.exemplo.turmaservice.model;

import jakarta.persistence.*;

/**
 * Entidade Turma pertence exclusivamente a este microservico.
 */
@Entity
@Table(name = "turma")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private int ano;
    private Long cursoId;
    private Long disciplinaId;
    private Long professorId;
    private boolean ativo;

    public Turma() {}

    public Turma(String nome, int ano, Long cursoId, Long disciplinaId, Long professorId, boolean ativo) {
        this.nome = nome;
        this.ano = ano;
        this.cursoId = cursoId;
        this.disciplinaId = disciplinaId;
        this.professorId = professorId;
        this.ativo = ativo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }
    public Long getCursoId() { return cursoId; }
    public void setCursoId(Long cursoId) { this.cursoId = cursoId; }
    public Long getDisciplinaId() { return disciplinaId; }
    public void setDisciplinaId(Long disciplinaId) { this.disciplinaId = disciplinaId; }
    public Long getProfessorId() { return professorId; }
    public void setProfessorId(Long professorId) { this.professorId = professorId; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}
