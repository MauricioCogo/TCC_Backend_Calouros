package com.mauriciocogo.tcc_backend.dto.create;

import com.mauriciocogo.tcc_backend.entity.Faq;

public record FaqCreateDTO(
        String title,
        String description,
        String type,
        Long informationId
) {
    public static Faq toEntity(FaqCreateDTO dto) {
        Faq faq = new Faq();
        faq.setTitle(dto.title());
        faq.setDescription(dto.description());
        faq.setType(dto.type());
        return faq;
    }
}
