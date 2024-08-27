package com.example.librarycompetition.dto;

import com.example.librarycompetition.domain.Book;
import io.swagger.v3.oas.annotations.media.Schema;

public record BookDTO(
        @Schema(example = "1")
        String bookId,
        @Schema(example = "1")
        Integer bookSequence,
        @Schema(example = "가나다")
        String bookTitle,
        @Schema(example = "가나다")
        String bookAuthor,
        @Schema(example = "정상")
        String bookWarning,
        @Schema(example = "30")
        Integer bookDamage,
        @Schema(example = "가나다")
        String bookLabel
) {

    public static BookDTO of(String bookId, Integer bookSequence, String bookTitle, String bookAuthor, String bookWarning, Integer bookDamage, String bookLabel) {
        return new BookDTO(bookId, bookSequence, bookTitle, bookAuthor, bookWarning, bookDamage, bookLabel);
    }

    public static BookDTO from(Book book) {
        return new BookDTO(
                book.getBookId(),
                book.getBookSequence(),
                book.getBookTitle(),
                book.getBookAuthor(),
                book.getBookWarning(),
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
                .bookWarning(bookWarning)
                .bookDamage(bookDamage)
                .bookLabel(bookLabel)
                .build();
    }

}
