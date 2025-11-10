package com.mauriciocogo.tcc_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mauriciocogo.tcc_backend.dto.WeatherDTO;
import com.mauriciocogo.tcc_backend.service.WeatherService;

import reactor.core.publisher.Mono;

@RestController
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/clima")
    public Mono<WeatherDTO> getWeather(
            @RequestParam double lat,
            @RequestParam double lon) {
        return weatherService.getWeatherByCoordinates(lat, lon);
    }

    @GetMapping("/clima/svs")
    public Mono<WeatherDTO> getWeatherSaoVicenteDoSul() {
        return weatherService.getWeatherSaoVicenteDoSul();
    }
}
