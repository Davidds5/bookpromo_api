package com.davidds5.bookpromo.controller;

import com.davidds5.bookpromo.dto.PriceAlertRequestDTO;
import com.davidds5.bookpromo.dto.PriceAlertResponseDTO;
import com.davidds5.bookpromo.service.PriceAlertService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/priceAlert")
public class PriceAlertController {

    private PriceAlertService priceAlertService;

    @PostMapping
    public ResponseEntity<PriceAlertResponseDTO> createPriceAlert(
            @RequestBody @Valid PriceAlertRequestDTO priceAlertRequestDTO) {
        PriceAlertResponseDTO savePriceAlert = priceAlertService.save(priceAlertRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savePriceAlert);
    }

    @GetMapping
    public ResponseEntity<List<PriceAlertResponseDTO>> findAll() {
        List<PriceAlertResponseDTO> priceAlerts = priceAlertService.findAll();
        return ResponseEntity.ok(priceAlerts);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PriceAlertResponseDTO> delete(@PathVariable Long id) {
        priceAlertService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
