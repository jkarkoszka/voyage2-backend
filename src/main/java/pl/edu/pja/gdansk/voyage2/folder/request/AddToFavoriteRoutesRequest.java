package pl.edu.pja.gdansk.voyage2.folder.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddToFavoriteRoutesRequest {

    @NotNull
    @Size(min = 1)
    private String routeId;

    @JsonCreator
    public AddToFavoriteRoutesRequest(@JsonProperty("routeId") String routeId) {
        this.routeId = routeId;
    }

    public String getRouteId() {
        return routeId;
    }
}
