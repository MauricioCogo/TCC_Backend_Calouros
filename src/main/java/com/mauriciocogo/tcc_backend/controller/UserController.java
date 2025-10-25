package com.mauriciocogo.tcc_backend.controller;

import org.springframework.web.bind.annotation.*;
import com.mauriciocogo.tcc_backend.dto.create.UserCreateDTO;
import com.mauriciocogo.tcc_backend.dto.response.UserResponseDTO;
import com.mauriciocogo.tcc_backend.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponseDTO createUser(@RequestBody UserCreateDTO dto) {
        return userService.createUser(dto);
    }

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public UserResponseDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("cpf/{cpf}")
    public UserResponseDTO getUserByCPF(@PathVariable String cpf) {
        return userService.getUserByCPF(cpf);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
