package com.example.librarycompetition.dto;

import com.example.librarycompetition.domain.Loan;

import java.time.LocalDate;

public record LoanDTO(
        String loanId,
        LocalDate loanTime,
        LocalDate returnTime,
        String memberId,
        String bookId
) {

    public static LoanDTO of(String loanId, LocalDate loanTime, LocalDate returnTime, String memberId, String bookId) {
        return new LoanDTO(loanId, loanTime, returnTime, memberId, bookId);
    }

    public static LoanDTO fromLoan(Loan loan) {
        return new LoanDTO(
                loan.getLoanId(),
                loan.getLoanTime(),
                loan.getReturnTime(),
                loan.getMemberId(),
                loan.getBookId()
        );
    }

    public Loan toEntity() {
        return Loan.builder()
                .loanId(loanId)
                .loanTime(loanTime)
                .returnTime(returnTime)
                .memberId(memberId)
                .bookId(bookId)
                .build();
    }

}
