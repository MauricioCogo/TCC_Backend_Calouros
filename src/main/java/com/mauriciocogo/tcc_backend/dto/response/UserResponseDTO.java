package com.mauriciocogo.tcc_backend.dto.response;

import java.time.LocalDateTime;

import com.mauriciocogo.tcc_backend.entity.User;

public record UserResponseDTO(
        Long id,
        String name,
        String cpf,
        String email,
        Boolean deleted,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt) {

    public static UserResponseDTO toDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getCpf(),
                user.getEmail(),
                user.getDeleted(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getDeletedAt());
    }

    public static User toEntity(UserResponseDTO dto) {
        if (dto == null) {
            return null;
        }

        User user = new User();
        user.setId(dto.id());
        user.setName(dto.name());
        user.setCpf(dto.cpf());
        user.setEmail(dto.email());
        user.setDeleted(dto.deleted());
        user.setCreatedAt(dto.createdAt());
        user.setUpdatedAt(dto.updatedAt());
        user.setDeletedAt(dto.deletedAt());

        return user;
    }

}
