package com.example.librarycompetition.unit.service;

import com.example.librarycompetition.domain.Loan;
import com.example.librarycompetition.dto.LoanDTO;
import com.example.librarycompetition.exception.ListNotFoundElementException;
import com.example.librarycompetition.exception.ResourceNotFoundException;
import com.example.librarycompetition.repository.LoanRepository;
import com.example.librarycompetition.service.LoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

public class LoanServiceUnitTest {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanService loanService;

    private LoanDTO loanDTO;
    private Loan loan;

    @BeforeEach
    @DisplayName("스텁 설정")
    void setUp() {
        MockitoAnnotations.openMocks(this);
        String loanId = "testLoanId";
        String memberId = "testMemberId";
        String bookId = "testBookId";
        LocalDate loanTime = LocalDate.now();

        loanDTO = LoanDTO.of(loanId, memberId, bookId, loanTime, null);
        loan = loanDTO.toEntity();
    }

    @Nested
    @DisplayName("GET 테스트")
    class Test_GET {

        @Test
        @DisplayName("getOneLoan 테스트")
        void testGetOneLoan() {
            // given
            given(loanRepository.findById(loanDTO.getLoanId())).willReturn(Optional.of(loan));

            // when
            LoanDTO result = loanService.getOneLoan(loanDTO.getLoanId());

            // then
            assertNotNull(result);
            assertEquals(loanDTO.getLoanId(), result.getLoanId());
        }

        @Test
        @DisplayName("getAllLoan 테스트")
        void testGetAllLoan() {
            // given
            List<Loan> loans = Collections.singletonList(loan);
            given(loanRepository.findAll()).willReturn(loans);

            // when
            List<LoanDTO> result = loanService.getAllLoan();

            // then
            assertFalse(result.isEmpty());
            assertEquals(loanDTO.getLoanId(), result.get(0).getLoanId());
        }

        @Test
        @DisplayName("getLoansByMemberId 테스트")
        void testGetLoansByMemberId() {
            // given
            List<Loan> loans = Collections.singletonList(loan);
            given(loanRepository.findLoansByMemberId(loanDTO.getMemberId())).willReturn(loans);

            // when
            List<LoanDTO> result = loanService.getLoansByMemberId(loanDTO.getMemberId());

            // then
            assertFalse(result.isEmpty());
            assertEquals(loanDTO.getMemberId(), result.get(0).getMemberId());
        }

        @Test
        @DisplayName("getLoansByBookId 테스트")
        void testGetLoansByBookId() {
            // given
            List<Loan> loans = Collections.singletonList(loan);
            given(loanRepository.findLoansByBookId(loanDTO.getBookId())).willReturn(loans);

            // when
            List<LoanDTO> result = loanService.getLoansByBookId(loanDTO.getBookId());

            // then
            assertFalse(result.isEmpty());
            assertEquals(loanDTO.getBookId(), result.get(0).getBookId());
        }

        @Test
        @DisplayName("getLoansByLoanTimeIsNotNullAndReturnTimeIsNull 테스트")
        void testGetLoansByLoanTimeIsNotNullAndReturnTimeIsNull() {
            // given
            List<Loan> loans = Collections.singletonList(loan);
            given(loanRepository.findLoansByLoanTimeIsNotNullAndReturnTimeIsNull()).willReturn(loans);

            // when
            List<LoanDTO> result = loanService.getLoansByLoanTimeIsNotNullAndReturnTimeIsNull();

            // then
            assertFalse(result.isEmpty());
            assertEquals(loanDTO.getLoanId(), result.get(0).getLoanId());
        }

        @Test
        @DisplayName("getLoansByLoanTimeBetween 테스트")
        void testGetLoansByLoanTimeBetween() {
            // given
            LocalDate endDate = LocalDate.now().plusDays(1);
            List<Loan> loans = Collections.singletonList(loan);
            given(loanRepository.findLoansByLoanTimeBetween(loanDTO.getLoanTime(), endDate)).willReturn(loans);

            // when
            List<LoanDTO> result = loanService.getLoansByLoanTimeBetween(loanDTO.getLoanTime(), endDate);

            // then
            assertFalse(result.isEmpty());
            assertEquals(loanDTO.getLoanTime(), result.get(0).getLoanTime());
        }

        @Test
        @DisplayName("getAllLoan - ListNotFoundElementException 테스트")
        void testGetAllLoan_ThrowsListNotFoundElementException() {
            // given
            given(loanRepository.findAll()).willReturn(new ArrayList<>());

            // when & then
            assertThrows(ListNotFoundElementException.class, () -> loanService.getAllLoan());
        }

        @Test
        @DisplayName("getLoansByMemberId - ListNotFoundElementException 테스트")
        void testGetLoansByMemberId_ThrowsListNotFoundElementException() {
            // given
            given(loanRepository.findLoansByMemberId(loanDTO.getMemberId())).willReturn(new ArrayList<>());

            // when & then
            assertThrows(ListNotFoundElementException.class, () -> loanService.getLoansByMemberId(loanDTO.getMemberId()));
        }

        @Test
        @DisplayName("getLoansByBookId - ListNotFoundElementException 테스트")
        void testGetLoansByBookId_ThrowsListNotFoundElementException() {
            // given
            given(loanRepository.findLoansByBookId(loanDTO.getBookId())).willReturn(new ArrayList<>());

            // when & then
            assertThrows(ListNotFoundElementException.class, () -> loanService.getLoansByBookId(loanDTO.getBookId()));
        }

        @Test
        @DisplayName("getLoansByLoanTimeIsNotNullAndReturnTimeIsNull - ListNotFoundElementException 테스트")
        void testGetLoansByLoanTimeIsNotNullAndReturnTimeIsNull_ThrowsListNotFoundElementException() {
            // given
            given(loanRepository.findLoansByLoanTimeIsNotNullAndReturnTimeIsNull()).willReturn(new ArrayList<>());

            // when & then
            assertThrows(ListNotFoundElementException.class, () -> loanService.getLoansByLoanTimeIsNotNullAndReturnTimeIsNull());
        }

        @Test
        @DisplayName("getLoansByLoanTimeBetween - ListNotFoundElementException 테스트")
        void testGetLoansByLoanTimeBetween_ThrowsListNotFoundElementException() {
            // given
            LocalDate endDate = LocalDate.now().plusDays(1);
            given(loanRepository.findLoansByLoanTimeBetween(loanDTO.getLoanTime(), endDate)).willReturn(new ArrayList<>());

            // when & then
            assertThrows(ListNotFoundElementException.class, () -> loanService.getLoansByLoanTimeBetween(loanDTO.getLoanTime(), endDate));
        }
    }

    @Nested
    @DisplayName("POST 테스트")
    class Test_POST {

        @Test
        @DisplayName("createLoan 테스트")
        void testCreateLoan() {
            // given
            given(loanRepository.insert(loan)).willReturn(loan);

            // when
            LoanDTO result = loanService.createLoan(loanDTO);

            // then
            assertNotNull(result);
            assertEquals(loanDTO.getLoanId(), result.getLoanId());
        }
    }

    @Nested
    @DisplayName("PUT 테스트")
    class Test_PUT {

        @Test
        @DisplayName("updateLoan 테스트")
        void testUpdateLoan() {
            // given
            given(loanRepository.save(loan)).willReturn(loan);

            // when
            LoanDTO result = loanService.updateLoan(loanDTO);

            // then
            assertNotNull(result);
            assertEquals(loanDTO.getLoanId(), result.getLoanId());
        }
    }

    @Nested
    @DisplayName("DELETE 테스트")
    class Test_DELETE {

        @Test
        @DisplayName("deleteLoan 테스트")
        void testDeleteLoan() {
            // given
            willDoNothing().given(loanRepository).deleteByLoanId(loanDTO.getLoanId());

            // when
            loanService.deleteLoan(loanDTO.getLoanId());

            // then
            verify(loanRepository, times(1)).deleteByLoanId(loanDTO.getLoanId());
        }
    }
}