package com.mauriciocogo.tcc_backend.dto.create;

import com.mauriciocogo.tcc_backend.entity.User;

public record UserCreateDTO(
        String nome,
        String cpf,
        String email,
        String senha
) {
    public static User toEntity(UserCreateDTO dto) {
        User user = new User();
        user.setNome(dto.nome());
        user.setCpf(dto.cpf());
        user.setEmail(dto.email());
        user.setSenha(dto.senha());
        user.setDeleted(false);
        return user;
    }
}
