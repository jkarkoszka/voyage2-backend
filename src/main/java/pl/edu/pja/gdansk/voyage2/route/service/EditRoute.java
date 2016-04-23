package pl.edu.pja.gdansk.voyage2.route.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pja.gdansk.voyage2.route.domain.Route;
import pl.edu.pja.gdansk.voyage2.route.exception.RouteAccessDeniedException;
import pl.edu.pja.gdansk.voyage2.route.exception.RouteIdsNotMatchException;
import pl.edu.pja.gdansk.voyage2.route.exception.RouteNotFoundException;
import pl.edu.pja.gdansk.voyage2.route.mapper.ElementMapper;
import pl.edu.pja.gdansk.voyage2.route.mapper.PointsMapper;
import pl.edu.pja.gdansk.voyage2.route.repository.RouteRepository;
import pl.edu.pja.gdansk.voyage2.route.request.EditRouteRequest;
import pl.edu.pja.gdansk.voyage2.security.domain.SecuredUserDetails;
import pl.edu.pja.gdansk.voyage2.user.repository.UserRepository;

@Component
public class EditRoute {

    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private PointsMapper pointsMapper;
    @Autowired
    private ElementMapper elementMapper;
    @Autowired
    private UserRepository userRepository;

    public Route edit(SecuredUserDetails securedUserDetails, String id, EditRouteRequest editRouteRequest) throws RouteNotFoundException, RouteIdsNotMatchException, RouteAccessDeniedException {
        Route route = routeRepository.findOne(id);
        if (route == null) {
            throw new RouteNotFoundException();
        }
        if (!route.getUser().equals(userRepository.findByUsername(securedUserDetails.getUsername()))) {
            throw new RouteAccessDeniedException();
        }
        if (!route.getId().equals(editRouteRequest.getId())) {
            throw new RouteIdsNotMatchException();
        }
        route.setName(editRouteRequest.getName());
        route.setDescription(editRouteRequest.getDescription());
        route.setDistance(editRouteRequest.getDistance());
        route.setStartedAt(editRouteRequest.getStartedAt());
        route.setFinishedAt(editRouteRequest.getFinishedAt());
        route.setPoints(pointsMapper.map(editRouteRequest.getPoints()));
        route.setElements(elementMapper.map(editRouteRequest));
        return routeRepository.save(route);
    }
}
