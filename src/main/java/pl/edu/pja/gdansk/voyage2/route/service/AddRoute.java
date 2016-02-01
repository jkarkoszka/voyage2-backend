package pl.edu.pja.gdansk.voyage2.route.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonLineString;
import org.springframework.stereotype.Component;
import pl.edu.pja.gdansk.voyage2.route.domain.Route;
import pl.edu.pja.gdansk.voyage2.route.repository.RouteRepository;
import pl.edu.pja.gdansk.voyage2.route.request.AddRouteRequest;
import pl.edu.pja.gdansk.voyage2.user.domain.User;

@Component
public class AddRoute {

    @Autowired
    private RouteRepository routeRepository;

    public Route add(User user, AddRouteRequest addRouteRequest) {
        Route route = new Route();
        route.setName(addRouteRequest.getName());
        route.setPoints(new GeoJsonLineString(addRouteRequest.getPoints()));
        route.setUser(user);
        return routeRepository.save(route);
    }
}
