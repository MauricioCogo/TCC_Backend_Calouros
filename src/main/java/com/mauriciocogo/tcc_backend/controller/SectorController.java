package com.mauriciocogo.tcc_backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mauriciocogo.tcc_backend.dto.create.SectorCreateDTO;
import com.mauriciocogo.tcc_backend.dto.response.SectorResponseDTO;
import com.mauriciocogo.tcc_backend.service.SectorService;

@RestController
@RequestMapping("/sectors")
public class SectorController {
    
    private final SectorService sectorService;

    public SectorController(SectorService sectorService){
        this.sectorService = sectorService;
    }

    @PostMapping
    public SectorResponseDTO createSector(SectorCreateDTO dto){
        return sectorService.createSector(dto);
    }

    @GetMapping
    public List<SectorResponseDTO> getAllSectors(){
        return sectorService.getAllSectors();
    }

    @GetMapping("/{id}")
    public SectorResponseDTO getSectorById(Long id){
        return sectorService.getSectorById(id);
    }

    @PutMapping("/{id}")
    public SectorResponseDTO updateSector(Long id, SectorCreateDTO dto){
        return sectorService.updateSector(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteSector(Long id){
        sectorService.deleteSector(id);
    }
}
