package experimentals.bdd.exception;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

/**
 * @author kevin.wang.cy@gmail.com
 */

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"})
public class RunCucumberTest {
    @Before
    public void setUp() throws Exception {
        System.out.println("calling setUp");
    }

    @After
    public void tearDown() throws Exception {

        System.out.println("calling tearDown");
    }
}