package com.davidds5.bookpromo.service;

import java.util.List;
import java.util.Optional;

import com.davidds5.bookpromo.dto.PromotionRequestDTO;
import com.davidds5.bookpromo.dto.PromotionResponseDTO;
import com.davidds5.bookpromo.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.davidds5.bookpromo.entity.Promotion;
import com.davidds5.bookpromo.repository.BookRepository;
import com.davidds5.bookpromo.repository.PromotionRepository;

@Service
@RequiredArgsConstructor
public class PromotionService {

    private final PromotionRepository promotionRepository;

    private final BookRepository bookRepository;

    public PromotionResponseDTO save(PromotionRequestDTO promotionRequestDTO) {
        Book book = bookRepository.findById(promotionRequestDTO.getBookId())
                .orElseThrow(() -> new IllegalArgumentException("Nao e possivel cadrasta: Livro com ID "
                        + promotionRequestDTO.getBookId() + " nao encontrado"));

        Promotion promotion = new Promotion();
        promotion.setPlatform(promotionRequestDTO.getPlatform());
        promotion.setAffiliateLink(promotionRequestDTO.getAffiliateLink());
        promotion.setPrice(promotionRequestDTO.getPrice());

        promotion.setBook(book);

        return PromotionResponseDTO.fromEntity(promotionRepository.save(promotion));
    }

    public List<PromotionResponseDTO> findAll() {
        return promotionRepository.findAll()
                .stream()
                .map(PromotionResponseDTO::fromEntity)
                .toList();
    }

    public Optional<Promotion> findById(Long id) {
        return promotionRepository.findById(id);
    }

    public void delete(Long id) {
        if (!promotionRepository.existsById(id)) {
            throw new IllegalArgumentException("Nao foi possivel deleta: Promocao com ID " + id + " nao encontrada");
        }
        promotionRepository.deleteById(id);
    }

    public PromotionResponseDTO getPromotion(Long id) {
        return promotionRepository.findById(id)
                .map(PromotionResponseDTO :: fromEntity)
                .orElseThrow(()-> new IllegalArgumentException("Promocao nao encontrada"));
    }

    public PromotionResponseDTO updatePromotion(Long id, PromotionRequestDTO dto) {
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Promocao com ID " + id + " nao encotrado"));

        Book book = bookRepository.findById(dto.getBookId());

    }
}
