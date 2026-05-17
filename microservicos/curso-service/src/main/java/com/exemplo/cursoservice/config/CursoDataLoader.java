package com.exemplo.cursoservice.config;

import com.exemplo.cursoservice.model.Curso;
import com.exemplo.cursoservice.repository.CursoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Carrega dados iniciais no banco H2 ao subir o microservico.
 */
@Configuration
public class CursoDataLoader {

    @Bean
    CommandLineRunner carregarDados(CursoRepository repository) {
        return args -> {
            repository.save(new Curso("Sistemas de Informacao", 3200, true));
            repository.save(new Curso("Analise e Desenvolvimento de Sistemas", 2400, true));
            repository.save(new Curso("Redes de Computadores", 2200, false));
            System.out.println("[curso-service] Dados iniciais carregados.");
        };
    }
}
