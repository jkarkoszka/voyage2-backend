package pl.edu.pja.gdansk.voyage2.route.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class ShareRouteRequest {

    @NotNull
    private String email;

    @JsonCreator
    public ShareRouteRequest(
            @JsonProperty("email") String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
