package pl.edu.pja.gdansk.voyage2.route.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonLineString;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.edu.pja.gdansk.voyage2.user.domain.User;

import java.util.List;

@Document
public class Route {

    @Id
    private String id;
    private String name;
    private String description;
    private Integer distance;
    private Integer startedAt;
    private Integer finishedAt;
    private GeoJsonLineString points;
    @DBRef
    private User user;
    private List<Element> elements;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GeoJsonLineString getPoints() {
        return points;
    }

    public void setPoints(GeoJsonLineString points) {
        this.points = points;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Integer startedAt) {
        this.startedAt = startedAt;
    }

    public Integer getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(Integer finishedAt) {
        this.finishedAt = finishedAt;
    }
}
