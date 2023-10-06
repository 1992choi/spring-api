package choi.web.api.test.testing;

import choi.web.api.domain.Member;
import choi.web.api.service.MemberService;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TestingController.class)
@MockBean(JpaMetamodelMappingContext.class)
class ControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @Test
    @DisplayName("회원 조회")
    void findMembers() throws Exception {
        // given
        Long fakeMemberId = 1L;
        Member member = Member.builder()
                .memberId(1L)
                .name("CHOI")
                .build();

        // mocking
        given(memberService.findMember(fakeMemberId)).willReturn(member);

        // when
        String memberId = "1";
        ResultActions actions = mockMvc.perform(
                get("/test/testing/members/{memberId}", memberId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.memberId").value(1))
                .andExpect(jsonPath("$.name").value("CHOI"))
                .andDo(print());
    }

}