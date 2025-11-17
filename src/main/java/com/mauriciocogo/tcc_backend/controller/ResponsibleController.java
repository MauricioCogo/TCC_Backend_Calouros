package com.mauriciocogo.tcc_backend.controller;

import com.mauriciocogo.tcc_backend.dto.create.ResponsibleCreateDTO;
import com.mauriciocogo.tcc_backend.dto.create.SectorCreateDTO;
import com.mauriciocogo.tcc_backend.dto.response.ResponsibleResponseDTO;
import com.mauriciocogo.tcc_backend.dto.response.SectorResponseDTO;
import com.mauriciocogo.tcc_backend.service.ResponsibleService;

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
@RequestMapping("/responsibles")
@Tag(name = "Usuários", description = "Operações relacionadas à gestão de usuários do sistema")
public class ResponsibleController {

    private final ResponsibleService responsibleService;

    public ResponsibleController(ResponsibleService responsibleService) {
        this.responsibleService = responsibleService;
    }

    @PostMapping
    @Operation(summary = "Criar novo usuário", description = "Cria um novo usuário no sistema com as informações fornecidas.")
    @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso")
    public ResponseEntity<ResponsibleResponseDTO> createResponsible(@RequestBody ResponsibleCreateDTO dto) {
        ResponsibleResponseDTO created = responsibleService.createResponsible(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    @Operation(summary = "Listar todos os usuários", description = "Retorna uma lista contendo todos os usuários cadastrados no sistema.")
    @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso")
    public ResponseEntity<List<ResponsibleResponseDTO>> getAllResponsibles() {
        List<ResponsibleResponseDTO> responsibles = responsibleService.getAllResponsibles();
        return ResponseEntity.ok(responsibles);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por ID", description = "Retorna os dados do usuário correspondente ao ID informado.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<ResponsibleResponseDTO> getResponsibleById(@PathVariable Long id) {
        ResponsibleResponseDTO responsible = responsibleService.getResponsibleById(id);
        return ResponseEntity.ok(responsible);
    }

    @GetMapping("/count")
    public Integer getCount() {
        return responsibleService.getCount();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar setor", description = "Atualiza as informações de um setor existente com base no ID fornecido.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Setor atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Setor não encontrado")
    })
    public ResponseEntity<ResponsibleResponseDTO> updateResponsible(
            @PathVariable Long id,
            @RequestBody ResponsibleCreateDTO dto) {
        ResponsibleResponseDTO updated = responsibleService.updateResponsible(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar usuário", description = "Remove um usuário do sistema com base no ID informado.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<Void> deleteResponsible(@PathVariable Long id) {
        responsibleService.deleteResponsible(id);
        return ResponseEntity.noContent().build();
    }
}
