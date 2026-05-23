package com.davidds5.bookpromo.dto;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Email;
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
public class UserRequestDTO {
    @NotBlank(message = "Nome e obrigatorio")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 a 100 caractres")
    private String name;

    @NotBlank(message = "Email e obrigatorio")
    @Email(message = "O email deve ser valido")
    private String email;

    @NotBlank(message = "Celular e obrigatorio")
    @Size(min = 11, max = 15, message = "O celular deve ter entre 11 a 15 caractres")
    private String phone;

    @NotBlank(message = "CPF e obrigatorio")
    @CPF(message = "CPF invalido")
    private String cpf;

}
