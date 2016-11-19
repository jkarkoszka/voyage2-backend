package pl.edu.pja.gdansk.voyage2.user.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ChangeAvatarRequest {

    @NotNull
    @Size(min = 1)
    private String attachmentId;

    @JsonCreator
    public ChangeAvatarRequest(@JsonProperty("attachmentId") String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getAttachmentId() {
        return attachmentId;
    }
}
