package pl.edu.pja.gdansk.voyage2.route.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.stereotype.Component;
import pl.edu.pja.gdansk.voyage2.route.domain.Route;
import pl.edu.pja.gdansk.voyage2.route.exception.RouteNotFoundException;
import pl.edu.pja.gdansk.voyage2.route.repository.RouteRepository;
import pl.edu.pja.gdansk.voyage2.security.domain.SecuredUserDetails;
import pl.edu.pja.gdansk.voyage2.user.domain.User;
import pl.edu.pja.gdansk.voyage2.user.exception.UserNotFoundException;
import pl.edu.pja.gdansk.voyage2.user.repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class RouteFetcher {

    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Route> findByArea(Point topLeft, Point bottomLeft, Point bottomRight, Point topRight, SecuredUserDetails principal) {
        User user = userRepository.findByUsername(principal.getUsername());
        if (Objects.isNull(user)) {
            throw new UserNotFoundException("User is not found by username = '" + principal.getUsername() + "'");
        }
        List<Point> points = Arrays.asList(
                topLeft,
                bottomLeft,
                bottomRight,
                topRight,
                topLeft
        );
        List<Route> routes = routeRepository.findByPointsWithin(
                new GeoJsonPolygon(points)
        );
        return routes.stream().filter(route -> filterRoutesForUser(route, user)).collect(Collectors.toList());
    }

    private boolean filterRoutesForUser(Route route, User user) {
        if (route.getUser().isPublic()) {
            return true;
        } else {
            if (user.getFavoriteRoutes().contains(route)) {
                return true;
            } else if (route.getUser().equals(user)) {
                return true;
            }
        }
        return false;
    }

    public Route findOneById(String id) throws RouteNotFoundException {
        Route route = routeRepository.findOne(id);
        if (route == null) {
            throw new RouteNotFoundException();
        }
        return route;
    }

    public List<Route> findMyRoutes(SecuredUserDetails principal) {
        User user = userRepository.findByUsername(principal.getUsername());
        if (Objects.isNull(user)) {
            throw new UserNotFoundException("User is not found by username = '" + principal.getUsername() + "'");
        }
        return routeRepository.findByUser(user);
    }

    public List<Route> findMyFavoriteRoutes(SecuredUserDetails principal) {
        User user = userRepository.findByUsername(principal.getUsername());
        if (Objects.isNull(user)) {
            throw new UserNotFoundException("User is not found by username = '" + principal.getUsername() + "'");
        }
        return user.getFavoriteRoutes();
    }
}
