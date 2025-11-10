package com.mauriciocogo.tcc_backend.dto;

import lombok.Data;

@Data
public class WeatherDTO {
    private Integer id;
    private String main;
    private String description;
    private String icon;

    private Double temp;
    private Double feels_like;
    private Double temp_min;
    private Double temp_max;
    private Integer humidity;
}
