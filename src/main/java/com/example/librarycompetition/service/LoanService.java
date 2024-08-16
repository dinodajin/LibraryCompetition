package com.example.librarycompetition.service;

import com.example.librarycompetition.dto.LoanDTO;
import com.example.librarycompetition.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;

    @Transactional
    public LoanDTO getOneLoan(String loanId) {
    }

    @Transactional
    public List<LoanDTO> getAllLoan() {
    }

    @Transactional
    public List<LoanDTO> getLoansByMemberId(String memberId) {
    }

    @Transactional
    public List<LoanDTO> getLoansByBookId(String bookId) {
    }

    @Transactional
    public List<LoanDTO> getLoansByLoanTimeIsNotNullAndReturnTimeIsNull() {
    }

    @Transactional
    public HttpStatusCode getLoansByLoanTimeBetween(LocalDate startDate, LocalDate endDate) {
    }

    @Transactional
    public HttpStatusCode createLoan(LoanDTO loanDTO) {
    }

    @Transactional
    public HttpStatusCode updateLoan(LoanDTO loanDTO) {
    }

    @Transactional
    public HttpStatusCode deleteLoan(String loanId) {
        return null;
    }

}
