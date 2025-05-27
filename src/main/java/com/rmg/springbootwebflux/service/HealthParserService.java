package com.rmg.springbootwebflux.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class HealthParserService {

    @Value("${huggingface.api.token}")
    private String huggingFaceToken;

    private final ObjectMapper objectMapper;
    private WebClient webClient;

    public HealthParserService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void init() {
        this.webClient = WebClient.builder()
                .baseUrl("https://api-inference.huggingface.co/models/deepseek-ai/DeepSeek-R1-Distill-Qwen-32B")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + huggingFaceToken)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public Mono<ArrayNode> callHuggingFaceModel(String inputText) {
        ObjectNode body = objectMapper.createObjectNode();
        body.put("inputs", generatePrompt(inputText));

        return webClient.post()
                .bodyValue(body)
                .retrieve()
                .bodyToMono(ArrayNode.class);
    }

    private String generatePrompt(String textoUsuario) {
        return """
        Analiza este texto escrito en español y extrae la información relevante de salud. 
        Devuelve un JSON con las siguientes categorías si están disponibles: 
        - sueno.total 
        - sueno.profundo 
        - medicamentos (nombre, dosis) 
        - presion (sistolica, diastolica) 
        - ejercicio (distancia, frecuenciaCardiacaPromedio) 
        - meditacion (duracion) 
        - observaciones generales 

        Texto del usuario: %s
        Solo responde con el JSON.
        """.formatted(textoUsuario);
    }
}