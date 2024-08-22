package com.example.librarycompetition.unit.service;

import com.example.librarycompetition.domain.Member;
import com.example.librarycompetition.dto.MemberDTO;
import com.example.librarycompetition.exception.ListNotFoundElementException;
import com.example.librarycompetition.exception.ResourceNotFoundException;
import com.example.librarycompetition.repository.MemberRepository;
import com.example.librarycompetition.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
        String memberId = "testMemberId";
        String memberName = "testMemberName";

        memberDTO = MemberDTO.of(memberId, memberName);
        member = memberDTO.toEntity();
    }

    @Nested
    @DisplayName("GET 테스트")
    class Test_GET {

        @Test
        @DisplayName("getOneMember 테스트")
        void testGetOneMember() {
            // given
            given(memberRepository.findByMemberId(memberDTO.getMemberId())).willReturn(Optional.of(member));

            // when
            MemberDTO result = memberService.getOneMember(memberDTO.getMemberId());

            // then
            assertNotNull(result);
            assertEquals(memberDTO.getMemberId(), result.getMemberId());
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
            assertEquals(memberDTO.getMemberId(), result.get(0).getMemberId());
        }

        @Test
        @DisplayName("getMembersByMemberName 테스트")
        void testGetMembersByMemberName() {
            // given
            List<Member> members = Collections.singletonList(member);
            given(memberRepository.findByMemberName(memberDTO.getMemberName())).willReturn(members);

            // when
            List<MemberDTO> result = memberService.getMembersByMemberName(memberDTO.getMemberName());

            // then
            assertFalse(result.isEmpty());
            assertEquals(memberDTO.getMemberName(), result.get(0).getMemberName());
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
            given(memberRepository.findByMemberName(memberDTO.getMemberName())).willReturn(new ArrayList<>());

            // when & then
            assertThrows(ListNotFoundElementException.class, () -> memberService.getMembersByMemberName(memberDTO.getMemberName()));
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
            assertEquals(memberDTO.getMemberId(), result.getMemberId());
        }
    }
}