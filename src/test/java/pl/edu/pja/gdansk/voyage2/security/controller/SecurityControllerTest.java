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
import pl.edu.pja.gdansk.voyage2.user.repository.UserRepository;
import pl.edu.pja.gdansk.voyage2.user.request.RegisterUserRequest;
import pl.edu.pja.gdansk.voyage2.user.service.RegisterUser;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class SecurityControllerTest extends BaseControllerTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RegisterUser registerUser;

    @Before
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void userTokenCreate() throws Exception {
        //given
        RegisterUserRequest request = new RegisterUserRequest();
        request.setEmail("test@example.com");
        request.setPassword("aaa");
        registerUser.createUser(request);

        //when//then
        this.mockMvc
            .perform(
                post("/user/token").contentType(MediaType.APPLICATION_JSON_UTF8).header("Authorization", "Basic dGVzdEBleGFtcGxlLmNvbTphYWE=")
            )
            .andExpect(status().is(HttpStatus.OK.value()))
            .andExpect(jsonPath("$.token").isNotEmpty())
            .andExpect(jsonPath("[?($.username == 'test@example.com')]").exists())
        ;
    }
}
