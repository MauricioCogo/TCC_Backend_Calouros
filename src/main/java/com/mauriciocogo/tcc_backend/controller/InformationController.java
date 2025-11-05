package com.mauriciocogo.tcc_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mauriciocogo.tcc_backend.dto.create.InformationCreateDTO;
import com.mauriciocogo.tcc_backend.dto.response.InformationResponseDTO;
import com.mauriciocogo.tcc_backend.service.InformationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/informations")
@Tag(name = "Informações", description = "Operações relacionadas ao gerenciamento de informações do sistema")
public class InformationController {

    private final InformationService informationService;

    public InformationController(InformationService informationService) {
        this.informationService = informationService;
    }

    @PostMapping
    @Operation(
        summary = "Criar nova informação",
        description = "Cria uma nova informação associada a um setor específico. "
                    + "Os campos incluem título, descrição, tipo e o ID do setor relacionado."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Informação criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<InformationResponseDTO> createInformation(@RequestBody InformationCreateDTO dto) {
        InformationResponseDTO created = informationService.createInformation(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    @Operation(
        summary = "Listar todas as informações",
        description = "Retorna uma lista de todas as informações cadastradas no sistema."
    )
    @ApiResponse(responseCode = "200", description = "Lista de informações retornada com sucesso")
    public ResponseEntity<List<InformationResponseDTO>> getAllInformation() {
        List<InformationResponseDTO> informationList = informationService.getAllInformations();
        return ResponseEntity.ok(informationList);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Buscar informação por ID",
        description = "Retorna os detalhes de uma informação específica com base no ID fornecido."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Informação encontrada"),
        @ApiResponse(responseCode = "404", description = "Informação não encontrada")
    })
    public ResponseEntity<InformationResponseDTO> getInformationById(@PathVariable Long id) {
        InformationResponseDTO info = informationService.getInformationById(id);
        return ResponseEntity.ok(info);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Atualizar informação existente",
        description = "Atualiza os dados de uma informação existente com base no ID fornecido."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Informação atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Informação não encontrada")
    })
    public ResponseEntity<InformationResponseDTO> updateInformation(
            @PathVariable Long id,
            @RequestBody InformationCreateDTO dto) {
        InformationResponseDTO updated = informationService.updateInformation(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar informação",
        description = "Remove uma informação do sistema com base no ID informado."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Informação deletada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Informação não encontrada")
    })
    public ResponseEntity<Void> deleteInformation(@PathVariable Long id) {
        informationService.deleteInformation(id);
        return ResponseEntity.noContent().build();
    }
}
