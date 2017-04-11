package kwang.experimentals.jersey;

import kwang.experimentals.jersey.config.JerseyAPIConfiguration;
import kwang.experimentals.jersey.service.TrySpringRunnerBeanService;
import kwang.experimentals.jersey.service.TrySpringRunnerBeanService2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	@Autowired
	public TrySpringRunnerBeanService beanService;

	@Autowired
	public TrySpringRunnerBeanService2 beanService2;

	@Test
	public void testBeanServiceEchoMessage() {
		String echoMessage = beanService.getEchoMessage("another message");

		assertEquals("echo another message", echoMessage);

		echoMessage = beanService2.getEchoMessage("another message");

		assertEquals("Hello another message", echoMessage);
	}
}
