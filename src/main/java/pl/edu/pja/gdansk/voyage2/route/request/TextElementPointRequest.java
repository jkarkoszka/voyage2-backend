package pl.edu.pja.gdansk.voyage2.route.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.geo.Point;

public class TextElementPointRequest {

    private String value;
    private Point point;

    @JsonCreator
    public TextElementPointRequest(
            @JsonProperty("value")String value,
            @JsonProperty("point") Point point) {
        this.value = value;
        this.point = point;
    }

    public String getValue() {
        return value;
    }

    public Point getPoint() {
        return point;
    }

}
