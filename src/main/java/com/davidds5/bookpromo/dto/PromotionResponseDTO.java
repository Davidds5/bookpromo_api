package com.davidds5.bookpromo.dto;

import com.davidds5.bookpromo.entity.Promotion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PromotionResponseDTO {
    private Long id;
    private Long bookId;
    private String platform;
    private BigDecimal price;
    private String affiliateLink;

    public static PromotionResponseDTO fromEntity(Promotion promotion) {
        return new PromotionResponseDTO(promotion.getId(),promotion.getBook().getId(), promotion.getPlatform(), promotion.getPrice(), promotion.getAffiliateLink());
    }
}
