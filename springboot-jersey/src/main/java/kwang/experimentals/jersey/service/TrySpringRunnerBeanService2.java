package kwang.experimentals.jersey.service;

import org.springframework.stereotype.Service;

/**
 * @author kevin.wang.cy@gmail.com
 */

public class TrySpringRunnerBeanService2 {
    public String getEchoMessage(String message) {
        return "Hello " + message;
    }
}
