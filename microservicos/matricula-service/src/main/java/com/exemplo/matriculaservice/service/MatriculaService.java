package com.exemplo.matriculaservice.service;

import com.exemplo.matriculaservice.dto.MatriculaDetalhadaDTO;
import com.exemplo.matriculaservice.model.Matricula;
import com.exemplo.matriculaservice.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Camada de negócio do microserviço de Matrículas.
 * Toda a lógica de negócio fica aqui; o controller só delega.
 */
@Service
public class MatriculaService {

    private final MatriculaRepository repository;
    private final RestTemplate restTemplate;

    @Value("${services.pessoa.url:http://localhost:8082/api/pessoas}")
    private String pessoaServiceUrl;

    @Value("${services.curso.url:http://localhost:8083/api/cursos}")
    private String cursoServiceUrl;

    public MatriculaService(MatriculaRepository repository, RestTemplateBuilder restTemplateBuilder) {
        this.repository = repository;
        this.restTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(2))
                .setReadTimeout(Duration.ofSeconds(2))
                .build();
    }

    /** Lista todas as matrículas */
    public List<Matricula> listarTodas() {
        return repository.findAll();
    }

    /** Busca uma matrícula pelo ID */
    public Optional<Matricula> buscarPorId(Long id) {
        return repository.findById(id);
    }

    /** Busca uma matrícula pelo ID e enriquece com dados dos serviços de Pessoa e Curso */
    public Optional<MatriculaDetalhadaDTO> buscarDetalhadaPorId(Long id) {
        return repository.findById(id)
                .map(this::montarDetalhada);
    }

    /** Lista matrículas de uma pessoa específica */
    public List<Matricula> listarPorPessoa(Long pessoaId) {
        return repository.findByPessoaId(pessoaId);
    }

    /** Lista matrículas de um curso específico */
    public List<Matricula> listarPorCurso(Long cursoId) {
        return repository.findByCursoId(cursoId);
    }

    /** Cria uma nova matrícula */
    public Matricula salvar(Matricula matricula) {
        return repository.save(matricula);
    }

    /** Atualiza uma matrícula existente */
    public Matricula atualizar(Long id, Matricula dados) {
        Matricula existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada: " + id));
        existente.setPessoaId(dados.getPessoaId());
        existente.setCursoId(dados.getCursoId());
        existente.setDataMatricula(dados.getDataMatricula());
        existente.setAtivo(dados.isAtivo());
        return repository.save(existente);
    }

    /** Desativa uma matrícula (soft delete) */
    public void desativar(Long id) {
        Matricula existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada: " + id));
        existente.setAtivo(false);
        repository.save(existente);
    }

    /** Remove permanentemente uma matrícula */
    public void excluir(Long id) {
        repository.deleteById(id);
    }

    private MatriculaDetalhadaDTO montarDetalhada(Matricula matricula) {
        String nomePessoa = buscarNomePessoa(matricula.getPessoaId());
        String nomeCurso = buscarNome(cursoServiceUrl, matricula.getCursoId());

        return new MatriculaDetalhadaDTO(
                matricula.getId(),
                matricula.getPessoaId(),
                nomePessoa,
                matricula.getCursoId(),
                nomeCurso,
                matricula.getDataMatricula(),
                matricula.isAtivo()
        );
    }

    private String buscarNomePessoa(Long pessoaId) {
        if (pessoaId == null) {
            return null;
        }

        try {
            return buscarNomeSemFallback(pessoaServiceUrl, pessoaId);
        } catch (ResourceAccessException e) {
            return "indisponível";
        } catch (RestClientException e) {
            return null;
        }
    }

    private String buscarNome(String serviceUrl, Long id) {
        if (id == null) {
            return null;
        }

        try {
            return buscarNomeSemFallback(serviceUrl, id);
        } catch (RestClientException e) {
            return null;
        }
    }

    private String buscarNomeSemFallback(String serviceUrl, Long id) {
        Map<?, ?> resposta = restTemplate.getForObject(serviceUrl + "/" + id, Map.class);
        Object nome = resposta != null ? resposta.get("nome") : null;
        return nome != null ? nome.toString() : null;
    }
}
