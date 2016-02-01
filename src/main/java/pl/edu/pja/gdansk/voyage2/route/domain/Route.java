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
    private GeoJsonLineString points;
    @DBRef
    private User user;
    @DBRef
    private List<PhotoElement> photoElements;
    @DBRef
    private List<TextElement> textElements;

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

    public List<PhotoElement> getPhotoElements() {
        return photoElements;
    }

    public void setPhotoElements(List<PhotoElement> photoElements) {
        this.photoElements = photoElements;
    }

    public List<TextElement> getTextElements() {
        return textElements;
    }

    public void setTextElements(List<TextElement> textElements) {
        this.textElements = textElements;
    }
}
