package pl.edu.pja.gdansk.voyage2.route.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.geo.Point;

import javax.validation.constraints.NotNull;
import java.util.List;

public class EditRouteRequest {

    @NotNull
    private String id;
    @NotNull
    private String name;
    @NotNull
    private List<Point> points;
    @NotNull
    private List<PhotoElementRequest> photoElements;
    @NotNull
    private List<TextElementRequest> textElements;

    public EditRouteRequest(
            @JsonProperty("id") String id,
            @JsonProperty("name") String name,
            @JsonProperty("points") List<Point> points,
            @JsonProperty("photoElements") List<PhotoElementRequest> photoElements,
            @JsonProperty("textElements") List<TextElementRequest> textElements) {
        this.id = id;
        this.name = name;
        this.points = points;
        this.photoElements = photoElements;
        this.textElements = textElements;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Point> getPoints() {
        return points;
    }

    public List<PhotoElementRequest> getPhotoElements() {
        return photoElements;
    }

    public List<TextElementRequest> getTextElements() {
        return textElements;
    }
}
