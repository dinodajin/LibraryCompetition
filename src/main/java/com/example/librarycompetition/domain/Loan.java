package com.example.librarycompetition.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "loan")
public class Loan {

    @Id
    private String loanId;

    private LocalDate loanTime;
    private LocalDate returnTime;
    private String declaration;
    private String memberId;
    private String bookId;

}
