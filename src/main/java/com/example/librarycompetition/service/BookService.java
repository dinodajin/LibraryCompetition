package com.example.librarycompetition.service;

import com.example.librarycompetition.domain.Book;
import com.example.librarycompetition.dto.BookDTO;
import com.example.librarycompetition.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public BookDTO getOneBook(String bookId) {
        return BookDTO.from(bookRepository.findByBookId(bookId));
    }

    public List<BookDTO> getAllBook() {
        List<Book> books = bookRepository.findAll();
        List<BookDTO> bookDTOs = new ArrayList<>();
        for (Book book : books) {
            BookDTO bookDTO = BookDTO.from(book);
            bookDTOs.add(bookDTO);
        }
        return bookDTOs;
    }

    public List<BookDTO> getBooksByBookTitle(String bookTitle) {
        List<Book> books = bookRepository.findBooksByBookTitle(bookTitle);
        List<BookDTO> bookDTOs = new ArrayList<>();
    }

    public List<BookDTO> getBooksByBookAuthor(String bookAuthor) {
    }

    public List<BookDTO> getBooksByBookDamage(Integer bookDamage) {
    }

    public List<BookDTO> getBooksByBookLabel(String bookLabel) {
    }

    public BookDTO createBook(BookDTO bookDTO) {
    }

    public BookDTO updateBook(BookDTO bookDTO) {
    }

    public Void deleteBook(String bookId) {
        return null;
    }
}
