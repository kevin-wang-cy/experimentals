package kwang.experimentals.jersey.config;

import kwang.experimentals.jersey.service.TrySpringRunnerBeanService2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kevin.wang.cy@gmail.com
 */
@Configuration
public class TrySpringRunnerBeanConfig {

    @Bean
    public TrySpringRunnerBeanService2 createBeanService2() {
        return new TrySpringRunnerBeanService2();
    }
}
