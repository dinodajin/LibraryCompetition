package com.example.librarycompetition.controller;

import com.example.librarycompetition.dto.LoanDTO;
import com.example.librarycompetition.service.LoanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/loan")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @GetMapping("/get/{loanId}")
    public ResponseEntity<LoanDTO> getOneLoan(@PathVariable String loanId) {
        return new ResponseEntity<>(loanService.getOneLoan(loanId), HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<LoanDTO>> getAllLoan() {
        return new ResponseEntity<>(loanService.getAllLoan(), HttpStatus.OK);
    }

    @GetMapping("/get/memberId/{memberId}")
    public ResponseEntity<List<LoanDTO>> getLoansByMemberId(@PathVariable String memberId) {
        return new ResponseEntity<>(loanService.getLoansByMemberId(memberId), HttpStatus.OK);
    }

    @GetMapping("/get/bookId/{bookId}")
    public ResponseEntity<List<LoanDTO>> getLoansByBookId(@PathVariable String bookId) {
        return new ResponseEntity<>(loanService.getLoansByBookId(bookId), HttpStatus.OK);
    }

    @GetMapping("/get/currentLoan")
    public ResponseEntity<List<LoanDTO>> getLoansByLoanTimeIsNotNullAndReturnTimeIsNull() {
        return new ResponseEntity<>(loanService.getLoansByLoanTimeIsNotNullAndReturnTimeIsNull(), HttpStatus.OK);
    }

    @GetMapping("/get/loanTime")
    public ResponseEntity<List<LoanDTO>> getLoansByLoanTimeBetween(@RequestParam LocalDate startDate,
                                                                   @RequestParam LocalDate endDate) {
        return new ResponseEntity<>(loanService.getLoansByLoanTimeBetween(startDate, endDate), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<LoanDTO> createLoan(@RequestBody LoanDTO loanDTO) {
        return new ResponseEntity<>(loanService.createLoan(loanDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<LoanDTO> updateLoan(@RequestBody LoanDTO loanDTO) {
        return new ResponseEntity<>(loanService.updateLoan(loanDTO), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteLoan(@RequestParam String loanId) {
        loanService.deleteLoan(loanId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
