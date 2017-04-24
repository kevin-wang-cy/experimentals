package experimentals.bdd.exception;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author kevin.wang.cy@gmail.com
 */
public class StepDefinitioins {

    List<Exception> exceptions = new ArrayList<>();

    @When("^I run a failing step")
    public void I_run_a_failing_step() throws Throwable {
        try {
            new KevinWoker().doWork();

            fail("Not possible step into here as there was an exception expteced.");
        }
        catch (RuntimeException ex) {
            exceptions.add(ex);
        }
    }


    @Then("^a step after failed step$")
    public void a_step_after_failed_step() throws Throwable {
       assertTrue(exceptions.size() > 0);
    }


}
