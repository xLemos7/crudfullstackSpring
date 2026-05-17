package com.exemplo.turmaservice.config;

import com.exemplo.turmaservice.model.Turma;
import com.exemplo.turmaservice.repository.TurmaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Carrega dados iniciais no banco H2 ao subir o microservico.
 */
@Configuration
public class TurmaDataLoader {

    @Bean
    CommandLineRunner carregarDados(TurmaRepository repository) {
        return args -> {
            repository.save(new Turma("ADS 2024 A", 2024, 2L, 1L, 1L, true));
            repository.save(new Turma("SI 2024 B", 2024, 1L, 2L, 2L, true));
            repository.save(new Turma("Redes 2023 A", 2023, 3L, 3L, 3L, false));
            System.out.println("[turma-service] Dados iniciais carregados.");
        };
    }
}
