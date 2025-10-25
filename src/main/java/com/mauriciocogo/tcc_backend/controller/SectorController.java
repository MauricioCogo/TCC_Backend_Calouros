package com.mauriciocogo.tcc_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mauriciocogo.tcc_backend.dto.create.SectorCreateDTO;
import com.mauriciocogo.tcc_backend.dto.response.SectorResponseDTO;
import com.mauriciocogo.tcc_backend.service.SectorService;

@RestController
@RequestMapping("/sectors")
public class SectorController {
    
    private final SectorService sectorService;

    public SectorController(SectorService sectorService) {
        this.sectorService = sectorService;
    }

    @PostMapping
    public ResponseEntity<SectorResponseDTO> createSector(@RequestBody SectorCreateDTO dto) {
        SectorResponseDTO created = sectorService.createSector(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<SectorResponseDTO>> getAllSectors() {
        List<SectorResponseDTO> sectors = sectorService.getAllSectors();
        return ResponseEntity.ok(sectors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SectorResponseDTO> getSectorById(@PathVariable Long id) {
        SectorResponseDTO sector = sectorService.getSectorById(id);
        return ResponseEntity.ok(sector);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SectorResponseDTO> updateSector(
            @PathVariable Long id, 
            @RequestBody SectorCreateDTO dto) {
        SectorResponseDTO updated = sectorService.updateSector(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSector(@PathVariable Long id) {
        sectorService.deleteSector(id);
        return ResponseEntity.noContent().build();
    }
}
