package pl.edu.pja.gdansk.voyage2.user.request;

import javax.validation.constraints.NotNull;

public class ChangeIsPublicRequest {

    @NotNull
    private boolean isPublic;

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }
}
