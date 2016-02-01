package pl.edu.pja.gdansk.voyage2.route.domain;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TextElement {

    @DBRef
    private Route route;
    private String value;

    public Route getRoute() {
        return route;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
