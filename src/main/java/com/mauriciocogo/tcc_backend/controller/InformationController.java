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

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/informations")
@Tag(
    name = "Informações",
    description = "Endpoints responsáveis pelo gerenciamento de informações institucionais."
)
public class InformationController {

    private final InformationService informationService;

    public InformationController(InformationService informationService) {
        this.informationService = informationService;
    }

    @PostMapping
    @Operation(
        summary = "Criar nova informação",
        description = "Cria uma nova informação vinculada a um setor. Requer título, descrição, tipo e ID do setor."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Informação criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<InformationResponseDTO> createInformation(
            @RequestBody InformationCreateDTO dto
    ) {
        InformationResponseDTO created = informationService.createInformation(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    @Operation(
        summary = "Listar informações ativas",
        description = "Retorna todas as informações ativas cadastradas no sistema."
    )
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public ResponseEntity<List<InformationResponseDTO>> getAllInformationActive() {
        return ResponseEntity.ok(informationService.getAllInformationsActives());
    }

    @GetMapping("/deleted")
    @Operation(
        summary = "Listar informações (inclui excluídas)",
        description = "Retorna todas as informações, incluindo aquelas marcadas como excluídas."
    )
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public ResponseEntity<List<InformationResponseDTO>> getAllInformation() {
        return ResponseEntity.ok(informationService.getAllInformations());
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Buscar informação por ID",
        description = "Retorna os dados de uma informação específica pelo ID."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Informação encontrada"),
        @ApiResponse(responseCode = "404", description = "Informação não encontrada")
    })
    public ResponseEntity<InformationResponseDTO> getInformationById(@PathVariable Long id) {
        return ResponseEntity.ok(informationService.getInformationById(id));
    }

    @GetMapping("/search")
    @Operation(
        summary = "Pesquisar informações",
        description = "Busca informações pelo título ou pela descrição. Parâmetro: q"
    )
    @ApiResponse(responseCode = "200", description = "Resultados retornados com sucesso")
    public ResponseEntity<List<InformationResponseDTO>> searchInformations(
            @RequestParam("q") String keyword
    ) {
        return ResponseEntity.ok(informationService.search(keyword));
    }

    @GetMapping("/count")
    @Operation(
        summary = "Contar informações cadastradas",
        description = "Retorna o número total de informações cadastradas."
    )
    public Integer getCount() {
        return informationService.getCount();
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Atualizar informação",
        description = "Atualiza os dados de uma informação existente através do ID."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Informação atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Informação não encontrada")
    })
    public ResponseEntity<InformationResponseDTO> updateInformation(
            @PathVariable Long id,
            @RequestBody InformationCreateDTO dto
    ) {
        return ResponseEntity.ok(informationService.updateInformation(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar informação",
        description = "Remove uma informação do sistema pelo ID informado."
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
