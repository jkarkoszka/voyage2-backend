package pl.edu.pja.gdansk.voyage2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.RestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.edu.pja.gdansk.voyage2.security.domain.SecuredUserDetails;
import pl.edu.pja.gdansk.voyage2.user.domain.User;
import pl.edu.pja.gdansk.voyage2.user.repository.UserRepository;
import pl.edu.pja.gdansk.voyage2.user.request.RegisterUserRequest;
import pl.edu.pja.gdansk.voyage2.user.service.RegisterUser;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

public class BaseControllerTest {

    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected WebApplicationContext context;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RegisterUser registerUser;
    @Rule
    public final RestDocumentation restDocumentation = new RestDocumentation("build/generated-snippets");
    protected RestDocumentationResultHandler document;
    protected MockMvc mockMvc;
    protected SecuredUserDetails user;
    @Before
    public void baseSetUp() throws Exception {
        this.document = document("{method-name}",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()));
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .apply(springSecurity())
                .alwaysDo(this.document)
                .build();
        createUser();
    }

    private void createUser() {
        userRepository.deleteAll();
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("test");
        registerUserRequest.setEmail("test@example.com");
        registerUserRequest.setPassword("aaa");
        registerUserRequest.setPublic(true);
        user = new SecuredUserDetails(registerUser.createUser(registerUserRequest));
    }
}
