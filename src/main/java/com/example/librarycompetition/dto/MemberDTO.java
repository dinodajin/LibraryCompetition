package com.example.librarycompetition.dto;

import com.example.librarycompetition.domain.Member;

import java.time.LocalDate;

public record MemberDTO(
        String memberId,
        String memberName,
        LocalDate memberBirth,
        String memberPhoneNumber,
        String memberWarning,
        Integer memberDamageCount
) {

    public static MemberDTO of(String memberId, String memberName, LocalDate memberBirth, String memberPhoneNumber, String memberWarning, Integer memberDamageCount) {
        return new MemberDTO(memberId, memberName, memberBirth, memberPhoneNumber, memberWarning, memberDamageCount);
    }

    public static MemberDTO from(Member member) {
        return new MemberDTO(
                member.getMemberId(),
                member.getMemberName(),
                member.getMemberBirth(),
                member.getMemberPhoneNumber(),
                member.getMemberWarning(),
                member.getMemberDamageCount()
        );
    }

    public Member toEntity() {
        return Member.builder()
                .memberId(memberId)
                .memberName(memberName)
                .memberBirth(memberBirth)
                .memberPhoneNumber(memberPhoneNumber)
                .memberWarning(memberWarning)
                .memberDamageCount(memberDamageCount)
                .build();
    }

}
