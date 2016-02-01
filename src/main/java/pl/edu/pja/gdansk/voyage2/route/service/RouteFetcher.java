package pl.edu.pja.gdansk.voyage2.route.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.stereotype.Component;
import pl.edu.pja.gdansk.voyage2.route.domain.Route;
import pl.edu.pja.gdansk.voyage2.route.exception.RouteNotFoundException;
import pl.edu.pja.gdansk.voyage2.route.repository.RouteRepository;

import java.util.Arrays;
import java.util.List;

@Component
public class RouteFetcher {

    @Autowired
    private RouteRepository routeRepository;

    public List<Route> findByArea(Point point1, Point point2, Point point3, Point point4) {
        List<Point> points = Arrays.asList(
                point1,
                point2,
                point3,
                point4,
                point1
        );
        return routeRepository.findByPointsWithin(
                new GeoJsonPolygon(points)
        );
    }

    public Route findOneById(String id) throws RouteNotFoundException {
        Route route = routeRepository.findOne(id);
        if (route == null) {
            throw new RouteNotFoundException();
        }
        return route;
    }
}
