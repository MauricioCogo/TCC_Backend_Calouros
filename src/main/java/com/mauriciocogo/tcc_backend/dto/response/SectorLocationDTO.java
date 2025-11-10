package com.mauriciocogo.tcc_backend.dto.response;

import com.mauriciocogo.tcc_backend.entity.Sector;

public record SectorLocationDTO(
        Long id,
        String acronym,
        String name,
        String lat,
        String longi,
        String build,
        String room) {

    public static SectorLocationDTO toDTO(Sector sector) {
        if (sector == null) {
            return null;
        }

        return new SectorLocationDTO(
                sector.getId(),
                sector.getAcronym(),
                sector.getName(),
                sector.getLat(),
                sector.getLongi(),
                sector.getBuild(),
                sector.getRoom());
    }

    public static Sector toEntity(SectorLocationDTO dto) {
        if (dto == null) {
            return null;
        }

        Sector sector = new Sector();
        sector.setId(dto.id());
        sector.setAcronym(dto.acronym());
        sector.setName(dto.name());
        sector.setLat(dto.lat());
        sector.setLongi(dto.longi());
        sector.setBuild(dto.build());
        sector.setRoom(dto.room());

        return sector;
    }
}
