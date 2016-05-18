package pl.edu.pja.gdansk.voyage2.folder.controller;

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
import pl.edu.pja.gdansk.voyage2.folder.domain.Folder;
import pl.edu.pja.gdansk.voyage2.folder.domain.FolderType;
import pl.edu.pja.gdansk.voyage2.folder.repository.FolderRepository;
import pl.edu.pja.gdansk.voyage2.folder.request.AddFolderRequest;
import pl.edu.pja.gdansk.voyage2.folder.service.AddFolder;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class RemoveFolderFolderControllerTest extends BaseControllerTest {

    @Autowired
    private FolderRepository folderRepository;
    @Autowired
    private AddFolder addFolder;

    @Before
    public void setUp() {
        folderRepository.deleteAll();
    }

    @Test
    public void myRoutesFolderRemove() throws Exception {
        //given
        AddFolderRequest addFolderRequest = new AddFolderRequest(
                "folder1"
        );
        Folder folder = addFolder.add(addFolderRequest, activatedUser);

        //when//then
        this.mockMvc
                .perform(
                        delete("/user/my-routes/folders/{folderId}", folder.getId())
                                .with(httpBasic("test", "aaa"))
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                )
                .andExpect(status().isNoContent())
        ;
    }
}