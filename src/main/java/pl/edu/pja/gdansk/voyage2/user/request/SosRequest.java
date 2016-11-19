package pl.edu.pja.gdansk.voyage2.user.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SosRequest {

    @NotNull
    @Size(min = 1)
    private String position;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
