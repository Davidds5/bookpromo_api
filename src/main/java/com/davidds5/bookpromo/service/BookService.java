package com.davidds5.bookpromo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.davidds5.bookpromo.dto.BookRequestDTO;
import com.davidds5.bookpromo.dto.BookResponseDTO;
import com.davidds5.bookpromo.entity.Book;
import com.davidds5.bookpromo.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public BookResponseDTO save(BookRequestDTO bookRequestDTO) {
        Book book = new Book();
        book.setName(bookRequestDTO.getName());
        book.setAuthor(bookRequestDTO.getAuthor());
        book.setCategory(bookRequestDTO.getCategory());
        return BookResponseDTO.fromEntity(bookRepository.save(book));
    }

    public List<BookResponseDTO> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(BookResponseDTO::fromEntity)
                .toList();
    }

    public void delete(Long id) {
      if (!bookRepository.existsById(id)){
          throw new IllegalArgumentException("Nao foi possivel deletar: Livro com ID "+ id + " nao encontrado");
        }
        bookRepository.deleteById(id);
    }

}
