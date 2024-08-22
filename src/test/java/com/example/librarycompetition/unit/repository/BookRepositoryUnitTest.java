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

    @Nested
    @DisplayName("READ 테스트")
    class Test_READ {

        @Test
        @DisplayName("findByBookId 테스트")
        void testFindByBookId() {
            // when
            Optional<Book> result = bookRepository.findByBookId(book.getBookId());

            // then
            assertTrue(result.isPresent());
            assertEquals(book.getBookId(), result.get().getBookId());
        }

        @Test
        @DisplayName("findBooksByBookTitle 테스트")
        void testFindBooksByBookTitle() {
            // when
            List<Book> result = bookRepository.findBooksByBookTitle(book.getBookTitle());

            // then
            assertFalse(result.isEmpty());
            assertEquals(book.getBookTitle(), result.get(0).getBookTitle());
        }

        @Test
        @DisplayName("findBooksByBookAuthor 테스트")
        void testFindBooksByBookAuthor() {
            // when
            List<Book> result = bookRepository.findBooksByBookAuthor(book.getBookAuthor());

            // then
            assertFalse(result.isEmpty());
            assertEquals(book.getBookAuthor(), result.get(0).getBookAuthor());
        }

        @Test
        @DisplayName("findBooksByBookDamage 테스트")
        void testFindBooksByBookDamage() {
            // when
            List<Book> result = bookRepository.findBooksByBookDamage(book.getBookDamage());

            // then
            assertFalse(result.isEmpty());
            assertEquals(book.getBookDamage(), result.get(0).getBookDamage());
        }

        @Test
        @DisplayName("findBooksByBookLabel 테스트")
        void testFindBooksByBookLabel() {
            // when
            List<Book> result = bookRepository.findBooksByBookLabel(book.getBookLabel());

            // then
            assertFalse(result.isEmpty());
            assertEquals(book.getBookLabel(), result.get(0).getBookLabel());
        }
    }
}