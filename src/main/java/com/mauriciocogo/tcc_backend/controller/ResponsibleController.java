package com.mauriciocogo.tcc_backend.controller;

import com.mauriciocogo.tcc_backend.dto.create.ResponsibleCreateDTO;
import com.mauriciocogo.tcc_backend.dto.response.ResponsibleResponseDTO;
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
@Tag(
    name = "Responsáveis",
    description = "Endpoints relacionados ao gerenciamento de responsáveis por setores."
)
public class ResponsibleController {

    private final ResponsibleService responsibleService;

    public ResponsibleController(ResponsibleService responsibleService) {
        this.responsibleService = responsibleService;
    }

    @PostMapping
    @Operation(
        summary = "Criar responsável",
        description = "Cria um novo responsável vinculado a um setor. Requer nome, email e função."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Responsável criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<ResponsibleResponseDTO> createResponsible(
            @RequestBody ResponsibleCreateDTO dto
    ) {
        ResponsibleResponseDTO created = responsibleService.createResponsible(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    @Operation(
        summary = "Listar responsáveis",
        description = "Retorna a lista de todos os responsáveis cadastrados."
    )
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public ResponseEntity<List<ResponsibleResponseDTO>> getAllResponsibles() {
        return ResponseEntity.ok(responsibleService.getAllResponsibles());
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Buscar responsável por ID",
        description = "Retorna os dados de um responsável específico pelo ID fornecido."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Responsável encontrado"),
        @ApiResponse(responseCode = "404", description = "Responsável não encontrado")
    })
    public ResponseEntity<ResponsibleResponseDTO> getResponsibleById(@PathVariable Long id) {
        return ResponseEntity.ok(responsibleService.getResponsibleById(id));
    }

    @GetMapping("/count")
    @Operation(
        summary = "Contar responsáveis",
        description = "Retorna o número total de responsáveis cadastrados."
    )
    public Integer getCount() {
        return responsibleService.getCount();
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Atualizar responsável",
        description = "Atualiza os dados de um responsável existente com base no ID informado."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Responsável atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Responsável não encontrado")
    })
    public ResponseEntity<ResponsibleResponseDTO> updateResponsible(
            @PathVariable Long id,
            @RequestBody ResponsibleCreateDTO dto
    ) {
        ResponsibleResponseDTO updated = responsibleService.updateResponsible(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar responsável",
        description = "Remove um responsável do sistema pelo ID informado."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Responsável deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Responsável não encontrado")
    })
    public ResponseEntity<Void> deleteResponsible(@PathVariable Long id) {
        responsibleService.deleteResponsible(id);
        return ResponseEntity.noContent().build();
    }
}
