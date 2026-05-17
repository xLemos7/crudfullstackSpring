package com.exemplo.turmaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * =====================================================
 * MICROSERVICO: turma-service
 * =====================================================
 * Servico independente responsavel apenas pelo gerenciamento de turmas.
 *
 * Para executar:
 *   mvn spring-boot:run
 *
 * Acesso:
 *   API:        http://localhost:8086/api/turmas
 *   H2 Console: http://localhost:8086/h2-console
 * =====================================================
 */
@SpringBootApplication
public class TurmaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TurmaServiceApplication.class, args);
    }
}
