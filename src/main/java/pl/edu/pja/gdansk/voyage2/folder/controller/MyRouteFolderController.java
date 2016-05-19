package pl.edu.pja.gdansk.voyage2.folder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.gdansk.voyage2.folder.domain.Folder;
import pl.edu.pja.gdansk.voyage2.folder.request.AddFolderRequest;
import pl.edu.pja.gdansk.voyage2.folder.service.AddFolder;
import pl.edu.pja.gdansk.voyage2.folder.service.FolderFetcher;
import pl.edu.pja.gdansk.voyage2.folder.service.DeleteFolder;
import pl.edu.pja.gdansk.voyage2.security.domain.SecuredUserDetails;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MyRouteFolderController {

    @Autowired
    private AddFolder addFolder;
    @Autowired
    private FolderFetcher folderFetcher;
    @Autowired
    private DeleteFolder deleteFolder;

    @RequestMapping(value = "/user/my-routes/folders", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Folder add(@Valid @RequestBody AddFolderRequest addFolderRequest,
                      @AuthenticationPrincipal(errorOnInvalidType = true) SecuredUserDetails principal) {
        return addFolder.add(addFolderRequest, principal);
    }

    @RequestMapping(value = "/user/my-routes/folders", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Folder> find(@AuthenticationPrincipal(errorOnInvalidType = true) SecuredUserDetails principal) {
        return folderFetcher.find(principal);
    }

    @RequestMapping(value = "/user/my-routes/folders/{folderId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "folderId") String folderId,
                       @AuthenticationPrincipal(errorOnInvalidType = true) SecuredUserDetails principal) {
        deleteFolder.delete(folderId, principal);
    }
}
