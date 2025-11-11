package com.mauriciocogo.tcc_backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.mauriciocogo.tcc_backend.dto.WeatherDTO;

import reactor.core.publisher.Mono;
import java.util.Map;
import java.util.List;

@Service
public class WeatherService {

    private final WebClient webClient;

    @Value("${openweather.api.key}")
    private String apiKey;

    public WeatherService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Mono<WeatherDTO> getWeatherByCoordinates(double lat, double lon) {
        String url = String.format(
                "https://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&appid=%s&units=metric&lang=pt_br",
                lat, lon, apiKey);

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    List<Map<String, Object>> weatherList = (List<Map<String, Object>>) response.get("weather");
                    if (weatherList == null || weatherList.isEmpty())
                        return null;

                    Map<String, Object> first = weatherList.get(0);
                    Map<String, Object> mainData = (Map<String, Object>) response.get("main");

                    WeatherDTO dto = new WeatherDTO();
                    dto.setId((Integer) first.get("id"));
                    dto.setMain((String) first.get("main"));
                    dto.setDescription((String) first.get("description"));
                    dto.setIcon((String) first.get("icon"));

                    if (mainData != null) {
                        dto.setTemp(getDouble(mainData.get("temp")));
                        dto.setFeels_like(getDouble(mainData.get("feels_like")));
                        dto.setTemp_min(getDouble(mainData.get("temp_min")));
                        dto.setTemp_max(getDouble(mainData.get("temp_max")));
                        dto.setHumidity(((Number) mainData.get("humidity")).intValue());
                    }

                    return dto;
                });
    }

    private Double getDouble(Object value) {
        if (value instanceof Number)
            return ((Number) value).doubleValue();
        return null;
    }

    public Mono<WeatherDTO> getWeatherSaoVicenteDoSul() {
        return getWeatherByCoordinates(-29.70210696117257, -54.6968503737989);
    }
}
