package experimentals.oauth2.sample1.rsserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author kevin.wang.cy@gmail.com
 */
@SpringBootApplication
public class RSServer {

    public static void main(String[] args) {
        SpringApplication.run(RSServer.class, args);
    }
}
