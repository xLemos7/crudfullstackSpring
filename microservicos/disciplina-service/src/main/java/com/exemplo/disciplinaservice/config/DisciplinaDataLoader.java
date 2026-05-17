package com.exemplo.disciplinaservice.config;

import com.exemplo.disciplinaservice.model.Disciplina;
import com.exemplo.disciplinaservice.repository.DisciplinaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Carrega dados iniciais no banco H2 ao subir o microservico.
 */
@Configuration
public class DisciplinaDataLoader {

    @Bean
    CommandLineRunner carregarDados(DisciplinaRepository repository) {
        return args -> {
            repository.save(new Disciplina("Programacao Web", 80, 1L, true));
            repository.save(new Disciplina("Banco de Dados", 80, 1L, true));
            repository.save(new Disciplina("Arquitetura de Redes", 60, 3L, false));
            System.out.println("[disciplina-service] Dados iniciais carregados.");
        };
    }
}
