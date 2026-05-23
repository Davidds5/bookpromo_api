package com.davidds5.bookpromo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriceAlertRequestDTO {

    @NotNull(message = "Id do livro obrigatorio")
    private Long bookId;

    @NotNull(message = "Id do usuario obrigatorio")
    private Long userId;

    @NotNull(message = "Preco obrigatorio")
    @Positive(message = "Preco tem que ser maior que zero")
    private BigDecimal desiredPrice;
}
