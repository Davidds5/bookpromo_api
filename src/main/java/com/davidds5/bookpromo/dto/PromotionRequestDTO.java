package com.davidds5.bookpromo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PromotionRequestDTO {
    
    @NotNull(message = "Id obrigatorio")
    private Long bookId;

    @NotBlank(message = "Nome Obrigatorio")
    private String platform;

    @NotNull(message = "Preco obrigatorio")
    @Positive(message = "Preco tem que ser maior que 0")
    private BigDecimal price;

    @NotBlank(message = "Url obrigatoria")
    @URL(message = "URL invalida")
    private String affiliateLink;

}
