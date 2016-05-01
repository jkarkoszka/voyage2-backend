package pl.edu.pja.gdansk.voyage2.route.controller;

import org.joda.time.DateTime;
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
import pl.edu.pja.gdansk.voyage2.route.service.AddRoute;

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
public class ListRoutesByAreaRouteControllerTest extends BaseControllerTest {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private AddRoute addRoute;

    @Before
    public void setUp() {
        routeRepository.deleteAll();
    }

    @Test
    public void routeListByArea() throws Exception {
        //given
        AddRouteRequest addRouteRequest1 = new AddRouteRequest(
                "Testowa trasa 1",
                "Opis trasy",
                100,
                123125345,
                223423423,
                Arrays.asList(new Point(0.5D, 0.5D), new Point(1,0.5D), new Point(2,0.5D), new Point(3, 0.5D)),
                Arrays.asList(),
                Arrays.asList()
        );
        Route route1 = addRoute.add(activatedUser, addRouteRequest1);

        AddRouteRequest addRouteRequest2 = new AddRouteRequest(
                "Testowa trasa 2",
                "Opis trasy",
                100,
                123125345,
                223423423,
                Arrays.asList(new Point(0.7D, 0.5D), new Point(1,0.7D), new Point(2,0.4D), new Point(3, 0.3D)),
                Arrays.asList(),
                Arrays.asList()
        );
        Route route2 = addRoute.add(activatedUser, addRouteRequest2);

        AddRouteRequest addRouteRequest3 = new AddRouteRequest(
                "Testowa trasa 3",
                "Opis trasy",
                100,
                123125345,
                223423423,
                Arrays.asList(new Point(0, 5), new Point(1,5), new Point(2,5), new Point(3, 5)),
                Arrays.asList(),
                Arrays.asList()
        );
        Route route3 = addRoute.add(activatedUser, addRouteRequest3);

        assertThat(routeRepository.findAll()).hasSize(3);

        //when//then
        String response = this.mockMvc
                .perform(
                        get("/routes/by-area")
                                .param("blX", "0")
                                .param("blY", "0")
                                .param("brX", "4")
                                .param("brY", "0")
                                .param("trX", "4")
                                .param("trY", "1")
                                .param("tlX", "0")
                                .param("tlY", "1")
                                .with(httpBasic("test", "aaa"))
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").isNotEmpty())
                .andExpect(jsonPath("[?($.[0].name == 'Testowa trasa 1')]").exists())
                .andExpect(jsonPath("$.[0].user").isNotEmpty())
                .andExpect(jsonPath("$.[0].points").isNotEmpty())
                .andExpect(jsonPath("$.[0].elements").isEmpty())
                .andExpect(jsonPath("$.[1].id").isNotEmpty())
                .andExpect(jsonPath("[?($.[1].name == 'Testowa trasa 2')]").exists())
                .andExpect(jsonPath("$.[1].user").isNotEmpty())
                .andExpect(jsonPath("$.[1].points").isNotEmpty())
                .andExpect(jsonPath("$.[1].elements").isEmpty())
                .andReturn().getResponse().getContentAsString()
        ;

        System.out.println(response);
    }

    @Test
    public void routeListByArea2() throws Exception {
        //given
        AddRouteRequest addRouteRequest1 = new AddRouteRequest(
                "Testowa trasa 1",
                "Opis trasy",
                100,
                123125345,
                223423423,
                Arrays.asList(new Point(18.5D, 54.5D), new Point(18.51, 54.5D), new Point(18.51, 54.5D)),
                Arrays.asList(),
                Arrays.asList()
        );
        Route route1 = addRoute.add(activatedUser, addRouteRequest1);

        assertThat(routeRepository.findAll()).hasSize(1);

        //when//then
        String response = this.mockMvc
                .perform(
                        get("/routes/by-area")
                                .param("blX", "18.0")
                                .param("blY", "54.0")
                                .param("tlX", "18.0")
                                .param("tlY", "55.0")
                                .param("trX", "19.0")
                                .param("trY", "55.0")
                                .param("brX", "19.0")
                                .param("brY", "54.0")
                                .with(httpBasic("test", "aaa"))
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").isNotEmpty())
                .andExpect(jsonPath("[?($.[0].name == 'Testowa trasa 1')]").exists())
                .andExpect(jsonPath("$.[0].user").isNotEmpty())
                .andExpect(jsonPath("$.[0].points").isNotEmpty())
                .andExpect(jsonPath("$.[0].elements").isEmpty())
                .andReturn().getResponse().getContentAsString()
                ;

        System.out.println(response);
    }
}