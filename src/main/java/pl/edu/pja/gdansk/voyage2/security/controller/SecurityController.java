package pl.edu.pja.gdansk.voyage2.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.gdansk.voyage2.security.domain.SecuredUserDetails;
import pl.edu.pja.gdansk.voyage2.security.response.UserTokenResponse;
import pl.edu.pja.gdansk.voyage2.user.domain.User;
import pl.edu.pja.gdansk.voyage2.user.repository.UserRepository;

import javax.servlet.http.HttpSession;

@RestController
public class SecurityController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/user/token", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    UserTokenResponse createToken(HttpSession session, @AuthenticationPrincipal(errorOnInvalidType = true) SecuredUserDetails principal) {
        User user = userRepository.findByUsername(principal.getUsername());
        return new UserTokenResponse(session.getId(), user.getUsername(), user.getAuthorities());
    }

    @RequestMapping(value = "/user/token", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void invalidateToken(HttpSession session) {
        session.invalidate();
    }
}
