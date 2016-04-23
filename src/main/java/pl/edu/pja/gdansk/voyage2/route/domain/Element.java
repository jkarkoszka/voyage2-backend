package pl.edu.pja.gdansk.voyage2.route.domain;


import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.DBRef;

abstract public class Element {
    @DBRef
    private Route route;
    private String value;
    private GeoJsonPoint point;

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public GeoJsonPoint getPoint() {
        return point;
    }

    public void setPoint(GeoJsonPoint point) {
        this.point = point;
    }
}
