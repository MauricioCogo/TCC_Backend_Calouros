package com.mauriciocogo.tcc_backend.dto.create;

import com.mauriciocogo.tcc_backend.entity.User;

public record UserCreateDTO(
        String name,
        String cpf,
        String email,
        String password
) {
    public static User toEntity(UserCreateDTO dto) {
        User user = new User();
        user.setName(dto.name());
        user.setCpf(dto.cpf());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        user.setDeleted(false);
        return user;
    }
}
