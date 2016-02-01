package pl.edu.pja.gdansk.voyage2.route.request;

import org.springframework.data.geo.Point;

import java.util.List;

public class AddRouteRequest {

    private String name;
    private List<Point> points;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}
