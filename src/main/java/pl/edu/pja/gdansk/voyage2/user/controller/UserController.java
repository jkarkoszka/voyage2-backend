package pl.edu.pja.gdansk.voyage2.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.gdansk.voyage2.security.domain.SecuredUserDetails;
import pl.edu.pja.gdansk.voyage2.user.domain.User;
import pl.edu.pja.gdansk.voyage2.user.request.*;
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
    private ChangeIsPublic changeIsPublic;
    @Autowired
    private ChangeSosEmail changeSosEmail;
    @Autowired
    private ChangeAvatar changeAvatar;
    @Autowired
    private DeleteAvatar deleteAvatar;
    @Autowired
    private SosSender sosSender;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@Valid @RequestBody RegisterUserRequest registerUserRequest) {
        return registerUser.register(registerUserRequest);
    }

    @RequestMapping(value = "/user/activationToken/{activationToken}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    String activate(@PathVariable String activationToken) {
        activateUser.activate(activationToken);
        return "Konto zostało aktywowane.";
    }

    @RequestMapping(value = "/user/password", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {
        resetPassword.reset(resetPasswordRequest);
    }

    @RequestMapping(value = "/user/avatar", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeAvatar(@Valid @RequestBody ChangeAvatarRequest changeAvatarRequest,
                             @AuthenticationPrincipal(errorOnInvalidType = true) SecuredUserDetails principal) {
        changeAvatar.change(changeAvatarRequest, principal);
    }

    @RequestMapping(value = "/user/avatar", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAvatar(@AuthenticationPrincipal(errorOnInvalidType = true) SecuredUserDetails principal) {
        deleteAvatar.delete(principal);
    }

    @RequestMapping(value = "/user/{userId}/password", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest,
                               @PathVariable String userId,
                               @AuthenticationPrincipal(errorOnInvalidType = true) SecuredUserDetails principal) {
        changePassword.change(changePasswordRequest, principal, userId);
    }

    @RequestMapping(value = "/user/{userId}/isPublic", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeIsActive(@Valid @RequestBody ChangeIsPublicRequest changeIsPublicRequest,
                               @PathVariable String userId,
                               @AuthenticationPrincipal(errorOnInvalidType = true) SecuredUserDetails principal) {
        changeIsPublic.change(changeIsPublicRequest, principal, userId);
    }

    @RequestMapping(value = "/user/{userId}/sosEmail", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeSosEmail(@Valid @RequestBody ChangeSosEmailRequest changeSosEmailRequest,
                                   @PathVariable String userId,
                                   @AuthenticationPrincipal(errorOnInvalidType = true) SecuredUserDetails principal) {
        changeSosEmail.change(changeSosEmailRequest, principal, userId);
    }

    @RequestMapping(value = "/sos", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sos(@Valid @RequestBody SosRequest sosRequest,
                    @AuthenticationPrincipal(errorOnInvalidType = true) SecuredUserDetails principal) {
        sosSender.send(principal, sosRequest);
    }
}
