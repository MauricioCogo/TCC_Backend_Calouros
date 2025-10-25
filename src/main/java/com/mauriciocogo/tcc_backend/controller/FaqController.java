package com.mauriciocogo.tcc_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mauriciocogo.tcc_backend.dto.create.FaqCreateDTO;
import com.mauriciocogo.tcc_backend.dto.response.FaqResponseDTO;
import com.mauriciocogo.tcc_backend.service.FaqService;

@RestController
@RequestMapping("/faqs")
public class FaqController {
    
    private final FaqService faqService;

    public FaqController(FaqService faqService) {
        this.faqService = faqService;
    }

    @PostMapping
    public ResponseEntity<FaqResponseDTO> createFaq(@RequestBody FaqCreateDTO dto) {
        FaqResponseDTO created = faqService.createFaq(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<FaqResponseDTO>> getAllFaqs() {
        List<FaqResponseDTO> faqs = faqService.getAllFaqs();
        return ResponseEntity.ok(faqs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FaqResponseDTO> getFaqById(@PathVariable Long id) {
        FaqResponseDTO faq = faqService.getFaqById(id);
        return ResponseEntity.ok(faq);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FaqResponseDTO> updateFaq(
            @PathVariable Long id, 
            @RequestBody FaqCreateDTO dto) {
        FaqResponseDTO updated = faqService.updateFaq(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaq(@PathVariable Long id) {
        faqService.deleteFaq(id);
        return ResponseEntity.noContent().build();
    }
}
