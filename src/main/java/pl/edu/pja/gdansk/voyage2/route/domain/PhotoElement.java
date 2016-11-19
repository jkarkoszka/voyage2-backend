package pl.edu.pja.gdansk.voyage2.route.domain;

import org.springframework.data.mongodb.core.mapping.DBRef;
import pl.edu.pja.gdansk.voyage2.attachment.domain.Attachment;

public class PhotoElement extends Element {

    @DBRef
    private Attachment attachment;

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }
}
