package pl.edu.pja.gdansk.voyage2.folder.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddFolderRequest {

    @NotNull
    @Size(min = 1)
    private String name;

    @JsonCreator
    public AddFolderRequest(@JsonProperty("name") String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
