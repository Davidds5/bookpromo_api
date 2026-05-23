package com.davidds5.bookpromo.dto;

import com.davidds5.bookpromo.entity.Book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDTO {
    private Long id;
    private String name;
    private String author;
    private String category;

    public static BookResponseDTO fromEntity(Book book) {
        return new BookResponseDTO(book.getId(), book.getName(), book.getAuthor(), book.getCategory());
    }

}
