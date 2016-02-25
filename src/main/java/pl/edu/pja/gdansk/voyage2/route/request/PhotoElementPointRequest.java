package pl.edu.pja.gdansk.voyage2.route.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.geo.Point;

public class PhotoElementPointRequest {

    private String attachmentId;
    private String value;
    private Point point;

    @JsonCreator
    public PhotoElementPointRequest(
            @JsonProperty("attachmentId") String attachmentId,
            @JsonProperty("value") String value,
            @JsonProperty("point") Point point) {
        this.attachmentId = attachmentId;
        this.value = value;
        this.point = point;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public String getValue() {
        return value;
    }

    public Point getPoint() {
        return point;
    }
}
