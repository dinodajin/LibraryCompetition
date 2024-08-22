package com.example.librarycompetition.unit.repository;

import com.example.librarycompetition.domain.Book;
import com.example.librarycompetition.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
public class BookRepositoryUnitTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private Book book;

    @BeforeEach
    @DisplayName("테스트 데이터 준비")
    void setUp() {
        mongoTemplate.dropCollection(Book.class);

        book = new Book();
        book.setBookId("testBookId");
        book.setBookTitle("Test Title");
        book.setBookAuthor("Test Author");
        book.setBookDamage(0);
        book.setBookLabel("Test Label");

        bookRepository.save(book);
    }
}