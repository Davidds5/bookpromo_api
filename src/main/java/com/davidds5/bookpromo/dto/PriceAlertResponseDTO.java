package com.davidds5.bookpromo.dto;


import com.davidds5.bookpromo.entity.PriceAlert;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriceAlertResponseDTO {
    private Long id;
    private Long bookId;
    private Long userId;
    private BigDecimal desirePrice;
    private boolean active;

    public static PriceAlertResponseDTO fromEntity(PriceAlert priceAlert) {
        return new PriceAlertResponseDTO(priceAlert.getId(), priceAlert.getBookId().getId(), priceAlert.getUserId().getId(), priceAlert.getDesiredPrice(), priceAlert.isActive());
    }
}
