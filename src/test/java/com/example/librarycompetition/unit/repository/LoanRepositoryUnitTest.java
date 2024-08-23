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
        loan.setLoanId("1");
        loan.setLoanTime(LocalDate.of(2024, 8, 21));
        loan.setReturnTime(LocalDate.of(2024, 8, 21));
        // testFindLoansByLoanTimeIsNotNullAndReturnTimeIsNull() 검사시 : loan.setReturnTime(null);
        loan.setDeclaration("책이 손상됐어요!");
        loan.setMemberId("1");
        loan.setBookId("1");
        loanRepository.save(loan);
    }

    @Nested
    @DisplayName("READ 테스트")
    class Test_READ {

        @Test
        @DisplayName("findByLoanId 테스트")
        void testFindByLoanId() {
            // when
            Optional<Loan> result = loanRepository.findByLoanId(loan.getLoanId());
            System.out.println("Result: " + result); // 결과 출력

            // then
            assertTrue(result.isPresent());
            assertEquals(loan.getLoanId(), result.get().getLoanId());
        }

        @Test
        @DisplayName("findLoansByMemberId 테스트")
        void testFindLoansByMemberId() {
            // when
            List<Loan> result = loanRepository.findLoansByMemberId(loan.getMemberId());

            // then
            assertFalse(result.isEmpty());
            assertEquals(loan.getMemberId(), result.get(0).getMemberId());
        }

        @Test
        @DisplayName("findLoansByBookId 테스트")
        void testFindLoansByBookId() {
            // when
            List<Loan> result = loanRepository.findLoansByBookId(loan.getBookId());

            // then
            assertFalse(result.isEmpty());
            assertEquals(loan.getBookId(), result.get(0).getBookId());
        }

        @Test
        @DisplayName("findLoansByLoanTimeIsNotNullAndReturnTimeIsNull 테스트")
        void testFindLoansByLoanTimeIsNotNullAndReturnTimeIsNull() {
            // when
            List<Loan> result = loanRepository.findLoansByLoanTimeIsNotNullAndReturnTimeIsNull();

            // then
            assertFalse(result.isEmpty());
            assertTrue(result.stream().allMatch(l -> l.getReturnTime() == null));
        }

        @Test
        @DisplayName("findLoansByLoanTimeBetween 테스트")
        void testFindLoansByLoanTimeBetween() {
            // when
            List<Loan> result = loanRepository.findLoansByLoanTimeBetween(LocalDate.of(2024, 8, 20), LocalDate.of(2024, 8, 22));

            // then
            assertFalse(result.isEmpty());
            assertTrue(result.stream().allMatch(l -> !l.getLoanTime().isBefore(LocalDate.of(2024, 8, 20)) && !l.getLoanTime().isAfter(LocalDate.of(2024, 8, 22))));
        }
    }

    @Nested
    @DisplayName("DELETE 테스트")
    class Test_DELETE {

        @Test
        @DisplayName("deleteByLoanId 테스트")
        void testDeleteByLoanId() {
            // when
            loanRepository.deleteByLoanId(loan.getLoanId());

            // then
            Optional<Loan> result = loanRepository.findByLoanId(loan.getLoanId());
            assertFalse(result.isPresent());
        }
    }
}