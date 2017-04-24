package experimentals.bdd.junit;

import org.junit.After;
import org.junit.Before;

/**
 * @author kevin.wang.cy@gmail.com
 */
public class KevinTest {
    @Before
    public void setUp() throws Exception {
        System.out.println(this.getClass() + ".setUp()....");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println(this.getClass() + ".tearDown()....");
    }

}