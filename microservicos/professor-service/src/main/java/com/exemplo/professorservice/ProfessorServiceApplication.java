package com.exemplo.professorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * =====================================================
 * MICROSERVICO: professor-service
 * =====================================================
 * Servico independente responsavel apenas pelo gerenciamento de professores.
 *
 * Para executar:
 *   mvn spring-boot:run
 *
 * Acesso:
 *   API:        http://localhost:8085/api/professores
 *   H2 Console: http://localhost:8085/h2-console
 * =====================================================
 */
@SpringBootApplication
public class ProfessorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProfessorServiceApplication.class, args);
    }
}
