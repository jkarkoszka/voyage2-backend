package pl.edu.pja.gdansk.voyage2.security.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.edu.pja.gdansk.voyage2.Application;
import pl.edu.pja.gdansk.voyage2.BaseControllerTest;
import pl.edu.pja.gdansk.voyage2.user.domain.User;
import pl.edu.pja.gdansk.voyage2.user.repository.UserRepository;
import pl.edu.pja.gdansk.voyage2.user.request.RegisterUserRequest;
import pl.edu.pja.gdansk.voyage2.user.service.ActivateUser;
import pl.edu.pja.gdansk.voyage2.user.service.RegisterUser;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class LoginSecurityControllerTest extends BaseControllerTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RegisterUser registerUser;
    @Autowired
    private ActivateUser activateUser;

    @Before
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void login() throws Exception {
        //given
        RegisterUserRequest request = new RegisterUserRequest();
        request.setUsername("test");
        request.setEmail("test@example.com");
        request.setPassword("aaa");
        request.setPublic(true);
        User user = registerUser.register(request);
        activateUser.activate(user.getActivationToken());
        //when//then
        this.mockMvc
            .perform(
                post("/user/token").contentType(MediaType.APPLICATION_JSON_UTF8).header("Authorization", "Basic dGVzdDphYWE=")
            )
            .andExpect(status().is(HttpStatus.OK.value()))
            .andExpect(jsonPath("$.token").isNotEmpty())
            .andExpect(jsonPath("[?($.username == 'test')]").exists())
            .andExpect(jsonPath("[?($.passwordStatus == 'NORMAL')]").exists())
            .andDo(document("security-login"))
        ;
    }
}
