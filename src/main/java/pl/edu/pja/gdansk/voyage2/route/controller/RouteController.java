package pl.edu.pja.gdansk.voyage2.route.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.gdansk.voyage2.route.domain.Route;
import pl.edu.pja.gdansk.voyage2.route.exception.RouteAccessDeniedException;
import pl.edu.pja.gdansk.voyage2.route.exception.RouteIdsNotMatchException;
import pl.edu.pja.gdansk.voyage2.route.exception.RouteNotFoundException;
import pl.edu.pja.gdansk.voyage2.route.request.AddRouteRequest;
import pl.edu.pja.gdansk.voyage2.route.request.EditRouteRequest;
import pl.edu.pja.gdansk.voyage2.route.request.ShareRouteRequest;
import pl.edu.pja.gdansk.voyage2.route.service.*;
import pl.edu.pja.gdansk.voyage2.security.domain.SecuredUserDetails;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RouteController {

    @Autowired
    private AddRoute addRoute;
    @Autowired
    private EditRoute editRoute;
    @Autowired
    private DeleteRoute deleteRoute;
    @Autowired
    private RouteFetcher routeFetcher;
    @Autowired
    private ShareRoute shareRoute;

    @RequestMapping(value = "/routes", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Route add(@Valid @RequestBody AddRouteRequest addRouteRequest, @AuthenticationPrincipal(errorOnInvalidType = true) SecuredUserDetails principal) {
        return addRoute.add(principal, addRouteRequest);
    }

    @RequestMapping(value = "/route/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Route edit(
            @PathVariable String id,
            @Valid @RequestBody EditRouteRequest editRouteRequest,
            @AuthenticationPrincipal(errorOnInvalidType = true) SecuredUserDetails principal
    ) throws RouteNotFoundException, RouteIdsNotMatchException, RouteAccessDeniedException {
        return editRoute.edit(principal, id, editRouteRequest);
    }

    @RequestMapping(value = "/route/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id, @AuthenticationPrincipal(errorOnInvalidType = true) SecuredUserDetails principal) throws RouteNotFoundException, RouteAccessDeniedException {
        deleteRoute.delete(principal, id);
    }

    @RequestMapping(value = "/route/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Route findOne(@PathVariable String id) throws RouteNotFoundException {
        return routeFetcher.findOneById(id);
    }

    @RequestMapping(value = "/route/{id}/users", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void share(
            @PathVariable String id,
            @Valid @RequestBody ShareRouteRequest shareRouteRequest,
            @AuthenticationPrincipal(errorOnInvalidType = true) SecuredUserDetails principal) {
        shareRoute.share(id, principal, shareRouteRequest);
    }

    @RequestMapping(value = "/routes/by-area", method = RequestMethod.GET)
    public List<Route> findByArea(
            @RequestParam(name = "tlX") double tlX,
            @RequestParam(name = "tlY") double tlY,
            @RequestParam(name = "blX") double blX,
            @RequestParam(name = "blY") double blY,
            @RequestParam(name = "brX") double brX,
            @RequestParam(name = "brY") double brY,
            @RequestParam(name = "trX") double trX,
            @RequestParam(name = "trY") double trY,
            @AuthenticationPrincipal(errorOnInvalidType = true) SecuredUserDetails principal
    ) {
        double distance = 1.0D;
        return routeFetcher.findByArea(
                new Point(tlX - distance, tlY + distance),
                new Point(blX - distance, blY - distance),
                new Point(brX + distance, brY - distance),
                new Point(trX + distance, trY + distance),
                principal
        );
    }
}
