package pl.edu.pja.gdansk.voyage2.folder.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.geo.Point;
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
import pl.edu.pja.gdansk.voyage2.route.domain.Route;
import pl.edu.pja.gdansk.voyage2.route.repository.RouteRepository;
import pl.edu.pja.gdansk.voyage2.route.request.AddRouteRequest;
import pl.edu.pja.gdansk.voyage2.route.service.AddRoute;
import pl.edu.pja.gdansk.voyage2.user.domain.User;
import pl.edu.pja.gdansk.voyage2.user.repository.UserRepository;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class ListFoldersFolderControllerTest extends BaseControllerTest {

    @Autowired
    private FolderRepository folderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddFolder addFolder;

    @Before
    public void setUp() {
        folderRepository.deleteAll();
    }

    @Test
    public void myRoutesFolderList() throws Exception {
        //given
        AddFolderRequest addFolderRequest1 = new AddFolderRequest(
                "folder1"
        );
        Folder folder1 = addFolder.add(addFolderRequest1, activatedUser);

        AddFolderRequest addFolderRequest2 = new AddFolderRequest(
                "folder2"
        );
        Folder folder2 = addFolder.add(addFolderRequest2, activatedUser);

        User user = userRepository.findByUsername(activatedUser.getUsername());

        assertThat(folderRepository.findByUser(user)).hasSize(2);

        //when//then
        this.mockMvc
                .perform(
                        get("/user/my-routes/folders")
                                .with(httpBasic("test", "aaa"))
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").isNotEmpty())
                .andExpect(jsonPath("[?($.[0].name == 'folder1')]").exists())
                .andExpect(jsonPath("$.[1].id").isNotEmpty())
                .andExpect(jsonPath("[?($.[1].name == 'folder2')]").exists())
                .andReturn()
        ;
    }
}