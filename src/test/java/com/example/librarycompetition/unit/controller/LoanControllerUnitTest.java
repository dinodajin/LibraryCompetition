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

    @Nested
    @DisplayName("GET 테스트")
    class Test_GET {

        @Test
        @DisplayName("getOneLoan 테스트")
        void testGetOneLoan() throws Exception {
            // given
            String loanId = "loanTest";
            given(loanService.getOneLoan(loanId)).willReturn(loanDTO);

            // when & then
            mockMvc.perform(get("/loan/get/" + loanId)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.loanId").value(loanId))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("getAllLoan 테스트")
        void testGetAllLoan() throws Exception {
            // given
            List<LoanDTO> loanList = Collections.singletonList(loanDTO);
            given(loanService.getAllLoan()).willReturn(loanList);

            // when & then
            mockMvc.perform(get("/loan/get/all")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$[0].loanId").value(loanDTO.getLoanId()))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("getLoansByMemberId 테스트")
        void testGetLoansByMemberId() throws Exception {
            // given
            String memberId = "memberTest";
            List<LoanDTO> loanList = Collections.singletonList(loanDTO);
            given(loanService.getLoansByMemberId(memberId)).willReturn(loanList);

            // when & then
            mockMvc.perform(get("/loan/get/memberId/" + memberId)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$[0].memberId").value(memberId))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("getLoansByBookId 테스트")
        void testGetLoansByBookId() throws Exception {
            // given
            String bookId = "bookTest";
            List<LoanDTO> loanList = Collections.singletonList(loanDTO);
            given(loanService.getLoansByBookId(bookId)).willReturn(loanList);

            // when & then
            mockMvc.perform(get("/loan/get/bookId/" + bookId)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$[0].bookId").value(bookId))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("getLoansByLoanTimeIsNotNullAndReturnTimeIsNull 테스트")
        void testGetLoansByLoanTimeIsNotNullAndReturnTimeIsNull() throws Exception {
            // given
            List<LoanDTO> loanList = Collections.singletonList(loanDTO);
            given(loanService.getLoansByLoanTimeIsNotNullAndReturnTimeIsNull()).willReturn(loanList);

            // when & then
            mockMvc.perform(get("/loan/get/currentLoan")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$[0].loanId").value(loanDTO.getLoanId()))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("getLoansByLoanTimeBetween 테스트")
        void testGetLoansByLoanTimeBetween() throws Exception {
            // given
            LocalDate startDate = LocalDate.of(2023, 1, 1);
            LocalDate endDate = LocalDate.of(2023, 12, 31);
            List<LoanDTO> loanList = Collections.singletonList(loanDTO);
            given(loanService.getLoansByLoanTimeBetween(startDate, endDate)).willReturn(loanList);

            // when & then
            mockMvc.perform(get("/loan/get/loanTime")
                            .param("startDate", startDate.toString())
                            .param("endDate", endDate.toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$[0].loanId").value(loanDTO.getLoanId()))
                    .andExpect(status().isOk());
        }
    }
}