package com.mauriciocogo.tcc_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mauriciocogo.tcc_backend.dto.create.InformationCreateDTO;
import com.mauriciocogo.tcc_backend.dto.response.InformationResponseDTO;
import com.mauriciocogo.tcc_backend.service.InformationService;

@RestController
@RequestMapping("/information")
public class InformationController {

    private final InformationService informationService;

    public InformationController(InformationService informationService) {
        this.informationService = informationService;
    }

    @PostMapping
    public ResponseEntity<InformationResponseDTO> createInformation(@RequestBody InformationCreateDTO dto) {
        InformationResponseDTO created = informationService.createInformation(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<InformationResponseDTO>> getAllInformation() {
        List<InformationResponseDTO> informationList = informationService.getAllInformations();
        return ResponseEntity.ok(informationList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InformationResponseDTO> getInformationById(@PathVariable Long id) {
        InformationResponseDTO info = informationService.getInformationById(id);
        return ResponseEntity.ok(info);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InformationResponseDTO> updateInformation(
            @PathVariable Long id,
            @RequestBody InformationCreateDTO dto) {
        InformationResponseDTO updated = informationService.updateInformation(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInformation(@PathVariable Long id) {
        informationService.deleteInformation(id);
        return ResponseEntity.noContent().build();
    }
}
