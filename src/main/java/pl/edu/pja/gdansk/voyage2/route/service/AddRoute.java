package pl.edu.pja.gdansk.voyage2.route.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pja.gdansk.voyage2.folder.domain.Folder;
import pl.edu.pja.gdansk.voyage2.folder.exception.FolderNotFoundException;
import pl.edu.pja.gdansk.voyage2.folder.repository.FolderRepository;
import pl.edu.pja.gdansk.voyage2.folder.service.FolderFetcher;
import pl.edu.pja.gdansk.voyage2.route.domain.Route;
import pl.edu.pja.gdansk.voyage2.route.mapper.ElementMapper;
import pl.edu.pja.gdansk.voyage2.route.mapper.PointsMapper;
import pl.edu.pja.gdansk.voyage2.route.repository.RouteRepository;
import pl.edu.pja.gdansk.voyage2.route.request.AddRouteRequest;
import pl.edu.pja.gdansk.voyage2.security.domain.SecuredUserDetails;
import pl.edu.pja.gdansk.voyage2.user.domain.User;
import pl.edu.pja.gdansk.voyage2.user.repository.UserRepository;

import java.util.Objects;

@Component
public class AddRoute {

    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PointsMapper pointsMapper;
    @Autowired
    private ElementMapper elementMapper;
    @Autowired
    private FolderFetcher folderFetcher;

    public Route add(SecuredUserDetails securedUserDetails, AddRouteRequest addRouteRequest) {
        User user = userRepository.findByUsername(securedUserDetails.getUsername());

        Route route = new Route();
        route.setName(addRouteRequest.getName());
        route.setDescription(addRouteRequest.getDescription());
        route.setDistance(addRouteRequest.getDistance());
        route.setStartedAt(addRouteRequest.getStartedAt());
        route.setFinishedAt(addRouteRequest.getFinishedAt());
        route.setPoints(pointsMapper.map(addRouteRequest.getPoints()));
        route.setElements(elementMapper.map(addRouteRequest));
        route.setUser(user);

        if (!Objects.isNull(addRouteRequest.getFolderId())) {
            Folder folder = folderFetcher.findOne(addRouteRequest.getFolderId(), user);
            route.setFolder(folder);
        }

        return routeRepository.save(route);
    }


}
