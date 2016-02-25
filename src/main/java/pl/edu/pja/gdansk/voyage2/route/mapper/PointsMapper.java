package pl.edu.pja.gdansk.voyage2.route.mapper;

import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonLineString;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PointsMapper {

    public GeoJsonLineString map(List<Point> points) {
        return new GeoJsonLineString(points);
    }
}
