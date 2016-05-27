package pl.edu.pja.gdansk.voyage2.user.controller;

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
import pl.edu.pja.gdansk.voyage2.user.domain.User;
import pl.edu.pja.gdansk.voyage2.user.repository.UserRepository;
import pl.edu.pja.gdansk.voyage2.user.request.ChangePasswordRequest;
import pl.edu.pja.gdansk.voyage2.user.request.ChangeSosEmailRequest;
import pl.edu.pja.gdansk.voyage2.user.service.ChangeSosEmail;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class ChangeSosEmailUserControllerTest extends BaseControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void changeSosEmail() throws Exception {
        //given
        ChangeSosEmailRequest request = new ChangeSosEmailRequest();
        request.setEmail("noreply@example.com");
        User user = userRepository.findByUsername(activatedUser.getUsername());
        //when//then
        this.mockMvc
                .perform(
                        patch("/user/{userId}/sosEmail", user.getId())
                                .with(httpBasic("test", "aaa"))
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(
                                        this.objectMapper.writeValueAsString(request)
                                )
                )
                .andExpect(status().isNoContent())
                .andDo(document("user-change-sos-email"))
        ;
    }
}