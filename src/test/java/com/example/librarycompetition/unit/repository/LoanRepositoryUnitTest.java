package com.example.librarycompetition.unit.repository;

import com.example.librarycompetition.domain.Loan;
import com.example.librarycompetition.repository.LoanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
public class LoanRepositoryUnitTest {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private Loan loan;

    @BeforeEach
    @DisplayName("테스트 데이터 준비")
    void setUp() {
        mongoTemplate.dropCollection(Loan.class);

        loan = new Loan();
        loan.setLoanId("testLoanId");
        loan.setMemberId("testMemberId");
        loan.setBookId("testBookId");
        loan.setLoanTime(LocalDate.of(2024, 8, 21));
        loan.setReturnTime(null); // Set to null to test active loans

        loanRepository.save(loan);
    }
}