package com.rmg.springbootwebflux.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/parser")
public class HealthParserController {

    private final WebClient webClient;

    @Value("${huggingface.api.token}")
    private String huggingfaceToken;

    public HealthParserController(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("https://api-inference.huggingface.co/models/google/flan-t5-xl")
                .build();
    }

    @PostMapping(consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<String> parseInput(@RequestBody String inputText) {
        String prompt = """
            Analiza este texto escrito en español y extrae la información relevante de salud. Devuelve un JSON con las siguientes categorías si están disponibles: 
            - sueno.total
            - sueno.profundo
            - medicamentos (nombre, dosis)
            - presion (sistolica, diastolica)
            - ejercicio (distancia, frecuenciaCardiacaPromedio)
            - meditacion (duracion)
            - observaciones generales

            Texto del usuario:
            %s

            Respuesta:
            """.formatted(inputText);

        String jsonRequest = String.format("{\"inputs\": \"%s\"}", prompt.replace("\"", "\\\""));

        return webClient.post()
                .header("Authorization", "Bearer " + huggingfaceToken)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(jsonRequest)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(e -> {
                    e.printStackTrace();
                    return Mono.just("{\"error\":\"No se pudo procesar la respuesta del modelo.\"}");
                });
    }
}
