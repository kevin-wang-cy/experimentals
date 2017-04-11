package kwang.experimentals.jersey;

import com.fasterxml.jackson.databind.ObjectMapper;
import kwang.experimentals.jersey.config.TrySpringRunnerBeanConfig;
import kwang.experimentals.jersey.service.TrySpringRunnerBeanService2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * @author kevin.wang.cy@gmail.com
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TrySpringRunnerIT.TestConfig.class, TrySpringRunnerBeanConfig.class})
public class TrySpringRunnerIT {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private TrySpringRunnerBeanService2 theService;

    @Test
    public void testServiceEchoMessage() {
        assertEquals("Hello the message", theService.getEchoMessage("the message"));
    }


    @Configuration
    public static class TestConfig {

        @Bean
        public ObjectMapper createMapper() {
            ObjectMapper mapper = new ObjectMapper();

            return mapper;
        }
    }
}
