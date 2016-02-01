package pl.edu.pja.gdansk.voyage2.route.domain;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PhotoElement {

    @DBRef
    private Route route;
    private String path;

    public Route getRoute() {
        return route;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        //TODO: generowanie URLa
        return path;
    }
}
