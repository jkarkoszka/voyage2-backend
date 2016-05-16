package pl.edu.pja.gdansk.voyage2.user.controller;

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
import pl.edu.pja.gdansk.voyage2.route.domain.Route;
import pl.edu.pja.gdansk.voyage2.route.repository.RouteRepository;
import pl.edu.pja.gdansk.voyage2.route.request.AddRouteRequest;
import pl.edu.pja.gdansk.voyage2.route.request.PhotoElementRequest;
import pl.edu.pja.gdansk.voyage2.route.request.TextElementRequest;
import pl.edu.pja.gdansk.voyage2.route.service.AddRoute;
import pl.edu.pja.gdansk.voyage2.user.request.AddToFavoriteRouteRequest;

import java.util.Arrays;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class AddRouteToFavoriteRoutesControllerTest extends BaseControllerTest {

    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private AddRoute addRoute;

    @Before
    public void setUp() {
        routeRepository.deleteAll();
    }

    @Test
    public void addRouteToFavoriteRoutes() throws Exception {
        //given
        AddRouteRequest addRouteRequest = new AddRouteRequest(
                "Testowa trasa",
                "Opis trasy",
                100,
                123125345,
                223423423,
                Arrays.asList(new Point(1, 0), new Point(5, 6), new Point(9, 9), new Point(16, 2)),
                Arrays.asList(new PhotoElementRequest("abc", "opis zdjecia 1", new Point(5, 6)), new PhotoElementRequest("bca", "opis zdjecia 2", new Point(9, 9))),
                Arrays.asList(new TextElementRequest("poczatek trasy", new Point(1, 0)), new TextElementRequest("koniec trasy", new Point(16, 2)))
        );
        Route route = addRoute.add(activatedUser, addRouteRequest);

        AddToFavoriteRouteRequest addToFavoriteRouteRequest = new AddToFavoriteRouteRequest();
        addToFavoriteRouteRequest.setRouteId(route.getId());

        //when//then
        this.mockMvc
                .perform(
                        post("/user/favorite-routes")
                                .with(httpBasic("test", "aaa"))
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(
                                        this.objectMapper.writeValueAsString(addToFavoriteRouteRequest)
                                )
                )
                .andExpect(status().isOk())
        ;
    }
}