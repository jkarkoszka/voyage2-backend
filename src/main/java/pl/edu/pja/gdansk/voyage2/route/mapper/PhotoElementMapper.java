package pl.edu.pja.gdansk.voyage2.route.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Component;
import pl.edu.pja.gdansk.voyage2.attachment.domain.Attachment;
import pl.edu.pja.gdansk.voyage2.attachment.repository.AttachmentRepository;
import pl.edu.pja.gdansk.voyage2.route.domain.Element;
import pl.edu.pja.gdansk.voyage2.route.domain.PhotoElement;
import pl.edu.pja.gdansk.voyage2.route.request.PhotoElementRequest;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class PhotoElementMapper {

    @Autowired
    private AttachmentRepository attachmentRepository;

    public List<Element> map(List<PhotoElementRequest> points) {
        return points.stream().map(this::map).filter(Objects::nonNull).collect(Collectors.toList());
    }

    private PhotoElement map(PhotoElementRequest element) {
        Attachment attachment = attachmentRepository.findOne(element.getAttachmentId());
        if (attachment == null) {
            return null;
        }
        PhotoElement photoElement = new PhotoElement();
        photoElement.setPoint(new GeoJsonPoint(element.getPoint()));
        photoElement.setValue(element.getValue());
        photoElement.setAttachment(attachment);
        return photoElement;
    }
}
