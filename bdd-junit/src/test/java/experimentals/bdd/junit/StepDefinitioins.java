package experimentals.bdd.junit;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * @author kevin.wang.cy@gmail.com
 */
public class StepDefinitioins {

    Kevin kevin = null;

    @Given("^with kevin had not eaten anything$")
    public void with_kevin_was_not_eat_anything() throws Throwable {
        kevin = new Kevin();
    }

    @Given("^with kevin had eaten (\\d+) cucumbers$")
    public void with_kevin_had_eaten_cucumbers(int arg1) throws Throwable {
        kevin = new Kevin();
        kevin.eatMany(arg1);
    }


    @When("^kevin eat(\\d+) (\\d+) cucumbers$")
    public void kevin_eat_cucumbers(int arg1, int arg2) throws Throwable {
        if (arg1 == 1) {
            kevin.eatMany(arg2);
        }
        else if (arg1 == 2) {
            for (int i = 0; i < arg2; i++) kevin.eatOne();
        }
        else {
            throw new PendingException();
        }
    }

    @Then("^kevin can eat(\\d+) antoher (\\d+) cucumbers without problem$")
    public void kevin_can_eat_antoher_cucumbers_without_problem(int arg1, int arg2) throws Throwable {
        if (arg1 == 1) {
            kevin.eatMany(arg2);
        }
        else if (arg1 == 2) {
            for (int i = 0; i < arg2; i++) kevin.eatOne();
        }
        else {
            throw new PendingException();
        }
    }

    @Then("^Kevin should not able to eat(\\d+) (\\d+) more cucumber$")
    public void kevin_should_not_able_to_eat_more_cucumber(int arg1, int arg2) throws Throwable {
        try {
            if (arg1 == 1) {
                kevin.eatMany(arg2);
            } else if (arg1 == 2) {
                for (int i = 0; i < arg2; i++) kevin.eatOne();
            } else {
                throw new PendingException();
            }

            fail("EatTooMuchException is exptected!");
        }
        catch (Exception ex) {
            assertTrue(ex instanceof Kevin.EatTooMuchException);
        }

    }
}
