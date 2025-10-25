package com.mauriciocogo.tcc_backend.dto.response;

import com.mauriciocogo.tcc_backend.entity.User;

public record UserResponseDTO(
        Long id,
        String name,
        String cpf,
        String email) {

    public static UserResponseDTO toDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getCpf(),
                user.getEmail());
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

        return user;
    }

}
