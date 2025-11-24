package com.mauriciocogo.tcc_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mauriciocogo.tcc_backend.dto.create.SectorCreateDTO;
import com.mauriciocogo.tcc_backend.dto.response.SectorLocationDTO;
import com.mauriciocogo.tcc_backend.dto.response.SectorResponseDTO;
import com.mauriciocogo.tcc_backend.service.SectorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/sectors")
@Tag(
    name = "Setores",
    description = "Endpoints responsáveis pelo gerenciamento dos setores institucionais."
)
public class SectorController {

    private final SectorService sectorService;

    public SectorController(SectorService sectorService) {
        this.sectorService = sectorService;
    }

    @PostMapping
    @Operation(
        summary = "Criar setor",
        description = "Cria um novo setor com nome, sigla, descrição, localização física, coordenadas geográficas e responsável vinculado."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Setor criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<SectorResponseDTO> createSector(@RequestBody SectorCreateDTO dto) {
        SectorResponseDTO created = sectorService.createSector(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    @Operation(
        summary = "Listar setores",
        description = "Retorna a lista completa de setores cadastrados no sistema."
    )
    @ApiResponse(responseCode = "200", description = "Lista de setores retornada com sucesso")
    public ResponseEntity<List<SectorResponseDTO>> getAllSectors() {
        return ResponseEntity.ok(sectorService.getAllSectors());
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Buscar setor por ID",
        description = "Retorna os dados completos de um setor específico, com base no ID informado."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Setor encontrado"),
        @ApiResponse(responseCode = "404", description = "Setor não encontrado")
    })
    public ResponseEntity<SectorResponseDTO> getSectorById(@PathVariable Long id) {
        return ResponseEntity.ok(sectorService.getSectorById(id));
    }

    @GetMapping("/search")
    @Operation(
        summary = "Pesquisar setores",
        description = "Realiza a busca de setores por palavra-chave (nome, sigla ou descrição). Retorna apenas dados essenciais para listagem rápida."
    )
    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    public ResponseEntity<List<SectorLocationDTO>> searchSectors(@RequestParam("q") String keyword) {
        return ResponseEntity.ok(sectorService.search(keyword));
    }

    @GetMapping("/count")
    @Operation(
        summary = "Contar setores",
        description = "Retorna a quantidade total de setores cadastrados."
    )
    public Integer getCount() {
        return sectorService.getCount();
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Atualizar setor",
        description = "Atualiza os dados de um setor existente com base no ID informado. Permite alterar descrição, localização, coordenadas, responsável, etc."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Setor atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Setor não encontrado")
    })
    public ResponseEntity<SectorResponseDTO> updateSector(
            @PathVariable Long id,
            @RequestBody SectorCreateDTO dto
    ) {
        SectorResponseDTO updated = sectorService.updateSector(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Excluir setor",
        description = "Remove permanentemente um setor do sistema, com base no ID informado."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Setor removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Setor não encontrado")
    })
    public ResponseEntity<Void> deleteSector(@PathVariable Long id) {
        sectorService.deleteSector(id);
        return ResponseEntity.noContent().build();
    }
}
