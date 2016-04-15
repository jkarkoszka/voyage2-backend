package pl.edu.pja.gdansk.voyage2.route.controller;

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
import pl.edu.pja.gdansk.voyage2.route.request.EditRouteRequest;
import pl.edu.pja.gdansk.voyage2.route.service.AddRoute;

import java.util.Arrays;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class EditRouteRouteControllerTest extends BaseControllerTest {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private AddRoute addRoute;

    @Before
    public void setUp() {
        routeRepository.deleteAll();
    }

    @Test
    public void routeEdit() throws Exception {
        //given
        AddRouteRequest addRouteRequest = new AddRouteRequest(
                "Testowa trasa",
                Arrays.asList(new Point(1, 0), new Point(5,6), new Point(9,9), new Point(16, 2)),
                Arrays.asList(),
                Arrays.asList()
        );
        Route route = addRoute.add(activatedUser, addRouteRequest);
        EditRouteRequest request = new EditRouteRequest(
                route.getId(),
                "Wyedytowana trasa",
                Arrays.asList(new Point(0, 1), new Point(6,5), new Point(12,56), new Point(2, 25)),
                Arrays.asList(),
                Arrays.asList()
        );

        //when//then
        this.mockMvc
                .perform(
                        put("/route/{id}", route.getId())
                                .with(httpBasic("test", "aaa"))
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(
                                        this.objectMapper.writeValueAsString(request)
                                )
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("[?($.name == 'Wyedytowana trasa')]").exists())
                .andExpect(jsonPath("$.user").isNotEmpty())
                .andExpect(jsonPath("$.points").isNotEmpty())
                .andExpect(jsonPath("$.photoElementPoints").isEmpty())
                .andExpect(jsonPath("$.textElementPoints").isEmpty())
        ;
    }
}