package com.example.librarycompetition.controller;

import com.example.librarycompetition.dto.BookDTO;
import com.example.librarycompetition.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/get/{bookId}")
    public ResponseEntity<BookDTO> getOneBook(@PathVariable String bookId) {
        log.info("getOneBook : bookId = {}", bookId);
        return new ResponseEntity<>(bookService.getOneBook(bookId), HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<BookDTO>> getAllBook() {
        log.info("getAllBook");
        return new ResponseEntity<>(bookService.getAllBook(), HttpStatus.OK);
    }

    @GetMapping("/get/bookTitle/{bookTitle}")
    public ResponseEntity<List<BookDTO>> getBooksByBookTitle(@PathVariable String bookTitle) {
        log.info("getBooksByBookTitle : bookTitle = {}", bookTitle);
        return new ResponseEntity<>(bookService.getBooksByBookTitle(bookTitle), HttpStatus.OK);
    }

    @GetMapping("/get/bookAuthor/{bookAuthor}")
    public ResponseEntity<List<BookDTO>> getBooksByBookAuthor(@PathVariable String bookAuthor) {
        log.info("getBooksByBookAuthor : bookAuthor = {}", bookAuthor);
        return new ResponseEntity<>(bookService.getBooksByBookAuthor(bookAuthor), HttpStatus.OK);
    }

    @GetMapping("/get/bookDamage/{bookDamage}")
    public ResponseEntity<List<BookDTO>> getBooksByBookDamage(@PathVariable Integer bookDamage) {
        log.info("getBooksByBookDamage : bookDamage = {}", bookDamage);
        return new ResponseEntity<>(bookService.getBooksByBookDamage(bookDamage), HttpStatus.OK);
    }

    @GetMapping("/get/bookLabel/{bookLabel}")
    public ResponseEntity<List<BookDTO>> getBooksByBookLabel(@PathVariable String bookLabel) {
        log.info("getBooksByBookLabel : bookLabel = {}", bookLabel);
        return new ResponseEntity<>(bookService.getBooksByBookLabel(bookLabel), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        log.info("createBook : bookDTO = {}", bookDTO);
        return new ResponseEntity<>(bookService.createBook(bookDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<BookDTO> updateBook(@RequestBody BookDTO bookDTO) {
        log.info("updateBook : bookDTO = {}", bookDTO);
        return new ResponseEntity<>(bookService.updateBook(bookDTO), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable String bookId) {
        log.info("deleteBook : bookId = {}", bookId);
        bookService.deleteBook(bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
