package com.davidds5.bookpromo.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.davidds5.bookpromo.dto.BookRequestDTO;
import com.davidds5.bookpromo.dto.BookResponseDTO;
import com.davidds5.bookpromo.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponseDTO> create(@RequestBody @Valid BookRequestDTO bookRequestDTO) {
        BookResponseDTO savedBook = bookService.save(bookRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> findAll() {
        List<BookResponseDTO> books = bookService.findAll();
        return ResponseEntity.ok(books);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookResponseDTO> delete(@PathVariable Long id) {
        bookService.delete(id);
        // retorna o status 204
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBookId(@PathVariable Long id) {
        BookResponseDTO responseDTO = bookService.getBookId(id);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(
            @PathVariable Long id,
            @RequestBody @Valid BookRequestDTO bookRequestDTO) {
        BookResponseDTO updated = bookService.updateBook(id, bookRequestDTO);
        return ResponseEntity.ok(updated);
    }

}