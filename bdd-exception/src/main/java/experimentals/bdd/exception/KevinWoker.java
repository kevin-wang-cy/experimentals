package experimentals.bdd.exception;

/**
 * @author kevin.wang.cy@gmail.com
 */
public class KevinWoker {

    public void doWork() {
        System.out.println("Calling DoWork.....");
        throw new RuntimeException("Sorry! Failed here.");
    }

}
