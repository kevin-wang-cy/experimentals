package experimentals.oauth2.sample1.rsserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

/**
 * @author kevin.wang.cy@gmail.com
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping(method = RequestMethod.GET)
    public Principal user(Principal user) {
        return user;
    }
}
