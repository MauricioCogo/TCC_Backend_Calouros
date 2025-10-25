package com.mauriciocogo.tcc_backend.dto.create;

import com.mauriciocogo.tcc_backend.entity.Information;

public record InformationCreateDTO (
    String title,
    String description,
    String type,
    Long sectorId) {

        public static Information toEntity(InformationCreateDTO dto) {
            Information information = new Information();
            information.setTitle(dto.title());
            information.setDescription(dto.description());
            information.setType(dto.type());
            return information;
        }
}
