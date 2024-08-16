package com.example.librarycompetition.service;

import com.example.librarycompetition.domain.Loan;
import com.example.librarycompetition.dto.LoanDTO;
import com.example.librarycompetition.exception.ListNotFoundElementException;
import com.example.librarycompetition.exception.ResourceNotFoundException;
import com.example.librarycompetition.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;

    @Transactional
    public LoanDTO getOneLoan(String loanId) {
        return LoanDTO.from(loanRepository.findById(loanId).orElseThrow(ResourceNotFoundException::new));
    }

    @Transactional
    public List<LoanDTO> getAllLoan() {
        List<Loan> loans = loanRepository.findAll();

        if (loans.isEmpty()) {
            throw new ListNotFoundElementException();
        }

        List<LoanDTO> loanDTOs = new ArrayList<>();
        for (Loan loan : loans) {
            loanDTOs.add(LoanDTO.from(loan));
        }

        return loanDTOs;
    }

    @Transactional
    public List<LoanDTO> getLoansByMemberId(String memberId) {
        List<Loan> loans = loanRepository.findLoansByMemberId(memberId);

        if (loans.isEmpty()) {
            throw new ListNotFoundElementException();
        }

        List<LoanDTO> loanDTOs = new ArrayList<>();
        for (Loan loan : loans) {
            loanDTOs.add(LoanDTO.from(loan));
        }

        return loanDTOs;
    }

    @Transactional
    public List<LoanDTO> getLoansByBookId(String bookId) {
        List<Loan> loans = loanRepository.findLoansByBookId(bookId);

        if (loans.isEmpty()) {
            throw new ListNotFoundElementException();
        }

        List<LoanDTO> loanDTOs = new ArrayList<>();
        for (Loan loan : loans) {
            loanDTOs.add(LoanDTO.from(loan));
        }

        return loanDTOs;
    }

    @Transactional
    public List<LoanDTO> getLoansByLoanTimeIsNotNullAndReturnTimeIsNull() {
        List<Loan> loans = loanRepository.findLoansByLoanTimeIsNotNullAndReturnTimeIsNull();

        if (loans.isEmpty()) {
            throw new ListNotFoundElementException();
        }

        List<LoanDTO> loanDTOs = new ArrayList<>();
        for (Loan loan : loans) {
            loanDTOs.add(LoanDTO.from(loan));
        }

        return loanDTOs;
    }

    @Transactional
    public List<LoanDTO> getLoansByLoanTimeBetween(LocalDate startDate, LocalDate endDate) {
        List<Loan> loans = loanRepository.findLoansByLoanTimeBetween(startDate, endDate);

        if (loans.isEmpty()) {
            throw new ListNotFoundElementException();
        }

        List<LoanDTO> loanDTOs = new ArrayList<>();
        for (Loan loan : loans) {
            loanDTOs.add(LoanDTO.from(loan));
        }

        return loanDTOs;
    }

    @Transactional
    public LoanDTO createLoan(LoanDTO loanDTO) {
        return LoanDTO.from(loanRepository.insert(loanDTO.toEntity()));
    }

    @Transactional
    public LoanDTO updateLoan(LoanDTO loanDTO) {
        return LoanDTO.from(loanRepository.save(loanDTO.toEntity()));
    }

    @Transactional
    public void deleteLoan(String loanId) {
        loanRepository.deleteByLoanId(loanId);
    }

}
