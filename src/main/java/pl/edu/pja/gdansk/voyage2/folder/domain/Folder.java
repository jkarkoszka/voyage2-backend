package pl.edu.pja.gdansk.voyage2.folder.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.edu.pja.gdansk.voyage2.user.domain.User;

@Document
public class Folder {

    @Id
    private String id;

    private String name;

    @JsonIgnore
    @DBRef
    private User user;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
