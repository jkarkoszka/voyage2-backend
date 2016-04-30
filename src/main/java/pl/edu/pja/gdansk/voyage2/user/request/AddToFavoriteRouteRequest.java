package pl.edu.pja.gdansk.voyage2.user.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddToFavoriteRouteRequest {

    @NotNull
    @Size(min = 1)
    private String routeId;

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }
}
