package pl.edu.pja.gdansk.voyage2.security.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.edu.pja.gdansk.voyage2.Application;
import pl.edu.pja.gdansk.voyage2.BaseControllerTest;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class LogoutSecurityControllerTest extends BaseControllerTest {

    @Test
    public void logout() throws Exception {
        //when//then
        this.mockMvc
                .perform(
                        delete("/user/token").contentType(MediaType.APPLICATION_JSON_UTF8)
                                .header("x-auth-token", "0dc1f6e1-c7f1-41ac-8ce2-32b6b3e57aa3")
                                .with(httpBasic("test", "aaa"))
                )
                .andExpect(status().is(HttpStatus.NO_CONTENT.value()))
                .andDo(document("security-logout"))
        ;
    }
}
