package experimentals.oauth2.sample1.authserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author kevin.wang.cy@gmail.com
 */

@SpringBootApplication
public class AuthServer {

    private static final Logger logger = LoggerFactory.getLogger(AuthServer.class);

    public static void main(String[] args) {
        SpringApplication.run(AuthServer.class, args);
    }
}
