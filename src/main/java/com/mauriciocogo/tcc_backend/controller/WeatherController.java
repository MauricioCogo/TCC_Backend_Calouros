package com.mauriciocogo.tcc_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mauriciocogo.tcc_backend.dto.WeatherDTO;
import com.mauriciocogo.tcc_backend.service.WeatherService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import reactor.core.publisher.Mono;

@RestController
@Tag(
    name = "Clima",
    description = "Consulta meteorológica utilizando a API OpenWeatherMap."
)
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/clima")
    @Operation(
        summary = "Consultar clima por coordenadas",
        description = "Retorna temperatura, condição do céu e outros dados climáticos usando latitude e longitude.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Clima retornado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos")
        }
    )
    public Mono<WeatherDTO> getWeather(
            @RequestParam double lat,
            @RequestParam double lon) {
        return weatherService.getWeatherByCoordinates(lat, lon);
    }

    @GetMapping("/clima/svs")
    @Operation(
        summary = "Consultar clima do Campus SVS",
        description = "Retorna automaticamente o clima usando as coordenadas oficiais do campus.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Clima retornado com sucesso")
        }
    )
    public Mono<WeatherDTO> getWeatherSaoVicenteDoSul() {
        return weatherService.getWeatherSaoVicenteDoSul();
    }
}
