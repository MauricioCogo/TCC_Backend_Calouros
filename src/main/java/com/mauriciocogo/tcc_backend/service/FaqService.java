package com.mauriciocogo.tcc_backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mauriciocogo.tcc_backend.dto.create.FaqCreateDTO;
import com.mauriciocogo.tcc_backend.dto.response.FaqResponseDTO;
import com.mauriciocogo.tcc_backend.dto.response.InformationResponseDTO;
import com.mauriciocogo.tcc_backend.entity.Faq;
import com.mauriciocogo.tcc_backend.entity.Information;
import com.mauriciocogo.tcc_backend.repository.FaqRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FaqService {
    
    private final FaqRepository faqRepository;
    private final InformationService informationService;

    public FaqService(FaqRepository faqRepository, InformationService informationService) {
        this.faqRepository = faqRepository;
        this.informationService = informationService;
    }

    public FaqResponseDTO createFaq(FaqCreateDTO dto) {
        Faq faq = FaqCreateDTO.toEntity(dto);
        Information information = InformationResponseDTO.toEntity(informationService.getInformationById(dto.informationId()));
        faq.setInformation(information);
        faq = faqRepository.save(faq);
        return FaqResponseDTO.toDTO(faq);
    }

    public FaqResponseDTO getFaqById(Long id) {
        Faq faq = faqRepository.findById(id).orElseThrow(() -> new RuntimeException("FAQ not found: " + id));
        return FaqResponseDTO.toDTO(faq);
    }

    public List<FaqResponseDTO> getAllFaqs() {
        return faqRepository.findAll()
                .stream()
                .map(FaqResponseDTO::toDTO)
                .toList();
    }

    public FaqResponseDTO updateFaq(Long id, FaqCreateDTO dto) {
        Faq faq = faqRepository.findById(id).orElseThrow(() -> new RuntimeException("FAQ not found: " + id));
        faq.setTitle(dto.title());
        faq.setDescription(dto.description());
        faq.setType(dto.type());
        Information information = InformationResponseDTO.toEntity(informationService.getInformationById(dto.informationId()));
        faq.setInformation(information);
        faq.setUpdatedAt(LocalDateTime.now());
        faq = faqRepository.save(faq);
        return FaqResponseDTO.toDTO(faq);
    }

    public void deleteFaq(Long id) {
        Faq faq = faqRepository.findById(id).orElseThrow(() -> new RuntimeException("FAQ not found: " + id));
        faq.setDeleted(true);
        faq.setDeletedAt(LocalDateTime.now());
        faqRepository.save(faq);
    }
}
