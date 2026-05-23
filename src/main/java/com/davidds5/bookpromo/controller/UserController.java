package com.davidds5.bookpromo.controller;

import com.davidds5.bookpromo.dto.UserRequestDTO;
import com.davidds5.bookpromo.dto.UserResponseDTO;
import com.davidds5.bookpromo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        UserResponseDTO saveUser = userService.save(userRequestDTO);
        return ResponseEntity.ok(saveUser);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponseDTO> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUsuarioId(@PathVariable Long id) {
        UserResponseDTO responseDTO = userService.getUser(id);
        return ResponseEntity.ok(responseDTO);
    }

}
