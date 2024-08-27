package com.example.librarycompetition.repository;

import com.example.librarycompetition.domain.Loan;
import com.example.librarycompetition.domain.Member;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends MongoRepository<Member, String> {

    // READ
    Optional<Member> findByMemberId(String memberId);
    List<Member> findByMemberNameContaining(String memberName);
    List<Member> findByMemberWarning(String memberWarning);

    // CREATE

    // UPDATE

    // DELETE
    void deleteById(String memberId);
}
