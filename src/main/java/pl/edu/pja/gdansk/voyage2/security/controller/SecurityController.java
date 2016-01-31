package pl.edu.pja.gdansk.voyage2.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.gdansk.voyage2.security.domain.Session;

import javax.servlet.http.HttpSession;

@RestController
public class SecurityController {

    @RequestMapping(value = "/user/token", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Session createToken(HttpSession session, @AuthenticationPrincipal User user) {
        return new Session(session.getId(), user.getUsername(), user.getAuthorities());
    }

    @RequestMapping(value = "/user/token", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void invalidateToken(HttpSession session) {
        session.invalidate();
    }
}
