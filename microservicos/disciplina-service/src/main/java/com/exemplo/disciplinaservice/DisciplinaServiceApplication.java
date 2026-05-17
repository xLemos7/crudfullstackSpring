package com.exemplo.disciplinaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * =====================================================
 * MICROSERVICO: disciplina-service
 * =====================================================
 * Servico independente responsavel apenas pelo gerenciamento de disciplinas.
 *
 * Para executar:
 *   mvn spring-boot:run
 *
 * Acesso:
 *   API:        http://localhost:8084/api/disciplinas
 *   H2 Console: http://localhost:8084/h2-console
 * =====================================================
 */
@SpringBootApplication
public class DisciplinaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DisciplinaServiceApplication.class, args);
    }
}
