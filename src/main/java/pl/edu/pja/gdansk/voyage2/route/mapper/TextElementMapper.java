package pl.edu.pja.gdansk.voyage2.route.mapper;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Component;
import pl.edu.pja.gdansk.voyage2.route.domain.Element;
import pl.edu.pja.gdansk.voyage2.route.domain.TextElement;
import pl.edu.pja.gdansk.voyage2.route.request.TextElementRequest;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class TextElementMapper {

    public List<Element> map(List<TextElementRequest> points) {
        return points.stream().map(this::map).filter(Objects::nonNull).collect(Collectors.toList());
    }

    private TextElement map(TextElementRequest element) {
        TextElement textElement = new TextElement();
        textElement.setPoint(new GeoJsonPoint(element.getPoint()));
        textElement.setValue(element.getValue());
        return textElement;
    }
}
