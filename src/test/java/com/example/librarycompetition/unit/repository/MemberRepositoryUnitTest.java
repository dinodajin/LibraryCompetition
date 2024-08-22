package com.example.librarycompetition.unit.repository;

import com.example.librarycompetition.domain.Member;
import com.example.librarycompetition.repository.MemberRepository;
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
public class MemberRepositoryUnitTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private Member member;

    @BeforeEach
    @DisplayName("테스트 데이터 준비")
    void setUp() {
        mongoTemplate.dropCollection(Member.class);

        member = new Member();
        member.setMemberId("1");
        member.setMemberName("가나다");
        member.setMemberBirth(LocalDate.of(2024, 8, 21));
        member.setMemberPhoneNumber("010-1111-2222");
        member.setMemberWarning("정상");
        member.setMemberDamageCount(1);

        memberRepository.save(member);
    }

    @Nested
    @DisplayName("READ 테스트")
    class Test_READ {

        @Test
        @DisplayName("findByMemberId 테스트")
        void testFindByMemberId() {
            // when
            Optional<Member> result = memberRepository.findByMemberId(member.getMemberId());

            // then
            assertTrue(result.isPresent());
            assertEquals(member.getMemberId(), result.get().getMemberId());
        }

        @Test
        @DisplayName("findByMemberName 테스트")
        void testFindByMemberName() {
            // when
            List<Member> result = memberRepository.findByMemberName(member.getMemberName());

            // then
            assertFalse(result.isEmpty());
            assertEquals(member.getMemberName(), result.get(0).getMemberName());
        }
    }

    @Nested
    @DisplayName("DELETE 테스트")
    class Test_DELETE {

        @Test
        @DisplayName("deleteById 테스트")
        void testDeleteById() {
            // when
            memberRepository.deleteById(member.getMemberId());

            // then
            Optional<Member> result = memberRepository.findByMemberId(member.getMemberId());
            assertFalse(result.isPresent());
        }
    }
}