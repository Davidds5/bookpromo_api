package com.davidds5.bookpromo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDTO {

    @NotBlank(message = "Requer nome")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caractres")
    private String name;

    @NotBlank(message = "Requer nome do author")
    @Size(min = 3, max = 100, message = "O nome do authot deve ter entre 3 e 100 caractres")
    private String author;

    private String category;

}
