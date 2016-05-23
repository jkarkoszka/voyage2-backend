package pl.edu.pja.gdansk.voyage2.folder.controller.favoriteroute;

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
import pl.edu.pja.gdansk.voyage2.folder.request.AddToFavoriteRoutesRequest;
import pl.edu.pja.gdansk.voyage2.folder.service.AddToFavoriteRoutes;
import pl.edu.pja.gdansk.voyage2.route.domain.Route;
import pl.edu.pja.gdansk.voyage2.route.repository.RouteRepository;
import pl.edu.pja.gdansk.voyage2.route.request.AddRouteRequest;
import pl.edu.pja.gdansk.voyage2.route.service.AddRoute;

import java.util.Arrays;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class RemoveFavoriteRouteControllerTest extends BaseControllerTest {

    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private AddRoute addRoute;
    @Autowired
    private AddToFavoriteRoutes addToFavoriteRoutes;

    @Before
    public void setUp() {
        routeRepository.deleteAll();
    }

    @Test
    public void remove() throws Exception {
        //given
        AddRouteRequest addRouteRequest = new AddRouteRequest(
                "Testowa trasa 1",
                "Opis trasy",
                100,
                123125345,
                223423423,
                Arrays.asList(new Point(0.5D, 0.5D), new Point(1, 0.5D), new Point(2, 0.5D), new Point(3, 0.5D)),
                Arrays.asList(),
                Arrays.asList(),
                null
        );
        Route route = addRoute.add(activatedUser, addRouteRequest);
        AddToFavoriteRoutesRequest addToFavoriteRoutesRequest = new AddToFavoriteRoutesRequest(route.getId());
        addToFavoriteRoutes.add(addToFavoriteRoutesRequest, activatedUser);
        //when//then
        this.mockMvc
                .perform(
                        delete("/user/favorite-routes/{routeId}", route.getId())
                                .with(httpBasic("test", "aaa"))
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                )
                .andExpect(status().isNoContent())
                .andDo(document("folder-favoriteroute-remove"))
        ;
    }
}