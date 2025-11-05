package com.mauriciocogo.tcc_backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import com.mauriciocogo.tcc_backend.dto.create.SectorCreateDTO;
import com.mauriciocogo.tcc_backend.dto.response.SectorResponseDTO;
import com.mauriciocogo.tcc_backend.dto.response.ResponsibleResponseDTO;
import com.mauriciocogo.tcc_backend.entity.Sector;
import com.mauriciocogo.tcc_backend.entity.Responsible;
import com.mauriciocogo.tcc_backend.repository.SectorRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SectorService {
    private final SectorRepository sectorRepository;
    private final ResponsibleService responsibleService;

    public SectorService(SectorRepository sectorRepository, ResponsibleService responsibleService) {
        this.sectorRepository = sectorRepository;
        this.responsibleService = responsibleService;
    }

    public SectorResponseDTO createSector(SectorCreateDTO dto) {
        Sector sector = SectorCreateDTO.toEntity(dto);
        Responsible u = ResponsibleResponseDTO.toEntity(responsibleService.getResponsibleById(dto.responsibleId()));
        sector.setResponsible(u);
        System.out.println(dto.operatingHours() + "bueda fixe");
        sector.setOperatingHours(dto.operatingHours());
        Sector savedSector = sectorRepository.save(sector);
        return SectorResponseDTO.toDTO(savedSector);
    }

    public List<SectorResponseDTO> getAllSectors() {
        return sectorRepository.findAll()
                .stream()
                .map(SectorResponseDTO::toDTO)
                .collect(Collectors.toList());
    }

    public SectorResponseDTO getSectorById(Long id) {
        Sector sector = sectorRepository.findById(id).orElseThrow(() -> new RuntimeException("Sector not found" + id));
        return SectorResponseDTO.toDTO(sector);
    }

    public List<SectorResponseDTO> search(String keyword) {
        return sectorRepository.searchByAcronymOrName(keyword)
                .stream()
                .map(SectorResponseDTO::toDTO)
                .collect(Collectors.toList());
    }

    public SectorResponseDTO updateSector(Long id, SectorCreateDTO dto) {
        Sector sector = sectorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sector not found: " + id));

        sector.setAcronym(dto.acromyn());
        sector.setName(dto.name());
        sector.setDescription(dto.desc());
        sector.setLat(dto.lat());
        sector.setLongi(dto.longi());
        sector.setBuild(dto.build());
        sector.setRoom(dto.room());
        sector.setOperatingHours(dto.operatingHours());

        Responsible u = ResponsibleResponseDTO.toEntity(responsibleService.getResponsibleById(dto.responsibleId()));
        sector.setResponsible(u);
        sector.setUpdatedAt(LocalDateTime.now());

        Sector updatedSector = sectorRepository.save(sector);
        return SectorResponseDTO.toDTO(updatedSector);
    }

    public void deleteSector(Long id) {
        Sector sector = sectorRepository.findById(id).orElseThrow(() -> new RuntimeException("Sector not found" + id));
        sector.setDeleted(true);
        sector.setDeletedAt(LocalDateTime.now());
        sectorRepository.save(sector);
    }
}
