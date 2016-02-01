package pl.edu.pja.gdansk.voyage2.route.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonLineString;
import org.springframework.stereotype.Component;
import pl.edu.pja.gdansk.voyage2.route.domain.Route;
import pl.edu.pja.gdansk.voyage2.route.exception.RouteAccessDeniedException;
import pl.edu.pja.gdansk.voyage2.route.exception.RouteIdsNotMatchException;
import pl.edu.pja.gdansk.voyage2.route.exception.RouteNotFoundException;
import pl.edu.pja.gdansk.voyage2.route.repository.RouteRepository;
import pl.edu.pja.gdansk.voyage2.route.request.EditRouteRequest;
import pl.edu.pja.gdansk.voyage2.user.domain.User;

@Component
public class EditRoute {

    @Autowired
    private RouteRepository routeRepository;

    public Route edit(User user, String id, EditRouteRequest editRouteRequest) throws RouteNotFoundException, RouteIdsNotMatchException, RouteAccessDeniedException {
        Route route = routeRepository.findOne(id);
        if (route == null) {
            throw new RouteNotFoundException();
        }
        if (route.getUser().equals(user)) {
            throw new RouteAccessDeniedException();
        }
        if (!route.getId().equals(editRouteRequest.getId())) {
            throw new RouteIdsNotMatchException();
        }
        route.setName(editRouteRequest.getName());
        route.setPoints(new GeoJsonLineString(editRouteRequest.getPoints()));
        return routeRepository.save(route);
    }
}
