package com.davidds5.bookpromo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.davidds5.bookpromo.dto.BookRequestDTO;
import com.davidds5.bookpromo.dto.BookResponseDTO;
import com.davidds5.bookpromo.entity.Book;
import com.davidds5.bookpromo.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public BookResponseDTO save(BookRequestDTO dto) {
        Book book = new Book();
        book.setName(dto.getName());
        book.setAuthor(dto.getAuthor());
        book.setCategory(dto.getCategory());
        return BookResponseDTO.fromEntity(bookRepository.save(book));
    }

    public List<BookResponseDTO> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(BookResponseDTO::fromEntity)
                .toList();
    }

    public void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new IllegalArgumentException("Nao foi possivel deletar: Livro com ID " + id + " nao encontrado");
        }
        bookRepository.deleteById(id);
    }

    public BookResponseDTO getBookId(Long id) {
        return bookRepository.findById(id)
                .map(BookResponseDTO::fromEntity)
                .orElseThrow(() -> new IllegalArgumentException("Book com ID " + id + " nao encontrado"));
    }

    public BookResponseDTO updateBook(Long id, BookRequestDTO dto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book com ID " + id + " nao encontrado"));

        book.setName(dto.getName());
        book.setAuthor(dto.getAuthor());
        book.setCategory(dto.getCategory());

        return BookResponseDTO.fromEntity(bookRepository.save(book));
    }

}
