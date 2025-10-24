package com.mauriciocogo.tcc_backend.dto.response;

import java.time.LocalDateTime;

import com.mauriciocogo.tcc_backend.entity.Sector;

public record SectorResponseDTO(
        Long id,
        String acronym,
        String name,
        String description,
        String lat,
        String longi,
        String build,
        String room,
        UserResponseDTO user,
        Boolean deleted,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt) {

    public static SectorResponseDTO toDTO(Sector sector) {
        if (sector == null) {
            return null;
        }

        return new SectorResponseDTO(
                sector.getId(),
                sector.getAcronym(),
                sector.getName(),
                sector.getDescription(),
                sector.getLat(),
                sector.getLongi(),
                sector.getBuild(),
                sector.getRoom(),
                UserResponseDTO.toDTO(sector.getUser()),
                sector.getDeleted(),
                sector.getCreatedAt(),
                sector.getUpdatedAt(),
                sector.getDeletedAt());
    }

    public static Sector toEntity(SectorResponseDTO dto) {
        if (dto == null) {
            return null;
        }

        Sector sector = new Sector();
        sector.setId(dto.id());
        sector.setAcronym(dto.acronym());
        sector.setName(dto.name());
        sector.setDescription(dto.description());
        sector.setLat(dto.lat());
        sector.setLongi(dto.longi());
        sector.setBuild(dto.build());
        sector.setRoom(dto.room());
        sector.setUser(UserResponseDTO.toEntity(dto.user()));
        sector.setDeleted(dto.deleted());
        sector.setCreatedAt(dto.createdAt());
        sector.setUpdatedAt(dto.updatedAt());
        sector.setDeletedAt(dto.deletedAt());

        return sector;
    }
}
