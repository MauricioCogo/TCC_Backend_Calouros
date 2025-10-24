package com.mauriciocogo.tcc_backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mauriciocogo.tcc_backend.dto.create.SectorCreateDTO;
import com.mauriciocogo.tcc_backend.dto.response.SectorResponseDTO;
import com.mauriciocogo.tcc_backend.entity.Sector;
import com.mauriciocogo.tcc_backend.repository.SectorRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SectorService {
    private final SectorRepository sectorRepository;

    public SectorService(SectorRepository sectorRepository){
        this.sectorRepository = sectorRepository;
    }

    public SectorResponseDTO createSector(SectorCreateDTO dto){
        Sector sector = SectorCreateDTO.toEntity(dto);
        Sector savedSector = sectorRepository.save(sector);
        return SectorResponseDTO.toDTO(savedSector);
    }

    public List<SectorResponseDTO> getAllSectors(){
        return sectorRepository.findAll()
            .stream()
            .map(SectorResponseDTO::toDTO)
            .collect(Collectors.toList());
    }

    public SectorResponseDTO getSectorById(Long id){
        Sector sector = sectorRepository.findById(id).orElseThrow(() -> new RuntimeException("Sector not found" + id));
        return SectorResponseDTO.toDTO(sector);
    }
    
}
