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
    private String description;
    @NotNull
    private Integer distance;
    @NotNull
    private Integer startedAt;
    @NotNull
    private Integer finishedAt;
    @NotNull
    private List<Point> points;
    @NotNull
    private List<PhotoElementRequest> photoElements;
    @NotNull
    private List<TextElementRequest> textElements;
    private String folderId;

    @JsonCreator
    public AddRouteRequest(
            @JsonProperty("name") String name,
            @JsonProperty("description") String description,
            @JsonProperty("distance") Integer distance,
            @JsonProperty("startedAt") Integer startedAt,
            @JsonProperty("finishedAt") Integer finishedAt,
            @JsonProperty("points") List<Point> points,
            @JsonProperty("photoElements") List<PhotoElementRequest> photoElements,
            @JsonProperty("textElements") List<TextElementRequest> textElements,
            @JsonProperty("folderId") String folderId) {
        this.name = name;
        this.description = description;
        this.distance = distance;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.points = points;
        this.photoElements = photoElements;
        this.textElements = textElements;
        this.folderId = folderId;
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

    public String getDescription() {
        return description;
    }

    public Integer getDistance() {
        return distance;
    }

    public Integer getStartedAt() {
        return startedAt;
    }

    public Integer getFinishedAt() {
        return finishedAt;
    }

    public String getFolderId() {
        return folderId;
    }
}
