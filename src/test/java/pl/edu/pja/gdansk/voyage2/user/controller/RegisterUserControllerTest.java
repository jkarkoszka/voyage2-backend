package pl.edu.pja.gdansk.voyage2.user.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.edu.pja.gdansk.voyage2.Application;
import pl.edu.pja.gdansk.voyage2.BaseControllerTest;
import pl.edu.pja.gdansk.voyage2.user.repository.UserRepository;
import pl.edu.pja.gdansk.voyage2.user.request.RegisterUserRequest;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class RegisterUserControllerTest extends BaseControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void userRegister() throws Exception {
        //given
        RegisterUserRequest request = new RegisterUserRequest();
        request.setUsername("test");
        request.setEmail("test@example.com");
        request.setPassword("password");
        request.setPublic(true);
        //when//then
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
                .andDo(
                        document("user-register",
                                requestFields(
                                        fieldWithPath("username").type(JsonFieldType.STRING).description("Nazwa użytkownika / Login"),
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("Adres e-mail"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("Hasło użytkownika"),
                                        fieldWithPath("public").type(JsonFieldType.BOOLEAN).description("Czy konto jest publiczne?")
                                ),
                                responseFields(
                                        fieldWithPath("id").type(JsonFieldType.STRING).description("Id użytkownika"),
                                        fieldWithPath("username").type(JsonFieldType.STRING).description("Nazwa użytkownika / Login"),
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("Adres e-mail"),
                                        fieldWithPath("role").type(JsonFieldType.STRING).description("Rola użytkownika (ADMIN,USER)"),
                                        fieldWithPath("public").type(JsonFieldType.BOOLEAN).description("Czy konto jest publiczne?"),
                                        fieldWithPath("passwordStatus").type(JsonFieldType.STRING).description("Status hasła użytkownika (NORMAL,ONETIME,EXPIRED)"),
                                        fieldWithPath("avatar").type(JsonFieldType.NULL).description("Avatar użytkownika (przy rejestracji, zawsze null)"),
                                        fieldWithPath("registerAt").type(JsonFieldType.NUMBER).description("Timestamp rejstracji"),
                                        fieldWithPath("active").type(JsonFieldType.BOOLEAN).description("Czy konto jest aktywne?")
                                        )
                        ))
        ;
    }
}
