package com.example.librarycompetition.unit.service;

import com.example.librarycompetition.domain.Member;
import com.example.librarycompetition.dto.MemberDTO;
import com.example.librarycompetition.exception.ListNotFoundElementException;
import com.example.librarycompetition.exception.ResourceNotFoundException;
import com.example.librarycompetition.repository.MemberRepository;
import com.example.librarycompetition.service.MemberService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

public class MemberServiceUnitTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    private MemberDTO memberDTO;
    private Member member;

    @BeforeEach
    @DisplayName("스텁 설정")
    void setUp() {
        MockitoAnnotations.openMocks(this);
        String memberId = "1";
        String memberName = "가나다";
        LocalDate memberBirth = LocalDate.of(2024, 8, 21);
        String memberPhoneNumber = "010-1111-2222";
        String memberWarning = "정상";
        Integer memberDamageCount = 1;
        memberDTO = MemberDTO.of(memberId, memberName, memberBirth, memberPhoneNumber, memberWarning, memberDamageCount);
        member = memberDTO.toEntity();
    }

    @Nested
    @DisplayName("GET 테스트")
    class Test_GET {

        @Test
        @DisplayName("getOneMember 테스트")
        void testGetOneMember() {
            // given
            given(memberRepository.findByMemberId(memberDTO.memberId())).willReturn(Optional.of(member));

            // when
            MemberDTO result = memberService.getOneMember(memberDTO.memberId());

            // then
            assertNotNull(result);
            assertEquals(memberDTO.memberId(), result.memberId());
        }

        @Test
        @DisplayName("getAllMember 테스트")
        void testGetAllMember() {
            // given
            List<Member> members = Collections.singletonList(member);
            given(memberRepository.findAll()).willReturn(members);

            // when
            List<MemberDTO> result = memberService.getAllMember();

            // then
            assertFalse(result.isEmpty());
            assertEquals(memberDTO.memberId(), result.get(0).memberId());
        }

        @Test
        @DisplayName("getMembersByMemberName 테스트")
        void testGetMembersByMemberName() {
            // given
            List<Member> members = Collections.singletonList(member);
            given(memberRepository.findByMemberName(memberDTO.memberName())).willReturn(members);

            // when
            List<MemberDTO> result = memberService.getMembersByMemberName(memberDTO.memberName());

            // then
            assertFalse(result.isEmpty());
            assertEquals(memberDTO.memberName(), result.get(0).memberName());
        }

        @Test
        @DisplayName("getAllMember - ListNotFoundElementException 테스트")
        void testGetAllMember_ThrowsListNotFoundElementException() {
            // given
            given(memberRepository.findAll()).willReturn(new ArrayList<>());

            // when & then
            assertThrows(ListNotFoundElementException.class, () -> memberService.getAllMember());
        }

        @Test
        @DisplayName("getMembersByMemberName - ListNotFoundElementException 테스트")
        void testGetMembersByMemberName_ThrowsListNotFoundElementException() {
            // given
            given(memberRepository.findByMemberName(memberDTO.memberName())).willReturn(new ArrayList<>());

            // when & then
            assertThrows(ListNotFoundElementException.class, () -> memberService.getMembersByMemberName(memberDTO.memberName()));
        }
    }

    @Nested
    @DisplayName("POST 테스트")
    class Test_POST {

        @Test
        @DisplayName("createMember 테스트")
        void testCreateMember() {
            // given
            given(memberRepository.insert(member)).willReturn(member);

            // when
            MemberDTO result = memberService.createMember(memberDTO);

            // then
            assertNotNull(result);
            assertEquals(memberDTO.memberId(), result.memberId());
        }
    }

    @Nested
    @DisplayName("PUT 테스트")
    class Test_PUT {

        @Test
        @DisplayName("updateMember 테스트")
        void testUpdateMember() {
            // given
            given(memberRepository.save(member)).willReturn(member);

            // when
            MemberDTO result = memberService.updateMember(memberDTO);

            // then
            assertNotNull(result);
            assertEquals(memberDTO.memberId(), result.memberId());
        }
    }

    @Nested
    @DisplayName("DELETE 테스트")
    class Test_DELETE {

        @Test
        @DisplayName("deleteMember 테스트")
        void testDeleteMember() {
            // given
            willDoNothing().given(memberRepository).deleteById(memberDTO.memberId());

            // when
            memberService.deleteMember(memberDTO.memberId());

            // then
            verify(memberRepository, times(1)).deleteById(memberDTO.memberId());
        }
    }
}