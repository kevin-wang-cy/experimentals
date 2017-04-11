package kwang.experimentals.jersey.service;

import org.springframework.stereotype.Service;

/**
 * @author kevin.wang.cy@gmail.com
 */

@Service
public class TrySpringRunnerBeanService {
    public String getEchoMessage(String message) {
        return "echo " + message;
    }
}
