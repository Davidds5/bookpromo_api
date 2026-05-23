package com.davidds5.bookpromo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.davidds5.bookpromo.dto.UserRequestDTO;
import com.davidds5.bookpromo.dto.UserResponseDTO;
import com.davidds5.bookpromo.entity.User;
import com.davidds5.bookpromo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDTO save(UserRequestDTO userRequestDTO) {
        if (userRepository.existsByEmail(userRequestDTO.getEmail())) {
            throw new IllegalArgumentException("Não é possível cadastrar: E-mail já cadastrado!");
        }
        if (userRepository.existsByCpf(userRequestDTO.getCpf())) {
            throw new IllegalArgumentException("Não é possível cadastrar: CPF já cadastrado!");
        }
        User user = new User();
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPhone(userRequestDTO.getPhone());
        user.setCpf(userRequestDTO.getCpf());
        return UserResponseDTO.fromEntity(userRepository.save(user));
    }

    public List<UserResponseDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDTO::fromEntity)
                .toList();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("Nao foi possivel deletar: Usuario com ID " + id + " nao encontrado!");
        }
        userRepository.deleteById(id);
    }

    public UserResponseDTO getUser(Long id) {
        return userRepository.findById(id)
                .map(UserResponseDTO :: fromEntity)
                .orElseThrow(() -> new IllegalArgumentException("Usuario nao encontrado!"));

    }

}
