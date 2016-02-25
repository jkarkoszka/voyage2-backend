package pl.edu.pja.gdansk.voyage2.route.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Component;
import pl.edu.pja.gdansk.voyage2.attachment.domain.Attachment;
import pl.edu.pja.gdansk.voyage2.attachment.repository.AttachmentRepository;
import pl.edu.pja.gdansk.voyage2.route.domain.PhotoElementPoint;
import pl.edu.pja.gdansk.voyage2.route.request.PhotoElementPointRequest;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class PhotoElementPointsMapper {

    @Autowired
    private AttachmentRepository attachmentRepository;

    public List<PhotoElementPoint> map(List<PhotoElementPointRequest> points) {
        return points.stream().map(this::map).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public PhotoElementPoint map(PhotoElementPointRequest photoElementPointRequest) {
        Attachment attachment = attachmentRepository.findOne(photoElementPointRequest.getAttachmentId());
        if (attachment == null) {
            return null;
        }
        PhotoElementPoint photoElementPoint = new PhotoElementPoint();
        photoElementPoint.setPoint(new GeoJsonPoint(photoElementPointRequest.getPoint()));
        photoElementPoint.setValue(photoElementPointRequest.getValue());
        photoElementPoint.setAttachment(attachment);
        return photoElementPoint;
    }
}
