package pl.edu.pja.gdansk.voyage2.folder.controller.myroutefolder;

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
import pl.edu.pja.gdansk.voyage2.folder.repository.FolderRepository;
import pl.edu.pja.gdansk.voyage2.folder.request.AddFolderRequest;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class AddMyRouteFolderControllerTest extends BaseControllerTest {

    @Autowired
    private FolderRepository folderRepository;

    @Before
    public void setUp() {
        folderRepository.deleteAll();
    }

    @Test
    public void add() throws Exception {
        //given
        AddFolderRequest request = new AddFolderRequest(
                "folder1"
        );
        //when//then
        this.mockMvc
                .perform(
                        post("/user/my-routes/folders")
                                .with(httpBasic("test", "aaa"))
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(
                                        this.objectMapper.writeValueAsString(request)
                                )
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("[?($.name == 'folder1')]").exists())
                .andDo(document("folder-myroutefolder-add"))
        ;
    }
}