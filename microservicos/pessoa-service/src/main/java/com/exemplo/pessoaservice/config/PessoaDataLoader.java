package com.exemplo.pessoaservice.config;

import com.exemplo.pessoaservice.model.Pessoa;
import com.exemplo.pessoaservice.repository.PessoaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Carrega dados iniciais no banco H2 ao subir o microservico.
 */
@Configuration
public class PessoaDataLoader {

    @Bean
    CommandLineRunner carregarDados(PessoaRepository repository) {
        return args -> {
            repository.save(new Pessoa("Ana Silva", 22, "ana.silva@email.com", true));
            repository.save(new Pessoa("Bruno Souza", 27, "bruno.souza@email.com", true));
            repository.save(new Pessoa("Carla Lima", 31, "carla.lima@email.com", false));
            System.out.println("[pessoa-service] Dados iniciais carregados.");
        };
    }
}
