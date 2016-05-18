package pl.edu.pja.gdansk.voyage2.route.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pja.gdansk.voyage2.folder.domain.Folder;
import pl.edu.pja.gdansk.voyage2.folder.service.FolderFetcher;
import pl.edu.pja.gdansk.voyage2.route.domain.Route;
import pl.edu.pja.gdansk.voyage2.route.exception.RouteAccessDeniedException;
import pl.edu.pja.gdansk.voyage2.route.exception.RouteIdsNotMatchException;
import pl.edu.pja.gdansk.voyage2.route.exception.RouteNotFoundException;
import pl.edu.pja.gdansk.voyage2.route.mapper.ElementMapper;
import pl.edu.pja.gdansk.voyage2.route.mapper.PointsMapper;
import pl.edu.pja.gdansk.voyage2.route.repository.RouteRepository;
import pl.edu.pja.gdansk.voyage2.route.request.EditRouteRequest;
import pl.edu.pja.gdansk.voyage2.security.domain.SecuredUserDetails;
import pl.edu.pja.gdansk.voyage2.user.domain.User;
import pl.edu.pja.gdansk.voyage2.user.repository.UserRepository;

import java.util.Objects;

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
    @Autowired
    private FolderFetcher folderFetcher;

    public Route edit(SecuredUserDetails securedUserDetails, String id, EditRouteRequest editRouteRequest) throws RouteNotFoundException, RouteIdsNotMatchException, RouteAccessDeniedException {
        User user = userRepository.findByUsername(securedUserDetails.getUsername());
        Route route = routeRepository.findOne(id);
        if (route == null) {
            throw new RouteNotFoundException();
        }
        if (!route.getUser().equals(user)) {
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

        if (!Objects.isNull(editRouteRequest.getFolderId())) {
            Folder folder = folderFetcher.findOne(editRouteRequest.getFolderId(), user);
            route.setFolder(folder);
        }

        return routeRepository.save(route);
    }
}
