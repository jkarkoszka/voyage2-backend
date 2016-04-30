package pl.edu.pja.gdansk.voyage2.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.gdansk.voyage2.security.domain.SecuredUserDetails;
import pl.edu.pja.gdansk.voyage2.user.domain.User;
import pl.edu.pja.gdansk.voyage2.user.request.AddToFavoriteRouteRequest;
import pl.edu.pja.gdansk.voyage2.user.request.ChangePasswordRequest;
import pl.edu.pja.gdansk.voyage2.user.request.RegisterUserRequest;
import pl.edu.pja.gdansk.voyage2.user.request.ResetPasswordRequest;
import pl.edu.pja.gdansk.voyage2.user.service.*;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private RegisterUser registerUser;
    @Autowired
    private ResetPassword resetPassword;
    @Autowired
    private ActivateUser activateUser;
    @Autowired
    private ChangePassword changePassword;
    @Autowired
    private AddToFavoriteRoute addToFavoriteRoute;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@Valid @RequestBody RegisterUserRequest registerUserRequest) {
        return registerUser.createUser(registerUserRequest);
    }

    @RequestMapping(value = "/user/activationToken/{activationToken}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody String activate(@PathVariable String activationToken) {
        activateUser.activate(activationToken);
        return "Konto zostało aktywowane.";
    }

    @RequestMapping(value = "/user/password", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {
        resetPassword.reset(resetPasswordRequest);
    }

    @RequestMapping(value = "/user/{userId}/password", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public void changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest,
                               @PathVariable String userId,
                               @AuthenticationPrincipal(errorOnInvalidType = true) SecuredUserDetails principal) {
        changePassword.change(changePasswordRequest, principal, userId);
    }

    @RequestMapping(value = "/user/favorite-routes", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void addToFavoriteRoute(@Valid @RequestBody AddToFavoriteRouteRequest addToFavoriteRouteRequest,
                                   @AuthenticationPrincipal(errorOnInvalidType = true) SecuredUserDetails principal) {
        addToFavoriteRoute.add(addToFavoriteRouteRequest, principal);
    }
}
