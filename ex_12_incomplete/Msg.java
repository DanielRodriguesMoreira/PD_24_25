package ex_12_incomplete;

public class Msg {
    public static final long serialVersionUID = 1010L;

    protected String nickname;
    protected String msg;

    public Msg(String nickname, String msg) {
        this.nickname = nickname;
        this.msg = msg;
    }

    public String getNickname() {
        return nickname;
    }

    public String getMsg() {
        return msg;
    }
}
