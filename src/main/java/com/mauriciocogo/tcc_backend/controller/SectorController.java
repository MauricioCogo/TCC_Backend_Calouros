package com.mauriciocogo.tcc_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mauriciocogo.tcc_backend.dto.create.SectorCreateDTO;
import com.mauriciocogo.tcc_backend.dto.response.SectorResponseDTO;
import com.mauriciocogo.tcc_backend.service.SectorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/sectors")
@Tag(name = "Setores", description = "Operações relacionadas à gestão de setores no sistema")
public class SectorController {

    private final SectorService sectorService;

    public SectorController(SectorService sectorService) {
        this.sectorService = sectorService;
    }

    @PostMapping
    @Operation(summary = "Criar novo setor", description = "Cria um novo setor com as informações fornecidas, incluindo o vínculo com um usuário responsável (via Id).")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Setor criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<SectorResponseDTO> createSector(@RequestBody SectorCreateDTO dto) {
        SectorResponseDTO created = sectorService.createSector(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    @Operation(summary = "Listar todos os setores", description = "Retorna uma lista de todos os setores cadastrados no sistema.")
    @ApiResponse(responseCode = "200", description = "Lista de setores retornada com sucesso")
    public ResponseEntity<List<SectorResponseDTO>> getAllSectors() {
        List<SectorResponseDTO> sectors = sectorService.getAllSectors();
        return ResponseEntity.ok(sectors);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar setor por ID", description = "Retorna os dados de um setor específico com base no ID fornecido.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Setor encontrado"),
            @ApiResponse(responseCode = "404", description = "Setor não encontrado")
    })
    public ResponseEntity<SectorResponseDTO> getSectorById(@PathVariable Long id) {
        SectorResponseDTO sector = sectorService.getSectorById(id);
        return ResponseEntity.ok(sector);
    }

    @GetMapping("/search")
    public ResponseEntity<List<SectorResponseDTO>> searchSectors(@RequestParam("q") String keyword) {
        List<SectorResponseDTO> results = sectorService.search(keyword);
        return ResponseEntity.ok(results);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar setor", description = "Atualiza as informações de um setor existente com base no ID fornecido.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Setor atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Setor não encontrado")
    })
    public ResponseEntity<SectorResponseDTO> updateSector(
            @PathVariable Long id,
            @RequestBody SectorCreateDTO dto) {
        SectorResponseDTO updated = sectorService.updateSector(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar setor", description = "Remove permanentemente um setor do sistema com base no ID informado.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Setor deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Setor não encontrado")
    })
    public ResponseEntity<Void> deleteSector(@PathVariable Long id) {
        sectorService.deleteSector(id);
        return ResponseEntity.noContent().build();
    }
}
