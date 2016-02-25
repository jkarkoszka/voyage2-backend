package pl.edu.pja.gdansk.voyage2.route.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pja.gdansk.voyage2.route.domain.Route;
import pl.edu.pja.gdansk.voyage2.route.mapper.PhotoElementPointsMapper;
import pl.edu.pja.gdansk.voyage2.route.mapper.PointsMapper;
import pl.edu.pja.gdansk.voyage2.route.mapper.TextElementPointsMapper;
import pl.edu.pja.gdansk.voyage2.route.repository.RouteRepository;
import pl.edu.pja.gdansk.voyage2.route.request.AddRouteRequest;
import pl.edu.pja.gdansk.voyage2.user.domain.User;

@Component
public class AddRoute {

    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private PointsMapper pointsMapper;
    @Autowired
    private PhotoElementPointsMapper photoElementPointsMapper;
    @Autowired
    private TextElementPointsMapper textElementPointsMapper;

    public Route add(User user, AddRouteRequest addRouteRequest) {
        Route route = new Route();
        route.setName(addRouteRequest.getName());
        route.setPoints(pointsMapper.map(addRouteRequest.getPoints()));
        route.setTextElementPoints(textElementPointsMapper.map(addRouteRequest.getTextElementPoints()));
        route.setPhotoElementPoints(photoElementPointsMapper.map(addRouteRequest.getPhotoElementPoints()));
        route.setUser(user);
        return routeRepository.save(route);
    }
}
