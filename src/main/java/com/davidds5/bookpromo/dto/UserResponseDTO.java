package com.davidds5.bookpromo.dto;

import com.davidds5.bookpromo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String cpf;

    public static UserResponseDTO fromEntity(User user) {
      return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getPhone(), user.getCpf());
    }

}
