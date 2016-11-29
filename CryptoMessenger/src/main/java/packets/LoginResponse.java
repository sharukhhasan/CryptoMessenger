package packets;

import java.io.Serializable;

/**
 * Created by Sharukh Hasan on 11/29/16.
 */
public class LoginResponse implements Serializable {
    private static final long serialVersionUID = 724043919519375872L;

    public boolean loginSuccessful;

    public LoginResponse(boolean success) {
        this.loginSuccessful = success;
    }
}
