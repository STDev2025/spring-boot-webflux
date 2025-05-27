package com.rmg.springbootwebflux.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmg.springbootwebflux.models.documents.HealthDataDto;
import com.rmg.springbootwebflux.service.HealthParserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/parser")
public class HealthParserController {

    private final HealthParserService parserService;
    private final ObjectMapper objectMapper;

    public HealthParserController(HealthParserService parserService, ObjectMapper objectMapper) {
        this.parserService = parserService;
        this.objectMapper = objectMapper;
    }

    @PostMapping(consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> parseText(@RequestBody String inputText) {
        return parserService.callHuggingFaceModel(inputText)
                .flatMap(arrayNode -> {
                    try {
                        if (arrayNode.isEmpty()) {
                            return Mono.just(ResponseEntity.badRequest().body("Respuesta vacía del modelo."));
                        }

                        String generatedText = arrayNode.get(0).get("generated_text").asText();

                        // Buscar el JSON dentro del texto generado
                        int start = generatedText.indexOf('{');
                        int end = generatedText.lastIndexOf('}') + 1;

                        if (start >= 0 && end > start) {
                            String jsonString = generatedText.substring(start, end);
                            HealthDataDto dto = objectMapper.readValue(jsonString, HealthDataDto.class);
                            return Mono.just(ResponseEntity.ok(dto));
                        } else {
                            return Mono.just(ResponseEntity.badRequest().body("No se pudo encontrar un JSON válido en la respuesta."));
                        }

                    } catch (Exception e) {
                        return Mono.just(ResponseEntity.internalServerError().body("Error al parsear JSON: " + e.getMessage()));
                    }
                })
                .onErrorResume(e -> {
                    e.printStackTrace();
                    return Mono.just(ResponseEntity.internalServerError().body("Error en Hugging Face: " + e.getMessage()));
                });
    }
}
