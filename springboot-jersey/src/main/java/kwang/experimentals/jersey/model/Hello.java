package kwang.experimentals.jersey.model;

/**
 * @author kevin.wang.cy@gmail.com
 */

public class Hello {

    private String msg;
    private Clock clock;

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Clock getClock() {
        return  this.clock;
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }
}
