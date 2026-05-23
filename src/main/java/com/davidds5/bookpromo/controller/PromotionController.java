package com.davidds5.bookpromo.controller;

import java.util.List;

import com.davidds5.bookpromo.dto.PromotionRequestDTO;
import com.davidds5.bookpromo.dto.PromotionResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.davidds5.bookpromo.service.PromotionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/promotions")
@RequiredArgsConstructor
public class PromotionController {

    private final PromotionService promotionService;

    @PostMapping
    public ResponseEntity<PromotionResponseDTO> create(@RequestBody @Valid PromotionRequestDTO promotionRequestDTO) {
        PromotionResponseDTO savedPromotion = promotionService.save(promotionRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPromotion);

    }

    @GetMapping
    public ResponseEntity<List<PromotionResponseDTO>> findAll() {
        List<PromotionResponseDTO> promotions = promotionService.findAll();
        return ResponseEntity.ok(promotions);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PromotionResponseDTO> delete(@PathVariable Long id) {
        promotionService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
