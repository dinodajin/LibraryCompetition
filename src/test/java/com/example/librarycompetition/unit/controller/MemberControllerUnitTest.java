package com.example.librarycompetition.unit.controller;

import com.example.librarycompetition.controller.MemberController;
import com.example.librarycompetition.dto.MemberDTO;
import com.example.librarycompetition.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.doNothing;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
@AutoConfigureMockMvc(addFilters = false)
public class MemberControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    private MemberDTO memberDTO;

    @BeforeEach
    @DisplayName("스텁 설정")
    void setUp() {
        // 테스트에 사용할 MemberDTO 객체를 초기화합니다.
        String memberId = "memberTest";
        String memberName = "John Doe";

        memberDTO = MemberDTO.of(memberId, memberName);
    }

    @Nested
    @DisplayName("GET 테스트")
    class Test_GET {

        @Test
        @DisplayName("getOneMember 테스트")
        void testGetOneMember() throws Exception {
            // given
            String memberId = "memberTest";
            given(memberService.getOneMember(memberId)).willReturn(memberDTO);

            // when & then
            mockMvc.perform(get("/member/get/" + memberId)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.memberId").value(memberId))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("getAllMembers 테스트")
        void testGetAllMembers() throws Exception {
            // given
            List<MemberDTO> memberList = Collections.singletonList(memberDTO);
            given(memberService.getAllMember()).willReturn(memberList);

            // when & then
            mockMvc.perform(get("/member/get/all")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$[0].memberId").value(memberDTO.getMemberId()))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("getMembersByMemberName 테스트")
        void testGetMembersByMemberName() throws Exception {
            // given
            String memberName = "John Doe";
            List<MemberDTO> memberList = Collections.singletonList(memberDTO);
            given(memberService.getMembersByMemberName(memberName)).willReturn(memberList);

            // when & then
            mockMvc.perform(get("/member/get/memberName/" + memberName)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$[0].memberName").value(memberName))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("POST 테스트")
    class Test_POST {

        @Test
        @DisplayName("createMember 테스트")
        void testCreateMember() throws Exception {
            // given
            given(memberService.createMember(memberDTO)).willReturn(memberDTO);

            // when & then
            mockMvc.perform(post("/member/create")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"memberId\": \"memberTest\", \"memberName\": \"John Doe\"}"))
                    .andExpect(jsonPath("$.memberId").value(memberDTO.getMemberId()))
                    .andExpect(status().isCreated());
        }
    }

    @Nested
    @DisplayName("PUT 테스트")
    class Test_PUT {

        @Test
        @DisplayName("updateMember 테스트")
        void testUpdateMember() throws Exception {
            // given
            given(memberService.updateMember(memberDTO)).willReturn(memberDTO);

            // when & then
            mockMvc.perform(put("/member/update")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"memberId\": \"memberTest\", \"memberName\": \"John Doe\"}"))
                    .andExpect(jsonPath("$.memberId").value(memberDTO.getMemberId()))
                    .andExpect(status().isAccepted());
        }
    }

    @Nested
    @DisplayName("DELETE 테스트")
    class Test_DELETE {

        @Test
        @DisplayName("deleteMember 테스트")
        void testDeleteMember() throws Exception {
            // given
            String memberId = "memberTest";
            doNothing().when(memberService).deleteMember(memberId);

            // when & then
            mockMvc.perform(delete("/member/delete/" + memberId)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent());

            then(memberService).should().deleteMember(memberId);
        }
    }
}