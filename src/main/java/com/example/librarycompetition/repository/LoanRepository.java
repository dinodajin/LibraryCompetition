package com.example.librarycompetition.repository;

import com.example.librarycompetition.domain.Loan;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LoanRepository extends MongoRepository<Loan, String> {

    // READ
    Optional<Loan> findByLoanId(String loanId);
    List<Loan> findLoansByMemberId(String memberId);
    List<Loan> findLoansByBookId(String bookId);
    List<Loan> findLoansByLoanTimeIsNotNullAndReturnTimeIsNull();
    List<Loan> findLoansByLoanTimeBetween(LocalDate startDate, LocalDate endDate);

    // CREATE

    // UPDATE

    // DELETE
    void deleteByLoanId(String loanId);
}
