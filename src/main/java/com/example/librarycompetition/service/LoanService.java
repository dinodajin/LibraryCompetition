package com.example.librarycompetition.service;

import com.example.librarycompetition.dto.LoanDTO;
import com.example.librarycompetition.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;

    public LoanDTO getOneLoan(String loanId) {
    }

    public List<LoanDTO> getAllLoan() {
    }

    public List<LoanDTO> getLoansByMemberId(String memberId) {
    }

    public List<LoanDTO> getLoansByBookId(String bookId) {
    }

    public List<LoanDTO> getLoansByLoanTimeIsNotNullAndReturnTimeIsNull() {
    }

    public HttpStatusCode getLoansByLoanTimeBetween(LocalDate startDate, LocalDate endDate) {
    }

    public HttpStatusCode createLoan(LoanDTO loanDTO) {
    }

    public HttpStatusCode updateLoan(LoanDTO loanDTO) {
    }

    public HttpStatusCode deleteLoan(String loanId) {
        return null;
    }
}
