package experimentals.oauth2.sample1.rsserver.controller;

import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("#oauth2.hasScope('resource-server-read')")
    @RequestMapping(method = RequestMethod.GET)
    public Principal user(Principal user) {

        return user;
    }
}
