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
}