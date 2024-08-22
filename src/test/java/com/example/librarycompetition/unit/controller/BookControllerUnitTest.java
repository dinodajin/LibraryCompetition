package com.example.librarycompetition.unit.controller;

import com.example.librarycompetition.controller.BookController;
import com.example.librarycompetition.dto.BookDTO;
import com.example.librarycompetition.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
@AutoConfigureMockMvc(addFilters = false)
public class BookControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private BookDTO bookDTO;

    @BeforeEach
    @DisplayName("스텁 설정")
    void setUp() {
        String bookId = "test";
        Integer bookSequence = 1;
        String bookTitle = "test";
        String bookAuthor = "test";
        Integer bookDamage = 10;
        String bookLabel = "test";

        bookDTO = BookDTO.of(bookId, bookSequence, bookTitle, bookAuthor, bookDamage, bookLabel);
    }

    @Nested
    @DisplayName("GET 테스트")
    class Test_GET {

        @Test
        @DisplayName("getByOneBook 테스트")
        void testGetOneBook() throws Exception {
            // given
            String bookId = "test";
            given(bookService.getOneBook(bookId)).willReturn(bookDTO);

            // when & then
            mockMvc.perform(get("/book/get/" + bookId)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.bookId").value(bookId))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("getAllBook 테스트")
        void testGetAllBook() throws Exception {
            // given
            List<BookDTO> bookList = Collections.singletonList(bookDTO);
            given(bookService.getAllBook()).willReturn(bookList);

            // when & then
            mockMvc.perform(get("/book/get/all")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$[0].bookId").value(bookDTO.getBookId()))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("getBooksByBookTitle 테스트")
        void testGetBooksByBookTitle() throws Exception {
            // given
            String bookTitle = "test";
            List<BookDTO> bookList = Collections.singletonList(bookDTO);
            given(bookService.getBooksByBookTitle(bookTitle)).willReturn(bookList);

            // when & then
            mockMvc.perform(get("/book/get/bookTitle/" + bookTitle)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$[0].bookTitle").value(bookTitle))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("getBooksByBookAuthor 테스트")
        void testGetBooksByBookAuthor() throws Exception {
            // given
            String bookAuthor = "test";
            List<BookDTO> bookList = Collections.singletonList(bookDTO);
            given(bookService.getBooksByBookAuthor(bookAuthor)).willReturn(bookList);

            // when & then
            mockMvc.perform(get("/book/get/bookAuthor/" + bookAuthor)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$[0].bookAuthor").value(bookAuthor))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("getBooksByBookDamage 테스트")
        void testGetBooksByBookDamage() throws Exception {
            // given
            Integer bookDamage = 10;
            List<BookDTO> bookList = Collections.singletonList(bookDTO);
            given(bookService.getBooksByBookDamage(bookDamage)).willReturn(bookList);

            // when & then
            mockMvc.perform(get("/book/get/bookDamage/" + bookDamage)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$[0].bookDamage").value(bookDamage))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("getBooksByBookLabel 테스트")
        void testGetBooksByBookLabel() throws Exception {
            // given
            String bookLabel = "test";
            List<BookDTO> bookList = Collections.singletonList(bookDTO);
            given(bookService.getBooksByBookLabel(bookLabel)).willReturn(bookList);

            // when & then
            mockMvc.perform(get("/book/get/bookLabel/" + bookLabel)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$[0].bookLabel").value(bookLabel))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("POST 테스트")
    class Test_POST {

        @Test
        @DisplayName("createBook 테스트")
        void testCreateBook() throws Exception {
            // given
            given(bookService.createBook(bookDTO)).willReturn(bookDTO);

            // when & then
            mockMvc.perform(post("/book/create")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"bookId\": \"test\", \"bookSequence\": 1, \"bookTitle\": \"test\", \"bookAuthor\": \"test\", \"bookDamage\": 10, \"bookLabel\": \"test\"}"))
                    .andExpect(jsonPath("$.bookId").value(bookDTO.getBookId()))
                    .andExpect(status().isCreated());
        }
    }
}