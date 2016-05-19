package pl.edu.pja.gdansk.voyage2.folder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.gdansk.voyage2.folder.request.AddToFavoriteRouteRequest;
import pl.edu.pja.gdansk.voyage2.folder.service.AddToFavoriteRoutes;
import pl.edu.pja.gdansk.voyage2.folder.service.DeleteFromFavoriteRoutes;
import pl.edu.pja.gdansk.voyage2.route.domain.Route;
import pl.edu.pja.gdansk.voyage2.route.service.RouteFetcher;
import pl.edu.pja.gdansk.voyage2.security.domain.SecuredUserDetails;

import javax.validation.Valid;
import java.util.List;

@RestController
public class FavoriteRouteController {

    @Autowired
    private RouteFetcher routeFetcher;
    @Autowired
    private AddToFavoriteRoutes addToFavoriteRoutes;
    @Autowired
    private DeleteFromFavoriteRoutes deleteFromFavoriteRoutes;

    @RequestMapping(value = "/user/favorite-routes", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Route> find(@AuthenticationPrincipal(errorOnInvalidType = true) SecuredUserDetails principal) {
        return routeFetcher.findMyFavoriteRoutes(principal);
    }

    @RequestMapping(value = "/user/favorite-routes", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void add(@Valid @RequestBody AddToFavoriteRouteRequest addToFavoriteRouteRequest,
                    @AuthenticationPrincipal(errorOnInvalidType = true) SecuredUserDetails principal) {
        addToFavoriteRoutes.add(addToFavoriteRouteRequest, principal);
    }

    @RequestMapping(value = "/user/favorite-routes/{routeId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "routeId") String routeId,
                       @AuthenticationPrincipal(errorOnInvalidType = true) SecuredUserDetails principal) {
        deleteFromFavoriteRoutes.remove(routeId, principal);
    }
}
