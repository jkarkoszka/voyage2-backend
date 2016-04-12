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
import pl.edu.pja.gdansk.voyage2.route.service.AddRoute;
import pl.edu.pja.gdansk.voyage2.route.service.DeleteRoute;
import pl.edu.pja.gdansk.voyage2.route.service.EditRoute;
import pl.edu.pja.gdansk.voyage2.route.service.RouteFetcher;
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

    @RequestMapping(value = "/routes", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Route addRoute(@Valid @RequestBody AddRouteRequest addRouteRequest, @AuthenticationPrincipal(errorOnInvalidType = true) SecuredUserDetails principal) {
        return addRoute.add(principal, addRouteRequest);
    }

    @RequestMapping(value = "/route/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Route editRoute(
            @PathVariable String id,
            @Valid @RequestBody EditRouteRequest editRouteRequest,
            @AuthenticationPrincipal(errorOnInvalidType = true) SecuredUserDetails principal
    ) throws RouteNotFoundException, RouteIdsNotMatchException, RouteAccessDeniedException {
        return editRoute.edit(principal, id, editRouteRequest);
    }

    @RequestMapping(value = "/route/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRoute(@PathVariable String id, @AuthenticationPrincipal(errorOnInvalidType = true) SecuredUserDetails principal) throws RouteNotFoundException, RouteAccessDeniedException {
        deleteRoute.delete(principal, id);
    }

    @RequestMapping(value = "/route/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Route getRouteById(@PathVariable String id) throws RouteNotFoundException {
        return routeFetcher.findOneById(id);
    }

    @RequestMapping(value = "/routes/by-area", method = RequestMethod.GET)
    public List<Route> listRoutesByArea(
            @RequestParam(name = "x1") double x1,
            @RequestParam(name = "y1") double y1,
            @RequestParam(name = "x2") double x2,
            @RequestParam(name = "y2") double y2,
            @RequestParam(name = "x3") double x3,
            @RequestParam(name = "y3") double y3,
            @RequestParam(name = "x4") double x4,
            @RequestParam(name = "y4") double y4
    ) {
        return routeFetcher.findByArea(
                new Point(x1, y1),
                new Point(x2, y2),
                new Point(x3, y3),
                new Point(x4, y4)
        );
    }
}
