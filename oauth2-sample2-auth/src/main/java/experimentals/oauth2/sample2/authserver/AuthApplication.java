package experimentals.oauth2.sample2.authserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author kevin.wang.cy@gmail.com
 */
@SpringBootApplication
public class AuthApplication {

        private static final Logger logger = LoggerFactory.getLogger(AuthApplication.class);

        public static void main(String[] args) {
            SpringApplication.run(AuthApplication.class, args);
        }
}
