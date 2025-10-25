package com.mauriciocogo.tcc_backend.dto.create;

import com.mauriciocogo.tcc_backend.dto.response.UserResponseDTO;
import com.mauriciocogo.tcc_backend.entity.Sector;
import com.mauriciocogo.tcc_backend.service.UserService;

public record SectorCreateDTO(
    Long id,
    String acromyn,
    String name,
    String desc,
    String lat,
    String longi,
    String build,
    String room,
    String userCPF
){
    public static Sector toEntity(SectorCreateDTO dto){
        Sector s = new Sector();
        s.setAcronym(dto.acromyn);
        s.setName(dto.name);
        s.setDescription(dto.desc);
        s.setLat(dto.lat);
        s.setLongi(dto.longi);
        s.setBuild(dto.build);
        s.setRoom(dto.room);
        return s;
    }
}
