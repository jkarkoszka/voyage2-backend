package pl.edu.pja.gdansk.voyage2.folder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pja.gdansk.voyage2.folder.domain.Folder;
import pl.edu.pja.gdansk.voyage2.folder.exception.FolderNameAlreadyUsedException;
import pl.edu.pja.gdansk.voyage2.folder.repository.FolderRepository;
import pl.edu.pja.gdansk.voyage2.folder.request.AddFolderRequest;
import pl.edu.pja.gdansk.voyage2.security.domain.SecuredUserDetails;
import pl.edu.pja.gdansk.voyage2.user.domain.User;
import pl.edu.pja.gdansk.voyage2.user.repository.UserRepository;

import java.util.Objects;

@Component
public class AddFolder {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FolderRepository folderRepository;

    public Folder add(AddFolderRequest addFolderRequest, SecuredUserDetails securedUserDetails) {
        User user = userRepository.findByUsername(securedUserDetails.getUsername());
        checkIfFolderNameIsNotAlreadyUsed(addFolderRequest, user);
        Folder folder = new Folder();
        folder.setName(addFolderRequest.getName());
        folder.setUser(user);
        return folderRepository.save(folder);
    }

    private void checkIfFolderNameIsNotAlreadyUsed(AddFolderRequest addFolderRequest, User user) {
        Folder folder = folderRepository.findByNameAndUser(addFolderRequest.getName(), user);
        if (!Objects.isNull(folder)) {
            throw new FolderNameAlreadyUsedException();
        }
    }
}
