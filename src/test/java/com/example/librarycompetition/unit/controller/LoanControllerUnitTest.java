package com.example.librarycompetition.unit.controller;

import com.example.librarycompetition.controller.LoanController;
import com.example.librarycompetition.dto.LoanDTO;
import com.example.librarycompetition.service.LoanService;
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

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.doNothing;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoanController.class)
@AutoConfigureMockMvc(addFilters = false)
public class LoanControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoanService loanService;

    private LoanDTO loanDTO;

    @BeforeEach
    @DisplayName("스텁 설정")
    void setUp() {
        // 테스트에 사용할 LoanDTO 객체를 초기화합니다.
        String loanId = "loanTest";
        String memberId = "memberTest";
        String bookId = "bookTest";
        LocalDate loanTime = LocalDate.of(2023, 8, 21);
        LocalDate returnTime = null; // 아직 반납되지 않은 상태

        loanDTO = LoanDTO.of(loanId, memberId, bookId, loanTime, returnTime);
    }
}