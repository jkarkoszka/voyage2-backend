package pl.edu.pja.gdansk.voyage2.route.request;

import org.springframework.data.geo.Point;

import java.util.List;

public class EditRouteRequest {

    private String id;
    private String name;
    private List<Point> points;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
