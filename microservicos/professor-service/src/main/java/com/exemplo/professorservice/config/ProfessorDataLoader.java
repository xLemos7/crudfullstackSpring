package com.exemplo.professorservice.config;

import com.exemplo.professorservice.model.Professor;
import com.exemplo.professorservice.repository.ProfessorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Carrega dados iniciais no banco H2 ao subir o microservico.
 */
@Configuration
public class ProfessorDataLoader {

    @Bean
    CommandLineRunner carregarDados(ProfessorRepository repository) {
        return args -> {
            repository.save(new Professor("Marcos Pereira", 42, "marcos.pereira@email.com", "Programacao", true));
            repository.save(new Professor("Juliana Rocha", 38, "juliana.rocha@email.com", "Banco de Dados", true));
            repository.save(new Professor("Renato Alves", 47, "renato.alves@email.com", "Redes", false));
            System.out.println("[professor-service] Dados iniciais carregados.");
        };
    }
}
