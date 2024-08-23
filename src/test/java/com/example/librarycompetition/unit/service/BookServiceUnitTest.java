package com.example.librarycompetition.unit.service;

import com.example.librarycompetition.domain.Book;
import com.example.librarycompetition.dto.BookDTO;
import com.example.librarycompetition.exception.ListNotFoundElementException;
import com.example.librarycompetition.exception.ResourceNotFoundException;
import com.example.librarycompetition.repository.BookRepository;
import com.example.librarycompetition.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

public class BookServiceUnitTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private BookDTO bookDTO;
    private Book book;

    @BeforeEach
    @DisplayName("스텁 설정")
    void setUp() {
        MockitoAnnotations.openMocks(this);
        String bookId = "testBookId";
        Integer bookSequence = 1;
        String bookTitle = "testTitle";
        String bookAuthor = "testAuthor";
        Integer bookDamage = 5;
        String bookLabel = "testLabel";

        bookDTO = BookDTO.of(bookId, bookSequence, bookTitle, bookAuthor, bookDamage, bookLabel);
        book = bookDTO.toEntity();
    }

    @Nested
    @DisplayName("GET 테스트")
    class Test_GET {

        @Test
        @DisplayName("getOneBook 테스트")
        void testGetOneBook() {
            // given
            given(bookRepository.findByBookId(bookDTO.bookId())).willReturn(Optional.of(book));

            // when
            BookDTO result = bookService.getOneBook(bookDTO.bookId());

            // then
            assertNotNull(result);
            assertEquals(bookDTO.bookId(), result.bookId());
        }

        @Test
        @DisplayName("getAllBook 테스트")
        void testGetAllBook() {
            // given
            List<Book> books = Collections.singletonList(book);
            given(bookRepository.findAll()).willReturn(books);

            // when
            List<BookDTO> result = bookService.getAllBook();

            // then
            assertFalse(result.isEmpty());
            assertEquals(bookDTO.bookId(), result.get(0).bookId());
        }

        @Test
        @DisplayName("getBooksByBookTitle 테스트")
        void testGetBooksByBookTitle() {
            // given
            List<Book> books = Collections.singletonList(book);
            given(bookRepository.findBooksByBookTitleContaining(bookDTO.bookTitle())).willReturn(books);

            // when
            List<BookDTO> result = bookService.getBooksByBookTitle(bookDTO.bookTitle());

            // then
            assertFalse(result.isEmpty());
            assertEquals(bookDTO.bookTitle(), result.get(0).bookTitle());
        }

        @Test
        @DisplayName("getBooksByBookAuthor 테스트")
        void testGetBooksByBookAuthor() {
            // given
            List<Book> books = Collections.singletonList(book);
            given(bookRepository.findBooksByBookAuthorContaining(bookDTO.bookAuthor())).willReturn(books);

            // when
            List<BookDTO> result = bookService.getBooksByBookAuthor(bookDTO.bookAuthor());

            // then
            assertFalse(result.isEmpty());
            assertEquals(bookDTO.bookAuthor(), result.get(0).bookAuthor());
        }

        @Test
        @DisplayName("getBooksByBookDamage 테스트")
        void testGetBooksByBookDamage() {
            // given
            List<Book> books = Collections.singletonList(book);
            given(bookRepository.findBooksByBookDamage(bookDTO.bookDamage())).willReturn(books);

            // when
            List<BookDTO> result = bookService.getBooksByBookDamage(bookDTO.bookDamage());

            // then
            assertFalse(result.isEmpty());
            assertEquals(bookDTO.bookDamage(), result.get(0).bookDamage());
        }

        @Test
        @DisplayName("getBooksByBookLabel 테스트")
        void testGetBooksByBookLabel() {
            // given
            List<Book> books = Collections.singletonList(book);
            given(bookRepository.findBooksByBookLabel(bookDTO.bookLabel())).willReturn(books);

            // when
            List<BookDTO> result = bookService.getBooksByBookLabel(bookDTO.bookLabel());

            // then
            assertFalse(result.isEmpty());
            assertEquals(bookDTO.bookLabel(), result.get(0).bookLabel());
        }

        @Test
        @DisplayName("getAllBook - ListNotFoundElementException 테스트")
        void testGetAllBook_ThrowsListNotFoundElementException() {
            // given
            given(bookRepository.findAll()).willReturn(new ArrayList<>());

            // when & then
            assertThrows(ListNotFoundElementException.class, () -> bookService.getAllBook());
        }

        @Test
        @DisplayName("getBooksByBookTitle - ListNotFoundElementException 테스트")
        void testGetBooksByBookTitle_ThrowsListNotFoundElementException() {
            // given
            given(bookRepository.findBooksByBookTitleContaining(bookDTO.bookTitle())).willReturn(new ArrayList<>());

            // when & then
            assertThrows(ListNotFoundElementException.class, () -> bookService.getBooksByBookTitle(bookDTO.bookTitle()));
        }

        @Test
        @DisplayName("getBooksByBookAuthor - ListNotFoundElementException 테스트")
        void testGetBooksByBookAuthor_ThrowsListNotFoundElementException() {
            // given
            given(bookRepository.findBooksByBookAuthorContaining(bookDTO.bookAuthor())).willReturn(new ArrayList<>());

            // when & then
            assertThrows(ListNotFoundElementException.class, () -> bookService.getBooksByBookAuthor(bookDTO.bookAuthor()));
        }

        @Test
        @DisplayName("getBooksByBookDamage - ListNotFoundElementException 테스트")
        void testGetBooksByBookDamage_ThrowsListNotFoundElementException() {
            // given
            given(bookRepository.findBooksByBookDamage(bookDTO.bookDamage())).willReturn(new ArrayList<>());

            // when & then
            assertThrows(ListNotFoundElementException.class, () -> bookService.getBooksByBookDamage(bookDTO.bookDamage()));
        }

        @Test
        @DisplayName("getBooksByBookLabel - ListNotFoundElementException 테스트")
        void testGetBooksByBookLabel_ThrowsListNotFoundElementException() {
            // given
            given(bookRepository.findBooksByBookLabel(bookDTO.bookLabel())).willReturn(new ArrayList<>());

            // when & then
            assertThrows(ListNotFoundElementException.class, () -> bookService.getBooksByBookLabel(bookDTO.bookLabel()));
        }
    }

    @Nested
    @DisplayName("POST 테스트")
    class Test_POST {

        @Test
        @DisplayName("createBook 테스트")
        void testCreateBook() {
            // given
            given(bookRepository.insert(book)).willReturn(book);

            // when
            BookDTO result = bookService.createBook(bookDTO);

            // then
            assertNotNull(result);
            assertEquals(bookDTO.bookId(), result.bookId());
        }
    }

    @Nested
    @DisplayName("PUT 테스트")
    class Test_PUT {

        @Test
        @DisplayName("updateBook 테스트")
        void testUpdateBook() {
            // given
            given(bookRepository.save(book)).willReturn(book);

            // when
            BookDTO result = bookService.updateBook(bookDTO);

            // then
            assertNotNull(result);
            assertEquals(bookDTO.bookId(), result.bookId());
        }
    }

    @Nested
    @DisplayName("DELETE 테스트")
    class Test_DELETE {

        @Test
        @DisplayName("deleteBook 테스트")
        void testDeleteBook() {
            // given
            willDoNothing().given(bookRepository).deleteByBookId(bookDTO.bookId());

            // when
            bookService.deleteBook(bookDTO.bookId());

            // then
            verify(bookRepository, times(1)).deleteByBookId(bookDTO.bookId());
        }
    }
}