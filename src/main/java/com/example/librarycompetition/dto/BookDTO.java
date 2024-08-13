package com.example.librarycompetition.dto;

import com.example.librarycompetition.domain.Book;

public record BookDTO(
        String bookId,
        Integer bookSequence,
        String bookTitle,
        String bookAuthor,
        Integer bookDamage,
        String bookLabel
) {

    public static BookDTO of(String bookId, Integer bookSequence, String bookTitle, String bookAuthor, Integer bookDamage, String bookLabel) {
        return new BookDTO(bookId, bookSequence, bookTitle, bookAuthor, bookDamage, bookLabel);
    }

    public static BookDTO from(Book book) {
        return new BookDTO(
                book.getBookId(),
                book.getBookSequence(),
                book.getBookTitle(),
                book.getBookAuthor(),
                book.getBookDamage(),
                book.getBookLabel()
        );
    }

    public Book toEntity() {
        return Book.builder()
                .bookId(bookId)
                .bookSequence(bookSequence)
                .bookTitle(bookTitle)
                .bookAuthor(bookAuthor)
                .bookDamage(bookDamage)
                .bookLabel(bookLabel)
                .build();
    }

}
