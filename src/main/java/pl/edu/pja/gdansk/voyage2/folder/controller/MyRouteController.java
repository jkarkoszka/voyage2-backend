package pl.edu.pja.gdansk.voyage2.folder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pja.gdansk.voyage2.route.domain.Route;
import pl.edu.pja.gdansk.voyage2.route.service.RouteFetcher;
import pl.edu.pja.gdansk.voyage2.security.domain.SecuredUserDetails;

import java.util.List;

@RestController
public class MyRouteController {

    @Autowired
    private RouteFetcher routeFetcher;

    @RequestMapping(value = "/user/my-routes", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Route> find(@AuthenticationPrincipal(errorOnInvalidType = true) SecuredUserDetails principal) {
        return routeFetcher.findMyRoutes(principal);
    }
}
