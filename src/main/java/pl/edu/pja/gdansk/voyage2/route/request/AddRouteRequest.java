package pl.edu.pja.gdansk.voyage2.route.request;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.geo.Point;

import javax.validation.constraints.NotNull;
import java.util.List;

public class AddRouteRequest {

    @NotNull
    private String name;
    @NotNull
    private List<Point> points;
    @NotNull
    private List<TextElementPointRequest> textElementPoints;
    @NotNull
    private List<PhotoElementPointRequest> photoElementPoints;

    @JsonCreator
    public AddRouteRequest(
            @JsonProperty("name") String name,
            @JsonProperty("points") List<Point> points,
            @JsonProperty("textElementPoints") List<TextElementPointRequest> textElementPoints,
            @JsonProperty("photoElementPoints") List<PhotoElementPointRequest> photoElementPoints) {
        this.name = name;
        this.points = points;
        this.textElementPoints = textElementPoints;
        this.photoElementPoints = photoElementPoints;
    }

    public String getName() {
        return name;
    }

    public List<Point> getPoints() {
        return points;
    }

    public List<TextElementPointRequest> getTextElementPoints() {
        return textElementPoints;
    }

    public List<PhotoElementPointRequest> getPhotoElementPoints() {
        return photoElementPoints;
    }

}
