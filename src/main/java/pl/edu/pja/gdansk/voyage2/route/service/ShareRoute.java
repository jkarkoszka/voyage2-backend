package pl.edu.pja.gdansk.voyage2.route.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pja.gdansk.voyage2.route.domain.Route;
import pl.edu.pja.gdansk.voyage2.route.exception.RouteAccessDeniedException;
import pl.edu.pja.gdansk.voyage2.route.exception.RouteNotFoundException;
import pl.edu.pja.gdansk.voyage2.route.repository.RouteRepository;
import pl.edu.pja.gdansk.voyage2.route.request.ShareRouteRequest;
import pl.edu.pja.gdansk.voyage2.security.domain.SecuredUserDetails;
import pl.edu.pja.gdansk.voyage2.user.domain.User;
import pl.edu.pja.gdansk.voyage2.user.exception.UserNotFoundException;
import pl.edu.pja.gdansk.voyage2.user.repository.UserRepository;

import java.util.Objects;

@Component
public class ShareRoute {

    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShareRouteLinkSender shareRouteLinkSender;

    public void share(String id, SecuredUserDetails principal, ShareRouteRequest shareRouteRequest) {
        Route route = routeRepository.findOne(id);
        if (route == null) {
            throw new RouteNotFoundException();
        }
        User user = userRepository.findByUsername(principal.getUsername());
        if (Objects.isNull(user)) {
            throw new UserNotFoundException("User is not found by username = '" + principal.getUsername() + "'");
        }
        if (!route.getUser().equals(user) || !user.getFavoriteRoutes().contains(route)) {
            throw new RouteAccessDeniedException();
        }
        shareRouteLinkSender.send(shareRouteRequest.getEmail(), route);
    }
}
