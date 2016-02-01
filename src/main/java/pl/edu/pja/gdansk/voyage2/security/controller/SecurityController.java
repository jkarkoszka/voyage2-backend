package pl.edu.pja.gdansk.voyage2.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.gdansk.voyage2.security.domain.SecuredUserDetails;
import pl.edu.pja.gdansk.voyage2.security.response.UserTokenResponse;

import javax.servlet.http.HttpSession;

@RestController
public class SecurityController {

    @RequestMapping(value = "/user/token", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    UserTokenResponse createToken(HttpSession session, @AuthenticationPrincipal(errorOnInvalidType = true) SecuredUserDetails principal) {
        return new UserTokenResponse(session.getId(), principal.getUser().getEmail(), principal.getUser().getAuthorities());
    }

    @RequestMapping(value = "/user/token", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void invalidateToken(HttpSession session) {
        session.invalidate();
    }
}
