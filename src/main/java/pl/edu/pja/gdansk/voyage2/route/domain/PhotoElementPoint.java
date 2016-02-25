package pl.edu.pja.gdansk.voyage2.route.domain;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.DBRef;
import pl.edu.pja.gdansk.voyage2.attachment.domain.Attachment;

public class PhotoElementPoint {

    @DBRef
    private Route route;
    @DBRef
    private Attachment attachment;
    private String value;
    private GeoJsonPoint point;

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
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
