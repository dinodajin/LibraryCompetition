package com.example.librarycompetition.repository.custom;

import com.example.librarycompetition.dto.BookDTO;

import java.util.List;

public interface BookCustomRepository {
    List<BookDTO> findBooksByDynamicQuery(String bookTitle, String bookAuthor, Integer bookDamage);
}
