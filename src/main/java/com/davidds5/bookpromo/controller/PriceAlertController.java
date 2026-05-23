package com.davidds5.bookpromo.controller;

import com.davidds5.bookpromo.dto.PriceAlertRequestDTO;
import com.davidds5.bookpromo.dto.PriceAlertResponseDTO;
import com.davidds5.bookpromo.entity.Book;
import com.davidds5.bookpromo.repository.PriceAlertRepository;
import com.davidds5.bookpromo.service.PriceAlertService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/priceAlert")
@RequiredArgsConstructor
public class PriceAlertController {

    private final PriceAlertService priceAlertService;
    private final PriceAlertRepository priceAlertRepository;

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

    @GetMapping("/{id}")
    public ResponseEntity<PriceAlertResponseDTO> getPriceId(@PathVariable Long id) {
        PriceAlertResponseDTO responseDTO = priceAlertService.getPriceAlertById(id);
        return ResponseEntity.ok(responseDTO);
    }


}
