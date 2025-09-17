package com.mauriciocogo.tcc_backend.dto.response;

import java.time.LocalDateTime;

import com.mauriciocogo.tcc_backend.entity.User;

public record UserResponseDTO(
        Long id,
        String nome,
        String cpf,
        String email,
        Boolean deleted,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt
) {
    public static UserResponseDTO toDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getNome(),
                user.getCpf(),
                user.getEmail(),
                user.getDeleted(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getDeletedAt()
        );
    }
}
