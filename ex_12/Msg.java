package ex_12;

import java.io.Serializable;

public class Msg implements Serializable {
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
