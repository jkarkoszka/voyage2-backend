package pl.edu.pja.gdansk.voyage2.route.repository;

import org.springframework.data.geo.Polygon;
import org.springframework.data.mongodb.repository.MongoRepository;
import pl.edu.pja.gdansk.voyage2.route.domain.Route;
import pl.edu.pja.gdansk.voyage2.user.domain.User;

import java.util.List;

public interface RouteRepository extends MongoRepository<Route, String> {

    List<Route> findByPointsWithin(Polygon polygon);

    List<Route> findByUser(User user);
}
