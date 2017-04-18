package experimentals.oauth2.sample1.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author kevin.wang.cy@gmail.com
 */

@RestController
@RequestMapping("/api")
public class APIControler {
    @Autowired
    private OAuth2RestTemplate resourceServerProxy;

    @Value("${resourceserver.rooturi:http://localhost:8801/rs}")
    private String resourceserverRootUri;


    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public Map<String, String> getMessage() {
        return resourceServerProxy.getForObject(resourceserverRootUri + "/", Map.class);
    }

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public void saveMessage(@RequestBody String newMessage) {
        resourceServerProxy.postForLocation(resourceserverRootUri + "/", newMessage);
    }
}
