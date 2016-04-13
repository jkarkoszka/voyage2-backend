package pl.edu.pja.gdansk.voyage2.route.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pja.gdansk.voyage2.route.domain.Route;
import pl.edu.pja.gdansk.voyage2.route.exception.RouteAccessDeniedException;
import pl.edu.pja.gdansk.voyage2.route.exception.RouteNotFoundException;
import pl.edu.pja.gdansk.voyage2.route.repository.RouteRepository;
import pl.edu.pja.gdansk.voyage2.security.domain.SecuredUserDetails;
import pl.edu.pja.gdansk.voyage2.user.domain.User;
import pl.edu.pja.gdansk.voyage2.user.repository.UserRepository;

@Component
public class DeleteRoute {

    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private UserRepository userRepository;

    public void delete(SecuredUserDetails securedUserDetails, String id) throws RouteNotFoundException, RouteAccessDeniedException {
        Route route = routeRepository.findOne(id);
        if (route == null) {
            throw new RouteNotFoundException();
        }
        if (!route.getUser().equals(userRepository.findByUsername(securedUserDetails.getUsername()))) {
            throw new RouteAccessDeniedException();
        }
        routeRepository.delete(route);
    }
}
