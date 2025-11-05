package com.mauriciocogo.tcc_backend.dto.response;

import com.mauriciocogo.tcc_backend.entity.Responsible;

public record ResponsibleResponseDTO(
        Long id,
        String name,
        String email,
        String role) {

    public static ResponsibleResponseDTO toDTO(Responsible responsible) {
        return new ResponsibleResponseDTO(
                responsible.getId(),
                responsible.getName(),
                responsible.getEmail(),
                responsible.getRole());
    }

    public static Responsible toEntity(ResponsibleResponseDTO dto) {
        if (dto == null) {
            return null;
        }

        Responsible responsible = new Responsible();
        responsible.setId(dto.id());
        responsible.setName(dto.name());
        responsible.setEmail(dto.email());
        responsible.setRole(dto.role());

        return responsible;
    }

}
