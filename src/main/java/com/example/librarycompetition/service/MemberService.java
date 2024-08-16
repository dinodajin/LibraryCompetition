package com.example.librarycompetition.service;

import com.example.librarycompetition.domain.Member;
import com.example.librarycompetition.dto.MemberDTO;
import com.example.librarycompetition.exception.ListNotFoundElementException;
import com.example.librarycompetition.exception.ResourceNotFoundException;
import com.example.librarycompetition.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private MemberRepository memberRepository;


    @Transactional
    public MemberDTO getOneMember(String memberId) {
        return MemberDTO.from(memberRepository.findByMemberId(memberId).orElseThrow(ResourceNotFoundException::new));
    }

    @Transactional
    public List<MemberDTO> getAllMember() {
        List<Member> members = memberRepository.findAll();

        if (members.isEmpty()) {
            throw new ListNotFoundElementException();
        }

        List<MemberDTO> memberDTOs = new ArrayList<>();
        for (Member member : members) {
            memberDTOs.add(MemberDTO.from(member));
        }

        return memberDTOs;
    }

    @Transactional
    public List<MemberDTO> getMembersByMemberName(String memberName) {

    }

    @Transactional
    public MemberDTO createMember(MemberDTO memberDTO) {
    }

    @Transactional
    public MemberDTO updateMember(MemberDTO memberDTO) {
    }

    @Transactional
    public Void deleteMember(String memberId) {
    }

}
