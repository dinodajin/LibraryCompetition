package com.example.librarycompetition.controller;

import com.example.librarycompetition.dto.MemberDTO;
import com.example.librarycompetition.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/get/{memberId}")
    public ResponseEntity<MemberDTO> getOneMember(@PathVariable String memberId) {
        return new ResponseEntity<>(memberService.getOneMember(memberId), HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<MemberDTO>> getAllMembers() {
        return new ResponseEntity<>(memberService.getAllMember(), HttpStatus.OK);
    }

    @GetMapping("/get/memberName/{memberName}")
    public ResponseEntity<List<MemberDTO>> getMembersByMemberName(@PathVariable String memberName) {
        return new ResponseEntity<>(memberService.getMembersByMemberName(memberName), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<MemberDTO> createMember(@RequestBody MemberDTO memberDTO) {
        return new ResponseEntity<>(memberService.createMember(memberDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<MemberDTO> updateMember(@RequestBody MemberDTO memberDTO) {
        return new ResponseEntity<>(memberService.updateMember(memberDTO), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable String memberId) {
        memberService.deleteMember(memberId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
