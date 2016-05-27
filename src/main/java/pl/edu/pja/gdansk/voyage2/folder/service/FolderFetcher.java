package pl.edu.pja.gdansk.voyage2.folder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pja.gdansk.voyage2.folder.domain.Folder;
import pl.edu.pja.gdansk.voyage2.folder.exception.FolderNotFoundException;
import pl.edu.pja.gdansk.voyage2.folder.repository.FolderRepository;
import pl.edu.pja.gdansk.voyage2.security.domain.SecuredUserDetails;
import pl.edu.pja.gdansk.voyage2.user.domain.User;
import pl.edu.pja.gdansk.voyage2.user.repository.UserRepository;

import java.util.List;
import java.util.Objects;

@Component
public class FolderFetcher {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FolderRepository folderRepository;

    public List<Folder> find(SecuredUserDetails securedUserDetails) {
        User user = userRepository.findByUsername(securedUserDetails.getUsername());
        return folderRepository.findByUser(user);
    }

    public Folder findOne(String folderId, User user) {
        Folder folder = folderRepository.findByIdAndUser(folderId, user);
        if (Objects.isNull(folder)) {
            throw new FolderNotFoundException();
        }
        return folder;
    }
}
