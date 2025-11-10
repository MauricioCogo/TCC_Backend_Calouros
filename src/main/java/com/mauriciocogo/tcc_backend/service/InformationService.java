package com.mauriciocogo.tcc_backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mauriciocogo.tcc_backend.dto.create.InformationCreateDTO;
import com.mauriciocogo.tcc_backend.dto.response.InformationResponseDTO;
import com.mauriciocogo.tcc_backend.dto.response.SectorResponseDTO;
import com.mauriciocogo.tcc_backend.entity.Information;
import com.mauriciocogo.tcc_backend.entity.Sector;
import com.mauriciocogo.tcc_backend.repository.InformationRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class InformationService {
    
    private final InformationRepository informationRepository;
    private final SectorService sectorService;

    public InformationService(InformationRepository informationRepository, SectorService sectorService) {
        this.informationRepository = informationRepository;
        this.sectorService = sectorService;
    }

    public InformationResponseDTO createInformation(InformationCreateDTO dto) {
        Information information = InformationCreateDTO.toEntity(dto);
        Sector sector = SectorResponseDTO.toEntity(sectorService.getSectorById(dto.sectorId()));
        information.setSector(sector);
        Information savedInformation = informationRepository.save(information);
        return InformationResponseDTO.toDTO(savedInformation);
    }

    public InformationResponseDTO getInformationById(Long id) {
        Information information = informationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Information not found with id " + id));
        return InformationResponseDTO.toDTO(information);
    }

    public List<InformationResponseDTO> getAllInformations() {
        return informationRepository.findAll()
                .stream()
                .map(InformationResponseDTO::toDTO)
                .toList();
    }

        public List<InformationResponseDTO> search(String keyword) {
        return informationRepository.searchByAcronymOrName(keyword)
                .stream()
                .map(InformationResponseDTO::toDTO)
                .collect(Collectors.toList());
    }

    public InformationResponseDTO updateInformation(Long id, InformationCreateDTO dto) {
        Information information = informationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Information not found with id " + id));

        information.setTitle(dto.title());
        information.setDescription(dto.description());
        information.setType(dto.type());
        information.setUpdatedAt(LocalDateTime.now());

        informationRepository.save(information);
        return InformationResponseDTO.toDTO(information);
    }

    public void deleteInformation(Long id) {
        Information information = InformationResponseDTO.toEntity(getInformationById(id));
        information.setDeleted(true);
        information.setDeletedAt(LocalDateTime.now());
        informationRepository.save(information);
    }
}
