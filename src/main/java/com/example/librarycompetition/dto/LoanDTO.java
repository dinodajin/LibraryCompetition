package com.example.librarycompetition.dto;

import com.example.librarycompetition.domain.Loan;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record LoanDTO(
        @Schema(example = "1")
        String loanId,
        @Schema(example = "2024-08-21")
        LocalDate loanTime,
        @Schema(example = "2024-08-21")
        LocalDate returnTime,
        @Schema(example = "책이 손상됐어요!")
        String declaration,
        @Schema(example = "훼손")
        String damageDegree,
        @Schema(example = "1")
        String memberId,
        @Schema(example = "1")
        String bookId
) {

    public static LoanDTO of(String loanId, LocalDate loanTime, LocalDate returnTime,
                             String declaration, String damageDegree, String memberId, String bookId) {
        return new LoanDTO(loanId, loanTime, returnTime, declaration, damageDegree, memberId, bookId);
    }

    public static LoanDTO from(Loan loan) {
        return new LoanDTO(
                loan.getLoanId(),
                loan.getLoanTime(),
                loan.getReturnTime(),
                loan.getDeclaration(),
                loan.getDamageDegree(),
                loan.getMemberId(),
                loan.getBookId()
        );
    }

    public Loan toEntity() {
        return Loan.builder()
                .loanId(loanId)
                .loanTime(loanTime)
                .returnTime(returnTime)
                .declaration(declaration)
                .damageDegree(damageDegree)
                .memberId(memberId)
                .bookId(bookId)
                .build();
    }

}
