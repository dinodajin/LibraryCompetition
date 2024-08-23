package com.example.librarycompetition.unit.controller;

import com.example.librarycompetition.controller.LoanController;
import com.example.librarycompetition.dto.LoanDTO;
import com.example.librarycompetition.service.LoanService;
import io.swagger.v3.oas.annotations.media.Schema;
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
        String loanId = "1";
        LocalDate loanTime = LocalDate.of(2024, 8, 21);
        LocalDate returnTime = LocalDate.of(2024, 8, 21);
        String declaration = "책이 손상됐어요!";
        String memberId = "1";
        String bookId = "1";

        loanDTO = LoanDTO.of(loanId, loanTime, returnTime, declaration, memberId, bookId);
    }

    @Nested
    @DisplayName("GET 테스트")
    class Test_GET {

        @Test
        @DisplayName("getOneLoan 테스트")
        void testGetOneLoan() throws Exception {
            // given
            String loanId = "1";
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
                    .andExpect(jsonPath("$[0].loanId").value(loanDTO.loanId()))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("getLoansByMemberId 테스트")
        void testGetLoansByMemberId() throws Exception {
            // given
            String memberId = "1";
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
            String bookId = "1";
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
                    .andExpect(jsonPath("$[0].loanId").value(loanDTO.loanId()))
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
                    .andExpect(jsonPath("$[0].loanId").value(loanDTO.loanId()))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("POST 테스트")
    class Test_POST {

        @Test
        @DisplayName("createLoan 테스트")
        void testCreateLoan() throws Exception {
            // given
            given(loanService.createLoan(loanDTO)).willReturn(loanDTO);

            // when & then
            mockMvc.perform(post("/loan/create")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"loanId\": \"1\", \"loanTime\": \"2024-08-21\", \"returnTime\": \"2024-08-21\", \"declaration\": \"책이 손상됐어요!\", \"memberId\": \"1\", \"bookId\": \"1\"}"))
                    .andExpect(jsonPath("$.loanId").value(loanDTO.loanId()))
                    .andExpect(status().isCreated());
        }
    }

    @Nested
    @DisplayName("PUT 테스트")
    class Test_PUT {

        @Test
        @DisplayName("updateLoan 테스트")
        void testUpdateLoan() throws Exception {
            // given
            given(loanService.updateLoan(loanDTO)).willReturn(loanDTO);

            // when & then
            mockMvc.perform(put("/loan/update")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"loanId\": \"1\", \"loanTime\": \"2024-08-21\", \"returnTime\": \"2024-08-21\", \"declaration\": \"책이 손상됐어요!\", \"memberId\": \"1\", \"bookId\": \"1\"}"))
                    .andExpect(jsonPath("$.loanId").value(loanDTO.loanId()))
                    .andExpect(status().isAccepted());
        }
    }

    @Nested
    @DisplayName("DELETE 테스트")
    class Test_DELETE {

        @Test
        @DisplayName("deleteLoan 테스트")
        void testDeleteLoan() throws Exception {
            // given
            String loanId = "1";
            doNothing().when(loanService).deleteLoan(loanId);

            // when & then
            mockMvc.perform(delete("/loan/delete")
                            .param("loanId", loanId)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent());

            then(loanService).should().deleteLoan(loanId);
        }
    }
}