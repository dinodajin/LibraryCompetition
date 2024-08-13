package com.example.librarycompetition.controller;

import com.example.librarycompetition.dto.LoanDTO;
import com.example.librarycompetition.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/loan")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @GetMapping("")
    public ResponseEntity<LoanDTO> getOneLoan(String loanId) {
        return new ResponseEntity<>(loanService.getOneLoan(loanId), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<LoanDTO>> getAllLoan() {
        return new ResponseEntity<>(loanService.getAllLoan(), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<LoanDTO>> getLoansByMemberId(String memberId) {
        return new ResponseEntity<>(loanService.getLoansByMemberId(memberId), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<LoanDTO>> getLoansByBookId(String bookId) {
        return new ResponseEntity<>(loanService.getLoansByBookId(bookId), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<LoanDTO>> getLoansByLoanTimeIsNotNullAndReturnTimeIsNull() {
        return new ResponseEntity<>(loanService.getLoansByLoanTimeIsNotNullAndReturnTimeIsNull(), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<LoanDTO>> getLoansByLoanTimeBetween(LocalDate startDate, LocalDate endDate) {
        return new ResponseEntity<>(loanService.getLoansByLoanTimeBetween(startDate, endDate));
    }

    @PostMapping("")
    public ResponseEntity<LoanDTO> createLoan(LoanDTO loanDTO) {
        return new ResponseEntity<>(loanService.createLoan(loanDTO));
    }

    @PutMapping("")
    public ResponseEntity<LoanDTO> updateLoan(LoanDTO loanDTO) {
        return new ResponseEntity<>(loanService.updateLoan(loanDTO));
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteLoan(String loanId) {
        return new ResponseEntity<>(loanService.deleteLoan(loanId));
    }

}
