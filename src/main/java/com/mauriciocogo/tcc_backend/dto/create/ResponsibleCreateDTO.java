package com.mauriciocogo.tcc_backend.dto.create;

import com.mauriciocogo.tcc_backend.entity.Responsible;

public record ResponsibleCreateDTO(
        String name,
        String email,
        String role
) {
    public static Responsible toEntity(ResponsibleCreateDTO dto) {
        Responsible responsible = new Responsible();
        responsible.setName(dto.name());
        responsible.setEmail(dto.email());
        responsible.setRole(dto.role());
        responsible.setDeleted(false);
        return responsible;
    }
}
