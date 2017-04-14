package experimentals.oauth2.sample1.rsserver.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * @author kevin.wang.cy@gmail.com
 */
@RestController
@RequestMapping("/")
public class HomeController {
    private String message = "Hello world!";

    @RequestMapping(method = RequestMethod.GET)
    public Map<String, String> home() {
        return Collections.singletonMap("message", message);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void updateMessage(@RequestBody String message) {
        this.message = message;
    }
}
