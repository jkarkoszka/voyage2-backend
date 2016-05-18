package pl.edu.pja.gdansk.voyage2.folder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pja.gdansk.voyage2.folder.exception.RouteIsNotInYourFolderException;
import pl.edu.pja.gdansk.voyage2.folder.request.AddToFavoriteRouteRequest;
import pl.edu.pja.gdansk.voyage2.route.domain.Route;
import pl.edu.pja.gdansk.voyage2.route.exception.RouteNotFoundException;
import pl.edu.pja.gdansk.voyage2.route.repository.RouteRepository;
import pl.edu.pja.gdansk.voyage2.security.domain.SecuredUserDetails;
import pl.edu.pja.gdansk.voyage2.user.domain.User;
import pl.edu.pja.gdansk.voyage2.user.exception.UserNotFoundException;
import pl.edu.pja.gdansk.voyage2.user.repository.UserRepository;

import java.util.Objects;

@Component
public class RemoveFromFavoriteRoutes {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RouteRepository routeRepository;

    public void remove(String routeId, SecuredUserDetails principal) {
        User user = userRepository.findByUsername(principal.getUsername());
        if (Objects.isNull(user)) {
            throw new UserNotFoundException("User is not found by username = '" + principal.getUsername() + "'");
        }
        Route route = routeRepository.findOne(routeId);
        if (Objects.isNull(route)) {
            throw new RouteNotFoundException();
        }
        if (!user.getFavoriteRoutes().contains(route)) {
            throw new RouteIsNotInYourFolderException();
        }
        user.removeRouteFromFavoriteRoutes(route);
        userRepository.save(user);
    }
}
