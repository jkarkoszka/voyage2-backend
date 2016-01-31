package pl.edu.pja.gdansk.voyage2.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.gdansk.voyage2.user.request.RegisterUserRequest;
import pl.edu.pja.gdansk.voyage2.user.service.RegisterUser;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private RegisterUser registerUser;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody RegisterUserRequest registerUserRequest) {
        registerUser.createUser(registerUserRequest);
    }
}
