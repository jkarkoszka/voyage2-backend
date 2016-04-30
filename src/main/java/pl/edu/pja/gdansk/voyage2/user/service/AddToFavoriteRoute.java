package pl.edu.pja.gdansk.voyage2.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pja.gdansk.voyage2.route.domain.Route;
import pl.edu.pja.gdansk.voyage2.route.exception.RouteNotFoundException;
import pl.edu.pja.gdansk.voyage2.route.repository.RouteRepository;
import pl.edu.pja.gdansk.voyage2.security.domain.SecuredUserDetails;
import pl.edu.pja.gdansk.voyage2.user.domain.User;
import pl.edu.pja.gdansk.voyage2.user.exception.UserNotFoundException;
import pl.edu.pja.gdansk.voyage2.user.repository.UserRepository;
import pl.edu.pja.gdansk.voyage2.user.request.AddToFavoriteRouteRequest;

import java.util.Objects;

@Component
public class AddToFavoriteRoute {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RouteRepository routeRepository;

    public void add(AddToFavoriteRouteRequest addToFavoriteRouteRequest, SecuredUserDetails principal) {
        User user = userRepository.findByUsername(principal.getUsername());
        if (Objects.isNull(user)) {
            throw new UserNotFoundException("User is not found by username = '" + principal.getUsername() + "'");
        }
        Route route = routeRepository.findOne(addToFavoriteRouteRequest.getRouteId());
        if (Objects.isNull(route)) {
            throw new RouteNotFoundException();
        }
        user.addRouteToFavoriteRoutes(route);
        userRepository.save(user);
    }
}
