package com.mauriciocogo.tcc_backend.dto.response;

import com.mauriciocogo.tcc_backend.entity.Information;

public record InformationResponseDTO(
        Long id,
        String title,
        String description,
        String type,
        Long sectorId) {

    public static InformationResponseDTO toDTO(Information information) {
        return new InformationResponseDTO(
                information.getId(),
                information.getTitle(),
                information.getDescription(),
                information.getType(),
                information.getSector().getId());
    }

    public static Information toEntity(InformationResponseDTO dto) {
        if (dto == null) {
            return null;
        }

        Information information = new Information();
        information.setId(dto.id());
        information.setTitle(dto.title());
        information.setDescription(dto.description());
        information.setType(dto.type());

        return information;
    }
}
