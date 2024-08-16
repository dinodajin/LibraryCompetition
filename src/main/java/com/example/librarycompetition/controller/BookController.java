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
    public ResponseEntity<List<BookDTO>> getBooksByBookTitle(@PathVariable String bookTitle) {
        return new ResponseEntity<>(bookService.getBooksByBookTitle(bookTitle), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<BookDTO>> getBooksByBookAuthor(@PathVariable String bookAuthor) {
        return new ResponseEntity<>(bookService.getBooksByBookAuthor(bookAuthor), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<BookDTO>> getBooksByBookDamage(@PathVariable Integer bookDamage) {
        return new ResponseEntity<>(bookService.getBooksByBookDamage(bookDamage), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<BookDTO>> getBooksByBookLabel(@PathVariable String bookLabel) {
        return new ResponseEntity<>(bookService.getBooksByBookLabel(bookLabel), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        return new ResponseEntity<>(bookService.createBook(bookDTO), HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<BookDTO> updateBook(@RequestBody BookDTO bookDTO) {
        return new ResponseEntity<>(bookService.updateBook(bookDTO), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteBook(@PathVariable String bookId) {
        bookService.deleteBook(bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
