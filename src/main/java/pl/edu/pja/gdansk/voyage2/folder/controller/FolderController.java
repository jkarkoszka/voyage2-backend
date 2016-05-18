package pl.edu.pja.gdansk.voyage2.folder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.gdansk.voyage2.folder.domain.Folder;
import pl.edu.pja.gdansk.voyage2.folder.domain.FolderType;
import pl.edu.pja.gdansk.voyage2.folder.request.AddFolderRequest;
import pl.edu.pja.gdansk.voyage2.folder.request.AddToFavoriteRouteRequest;
import pl.edu.pja.gdansk.voyage2.folder.service.*;
import pl.edu.pja.gdansk.voyage2.route.domain.Route;
import pl.edu.pja.gdansk.voyage2.route.service.RouteFetcher;
import pl.edu.pja.gdansk.voyage2.security.domain.SecuredUserDetails;

import javax.validation.Valid;
import java.util.List;

@RestController
public class FolderController {

    @Autowired
    private RouteFetcher routeFetcher;
    @Autowired
    private AddToFavoriteRoutes addToFavoriteRoutes;
    @Autowired
    private RemoveFromFavoriteRoutes removeFromFavoriteRoutes;
    @Autowired
    private AddFolder addFolder;
    @Autowired
    private FolderFetcher folderFetcher;
    @Autowired
    private RemoveFolder removeFolder;

    @RequestMapping(value = "/user/favorite-routes", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Route> listFavoriteRoutes(@AuthenticationPrincipal(errorOnInvalidType = true) SecuredUserDetails principal) {
        return routeFetcher.findMyFavoriteRoutes(principal);
    }

    @RequestMapping(value = "/user/my-routes", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Route> listMyRoutes(@AuthenticationPrincipal(errorOnInvalidType = true) SecuredUserDetails principal) {
        return routeFetcher.findMyRoutes(principal);
    }

    @RequestMapping(value = "/user/favorite-routes", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addToFavoriteRoute(@Valid @RequestBody AddToFavoriteRouteRequest addToFavoriteRouteRequest,
                                   @AuthenticationPrincipal(errorOnInvalidType = true) SecuredUserDetails principal) {
        addToFavoriteRoutes.add(addToFavoriteRouteRequest, principal);
    }

    @RequestMapping(value = "/user/favorite-routes/{routeId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeFromFavoriteRoute(@PathVariable(value = "routeId") String routeId,
                                   @AuthenticationPrincipal(errorOnInvalidType = true) SecuredUserDetails principal) {
        removeFromFavoriteRoutes.remove(routeId, principal);
    }

    @RequestMapping(value = "/user/my-routes/folders", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Folder addFolderToMyRoutes(@Valid @RequestBody AddFolderRequest addFolderRequest,
                                    @AuthenticationPrincipal(errorOnInvalidType = true) SecuredUserDetails principal) {
        return addFolder.add(addFolderRequest, principal);
    }

    @RequestMapping(value = "/user/my-routes/folders", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Folder> listMyRoutesFolders(@AuthenticationPrincipal(errorOnInvalidType = true) SecuredUserDetails principal) {
        return folderFetcher.find(principal);
    }

    @RequestMapping(value = "/user/my-routes/folders/{folderId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeFolderFromMyRoutes(@PathVariable(value = "folderId") String folderId,
                                         @AuthenticationPrincipal(errorOnInvalidType = true) SecuredUserDetails principal) {
        removeFolder.remove(folderId, principal);
    }
}
