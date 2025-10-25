package com.mauriciocogo.tcc_backend.dto.response;

import com.mauriciocogo.tcc_backend.entity.Faq;

public record FaqResponseDTO (
    Long id,
    String title,
    String description,
    String type,
    InformationResponseDTO information
){
    public static FaqResponseDTO toDTO(Faq fac) {
        return new FaqResponseDTO(
            fac.getId(),
            fac.getTitle(),
            fac.getDescription(),
            fac.getType(),
            InformationResponseDTO.toDTO(fac.getInformation())
        );
    }

    public static Faq toEntity(FaqResponseDTO dto) {
        if (dto == null) {
            return null;
        }

        Faq faq = new Faq();
        faq.setId(dto.id());
        faq.setTitle(dto.title());
        faq.setDescription(dto.description());
        faq.setType(dto.type());
        faq.setInformation(InformationResponseDTO.toEntity(dto.information()));

        return faq;
    }
}
