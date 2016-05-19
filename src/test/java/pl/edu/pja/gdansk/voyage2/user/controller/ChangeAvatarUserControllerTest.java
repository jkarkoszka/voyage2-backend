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
import pl.edu.pja.gdansk.voyage2.attachment.domain.Attachment;
import pl.edu.pja.gdansk.voyage2.attachment.repository.AttachmentRepository;
import pl.edu.pja.gdansk.voyage2.folder.service.AddFolder;
import pl.edu.pja.gdansk.voyage2.user.domain.User;
import pl.edu.pja.gdansk.voyage2.user.repository.UserRepository;
import pl.edu.pja.gdansk.voyage2.user.request.ChangeAvatarRequest;
import pl.edu.pja.gdansk.voyage2.user.request.ChangePasswordRequest;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class ChangeAvatarUserControllerTest extends BaseControllerTest {

    @Autowired
    private AttachmentRepository attachmentRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void changeAvatar() throws Exception {
        //given
        User user = userRepository.findByUsername(activatedUser.getUsername());

        Attachment attachment = new Attachment();
        attachment.setUser(user);
        attachment.setId("abc");
        attachment.setName("avatar.png");

        attachmentRepository.save(attachment);

        ChangeAvatarRequest request = new ChangeAvatarRequest(attachment.getId());

        //when//then
        this.mockMvc
                .perform(
                        patch("/user/avatar")
                                .with(httpBasic("test", "aaa"))
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(
                                        this.objectMapper.writeValueAsString(request)
                                )
                )
                .andExpect(status().isNoContent())
        ;
    }
}