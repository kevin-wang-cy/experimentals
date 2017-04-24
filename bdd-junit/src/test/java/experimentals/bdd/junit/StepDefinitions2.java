package experimentals.bdd.junit;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * @author kevin.wang.cy@gmail.com
 */
public class StepDefinitions2 {

    @Given("^below members:$")
    public void below_members(DataTable arg1) throws Throwable {
        System.out.println("aaaa " + arg1.raw().stream().count());
    }

    @Given("^below cosume info:$")
    public void below_cosume_info(DataTable arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
        // E,K,V must be a scalar (String, Integer, Date, enum etc)

        System.out.println("bbbbbb " + arg1.raw().stream().count());

        // throw new PendingException();
    }

    @When("^Execute Settlement$")
    public void execute_Settlement() throws Throwable {
        System.out.println("[1].execute_Settlement.....");
    }

    @Then("^Result should be like this$")
    public void result_should_be_like_this(DataTable arg1) throws Throwable {
        System.out.println("ccccc " + arg1.raw().stream().count());
    }

}
