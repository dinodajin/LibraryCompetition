package com.example.librarycompetition.service;

import com.example.librarycompetition.dto.MemberDTO;
import com.example.librarycompetition.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private MemberRepository memberRepository;


    public MemberDTO getOneMember(String memberId) {
        return memberRepository.findByMemberId(memberId).orElseThrow();
    }

    public List<MemberDTO> getAllMember() {
        return memberRepository.findAll()
    }

    public List<MemberDTO> getMembersByMemberName(String memberName) {
    }

    public MemberDTO createMember(MemberDTO memberDTO) {
    }

    public MemberDTO updateMember(MemberDTO memberDTO) {
    }

    public Void deleteMember(String memberId) {
    }
}
