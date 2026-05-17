package com.exemplo.pessoaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * =====================================================
 * MICROSERVICO: pessoa-service
 * =====================================================
 * Servico independente responsavel apenas pelo gerenciamento de pessoas.
 *
 * Para executar:
 *   mvn spring-boot:run
 *
 * Acesso:
 *   API:        http://localhost:8082/api/pessoas
 *   H2 Console: http://localhost:8082/h2-console
 * =====================================================
 */
@SpringBootApplication
public class PessoaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PessoaServiceApplication.class, args);
    }
}
