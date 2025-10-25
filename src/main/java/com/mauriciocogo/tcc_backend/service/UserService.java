package com.mauriciocogo.tcc_backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mauriciocogo.tcc_backend.dto.create.UserCreateDTO;
import com.mauriciocogo.tcc_backend.dto.response.UserResponseDTO;
import com.mauriciocogo.tcc_backend.entity.User;
import com.mauriciocogo.tcc_backend.repository.UserRepository;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO createUser(UserCreateDTO dto) {
        User user = UserCreateDTO.toEntity(dto);
        User savedUser = userRepository.save(user);
        return UserResponseDTO.toDTO(savedUser);
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDTO::toDTO)
                .collect(Collectors.toList());
    }

    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
        return UserResponseDTO.toDTO(user);
    }

    public UserResponseDTO getUserByCPF(String cpf) {
        User user = userRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("User not found with cpf " + cpf));
        return UserResponseDTO.toDTO(user);
    }

    public UserResponseDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email " + email));
        return UserResponseDTO.toDTO(user);
    }

    public void updateUser(Long id, UserCreateDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));

        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setCpf(dto.cpf());
        user.setPassword(dto.password());
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
        user.setDeleted(true);
        user.setDeletedAt(LocalDateTime.now());
        userRepository.save(user);
    }
}
