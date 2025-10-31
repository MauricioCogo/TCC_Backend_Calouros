package com.mauriciocogo.tcc_backend.controller;

import com.mauriciocogo.tcc_backend.dto.create.UserCreateDTO;
import com.mauriciocogo.tcc_backend.dto.response.UserResponseDTO;
import com.mauriciocogo.tcc_backend.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
@Tag(name = "Usuários", description = "Operações relacionadas à gestão de usuários do sistema")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Operation(
        summary = "Criar novo usuário",
        description = "Cria um novo usuário no sistema com as informações fornecidas."
    )
    @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserCreateDTO dto) {
        UserResponseDTO created = userService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    @Operation(
        summary = "Listar todos os usuários",
        description = "Retorna uma lista contendo todos os usuários cadastrados no sistema."
    )
    @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Buscar usuário por ID",
        description = "Retorna os dados do usuário correspondente ao ID informado."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        UserResponseDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/cpf/{cpf}")
    @Operation(
        summary = "Buscar usuário por CPF",
        description = "Retorna os dados do usuário correspondente ao CPF informado."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<UserResponseDTO> getUserByCPF(@PathVariable String cpf) {
        UserResponseDTO user = userService.getUserByCPF(cpf);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/auth")
    @Operation(
        summary = "Autenticar usuário",
        description = "Verifica as credenciais do usuário e retorna suas informações caso a autenticação seja bem-sucedida."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuário autenticado com sucesso"),
        @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    public ResponseEntity<UserResponseDTO> authenticateUser(@RequestBody UserCreateDTO dto) {
        UserResponseDTO authenticated = userService.authenticateUser(dto);
        return ResponseEntity.ok(authenticated);
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar usuário",
        description = "Remove um usuário do sistema com base no ID informado."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
