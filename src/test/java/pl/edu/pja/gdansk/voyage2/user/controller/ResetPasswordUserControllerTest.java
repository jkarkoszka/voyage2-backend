package pl.edu.pja.gdansk.voyage2.user.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.edu.pja.gdansk.voyage2.Application;
import pl.edu.pja.gdansk.voyage2.BaseControllerTest;
import pl.edu.pja.gdansk.voyage2.user.repository.UserRepository;
import pl.edu.pja.gdansk.voyage2.user.request.RegisterUserRequest;
import pl.edu.pja.gdansk.voyage2.user.request.ResetPasswordRequest;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class ResetPasswordUserControllerTest extends BaseControllerTest {

    @Test
    public void userResetPassword() throws Exception {
        //given
        ResetPasswordRequest request = new ResetPasswordRequest();
        request.setEmail("test@example.com");
        //when//then
        this.mockMvc
                .perform(
                        post("/user/password")
                            .contentType(MediaType.APPLICATION_JSON_UTF8)
                            .content(
                                this.objectMapper.writeValueAsString(request)
                            )
                )
                .andExpect(status().isNoContent())
                .andDo(document("user-reset-password"))
        ;
    }
}
