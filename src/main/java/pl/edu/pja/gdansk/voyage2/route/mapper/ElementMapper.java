package pl.edu.pja.gdansk.voyage2.route.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pja.gdansk.voyage2.route.domain.Element;
import pl.edu.pja.gdansk.voyage2.route.request.AddRouteRequest;
import pl.edu.pja.gdansk.voyage2.route.request.EditRouteRequest;

import java.util.ArrayList;
import java.util.List;

@Component
public class ElementMapper {

    @Autowired
    private TextElementMapper textElementMapper;
    @Autowired
    private PhotoElementMapper photoElementMapper;

    public List<Element> map(AddRouteRequest addRouteRequest) {
        List<Element> textElements = textElementMapper.map(addRouteRequest.getTextElements());
        List<Element> photoElements = photoElementMapper.map(addRouteRequest.getPhotoElements());
        return getElements(textElements, photoElements);
    }

    private List<Element> getElements(List<Element> textElements, List<Element> photoElements) {
        List<Element> elements = new ArrayList<>();
        elements.addAll(textElements);
        elements.addAll(photoElements);
        return elements;
    }

    public List<Element> map(EditRouteRequest editRouteRequest) {
        List<Element> textElements = textElementMapper.map(editRouteRequest.getTextElements());
        List<Element> photoElements = photoElementMapper.map(editRouteRequest.getPhotoElements());
        return getElements(textElements, photoElements);
    }
}
