package com.mauriciocogo.tcc_backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mauriciocogo.tcc_backend.dto.create.ResponsibleCreateDTO;
import com.mauriciocogo.tcc_backend.dto.response.ResponsibleResponseDTO;
import com.mauriciocogo.tcc_backend.entity.Responsible;
import com.mauriciocogo.tcc_backend.repository.ResponsibleRepository;

@Service
@Transactional
public class ResponsibleService {

    private final ResponsibleRepository responsibleRepository;

    public ResponsibleService(ResponsibleRepository responsibleRepository) {
        this.responsibleRepository = responsibleRepository;
    }

    public ResponsibleResponseDTO createResponsible(ResponsibleCreateDTO dto) {
        Responsible responsible = ResponsibleCreateDTO.toEntity(dto);
        Responsible savedResponsible = responsibleRepository.save(responsible);
        return ResponsibleResponseDTO.toDTO(savedResponsible);
    }

    public List<ResponsibleResponseDTO> getAllResponsibles() {
        return responsibleRepository.findAll()
                .stream()
                .map(ResponsibleResponseDTO::toDTO)
                .collect(Collectors.toList());
    }

    public ResponsibleResponseDTO getResponsibleById(Long id) {
        Responsible responsible = responsibleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Responsible not found with id " + id));
        return ResponsibleResponseDTO.toDTO(responsible);
    }


    public ResponsibleResponseDTO getResponsibleByEmail(String email) {
        Responsible responsible = responsibleRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Responsible not found with email " + email));
        return ResponsibleResponseDTO.toDTO(responsible);
    }

    public ResponsibleResponseDTO updateResponsible(Long id, ResponsibleCreateDTO dto) {
        Responsible responsible = responsibleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Responsible not found with id " + id));

        responsible.setName(dto.name());
        responsible.setEmail(dto.email());
        responsible.setRole(dto.role());
        responsible.setUpdatedAt(LocalDateTime.now());

        responsibleRepository.save(responsible);
        return ResponsibleResponseDTO.toDTO(responsible);
    }

    public void deleteResponsible(Long id) {
        Responsible responsible = responsibleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Responsible not found with id " + id));
        responsible.setDeleted(true);
        responsible.setDeletedAt(LocalDateTime.now());
        responsibleRepository.save(responsible);
    }
}
