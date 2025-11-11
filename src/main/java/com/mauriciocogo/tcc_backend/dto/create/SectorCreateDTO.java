package com.mauriciocogo.tcc_backend.dto.create;

import com.mauriciocogo.tcc_backend.dto.OperatingHours;
import com.mauriciocogo.tcc_backend.entity.Sector;

public record SectorCreateDTO(
    Long id,
    String acronym,
    String name,
    String description,
    String lat,
    String longi,
    String build,
    String room,
    Long responsibleId,
    OperatingHours operatingHours
){
    public static Sector toEntity(SectorCreateDTO dto){
        Sector s = new Sector();
        s.setAcronym(dto.acronym);
        s.setName(dto.name);
        s.setDescription(dto.description);
        s.setLat(dto.lat);
        s.setLongi(dto.longi);
        s.setBuild(dto.build);
        s.setRoom(dto.room);
        s.setOperatingHours(dto.operatingHours());
        return s;
    }
}
