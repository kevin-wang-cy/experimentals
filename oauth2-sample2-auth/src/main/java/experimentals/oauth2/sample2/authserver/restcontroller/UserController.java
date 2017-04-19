package experimentals.oauth2.sample2.authserver.restcontroller;

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

        // org.springframework.boot.autoconfigure.security.oauth2.resource.FixedPrincipalExtractor default below keys for principal
        // "user", "username", "userid", "user_id", "login", "id", "name"


        // org.springframework.boot.autoconfigure.security.oauth2.resource.FixedAuthoritiesExtractor
        // authorities => ["ROLE_USER1", "ROLE_AdMIN2"] or
        // authorities => [{"authority": "ROLE1"}, {"role": "ROLE_ADMIN"}, {"value": "ROLE_2"}, {"XX": "XXX", "authority":"ROLE_XXX"}]

        logger.info("AS /user has been called");
        logger.debug("user info: "+user.toString());



        return user;
    }
}
