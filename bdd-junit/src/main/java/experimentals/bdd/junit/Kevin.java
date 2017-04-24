package experimentals.bdd.junit;

/**
 * @author kevin.wang.cy@gmail.com
 */
public class Kevin {
    private int hasEaten = 0;

    public Kevin() {
        System.out.println("Kevin is created with " + this.hasEaten + " cucumber eated");
    }

    public void eatMany(int cukes) throws  EatTooMuchException {

        System.out.println("Hi, Kevin is going to eat " + cukes + " cucumbers");

        this.hasEaten += cukes;

        if (hasEaten > 10) throw new EatTooMuchException();
    }

    public void eatOne() throws  EatTooMuchException {
        this.eatMany(1);
    }

    public static class EatTooMuchException extends  Exception {

    }
}
