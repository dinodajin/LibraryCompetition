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

    @Nested
    @DisplayName("GET 테스트")
    class Test_GET {

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

        @Test
        @DisplayName("getByOne 테스트")
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

    }

}
