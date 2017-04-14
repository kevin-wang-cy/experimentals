package experimentals.oauth2.sample1.authserver.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author kevin.wang.cy@gmail.com
 */

@RestController
@RequestMapping("/me")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @RequestMapping(method = RequestMethod.GET)
    public Principal user(Principal user) {

        logger.info("AS /user has been called");
        logger.debug("user info: "+user.toString());



        return user;
    }
}
