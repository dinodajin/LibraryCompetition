package com.example.librarycompetition.controller;

import com.example.librarycompetition.dto.BookDTO;
import com.example.librarycompetition.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("")
    public ResponseEntity<BookDTO> getOneBook(@PathVariable String bookId) {
        return new ResponseEntity<>(bookService.getOneBook(bookId), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<BookDTO>> getAllBook() {
        return new ResponseEntity<>(bookService.getAllBook(), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<BookDTO>> getBooksByBookTitle(String bookTitle) {
        return new ResponseEntity<>(bookService.getBooksByBookTitle(bookTitle), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<BookDTO>> getBooksByBookAuthor(String bookAuthor) {
        return new ResponseEntity<>(bookService.getBooksByBookAuthor(bookAuthor), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<BookDTO>> getBooksByBookDamage(Integer bookDamage) {
        return new ResponseEntity<>(bookService.getBooksByBookDamage(bookDamage), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<BookDTO>> getBooksByBookLabel(String bookLabel) {
        return new ResponseEntity<>(bookService.getBooksByBookLabel(bookLabel), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<BookDTO> createBook(BookDTO bookDTO) {
        return new ResponseEntity<>(bookService.createBook(bookDTO), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<BookDTO> updateBook(BookDTO bookDTO) {
        return new ResponseEntity<>(bookService.updateBook(bookDTO), HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteBook(String bookId) {
        return new ResponseEntity<>(bookService.deleteBook(bookId), HttpStatus.OK);
    }
}
