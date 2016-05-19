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

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class RegisterUserUserControllerTest extends BaseControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void userRegister() throws Exception {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setUsername("test");
        request.setEmail("test@example.com");
        request.setPassword("password");
        request.setPublic(true);
        this.mockMvc
                .perform(
                        post("/user")
                            .contentType(MediaType.APPLICATION_JSON_UTF8)
                            .content(
                                this.objectMapper.writeValueAsString(request)
                            )
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("[?($.username == 'test')]").exists())
                .andExpect(jsonPath("[?($.email == 'test@example.com')]").exists())
                .andExpect(jsonPath("[?($.role == 'USER')]").exists())
                .andExpect(jsonPath("[?($.public == true)]").exists())
                .andExpect(jsonPath("[?($.passwordStatus == 'NORMAL')]").exists())
                .andExpect(jsonPath("[?($.avatar == null)]").exists())
                .andExpect(jsonPath("$.password").doesNotExist())
        ;
    }
}
