package pl.edu.pja.gdansk.voyage2.route.mapper;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Component;
import pl.edu.pja.gdansk.voyage2.route.domain.TextElementPoint;
import pl.edu.pja.gdansk.voyage2.route.request.TextElementPointRequest;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TextElementPointsMapper {

    public List<TextElementPoint> map(List<TextElementPointRequest> points) {
        return points.stream().map(this::map).collect(Collectors.toList());
    }

    public TextElementPoint map(TextElementPointRequest textElementPointRequest) {
        TextElementPoint textElementPoint = new TextElementPoint();
        textElementPoint.setPoint(new GeoJsonPoint(textElementPointRequest.getPoint()));
        textElementPoint.setValue(textElementPointRequest.getValue());
        return textElementPoint;
    }
}
