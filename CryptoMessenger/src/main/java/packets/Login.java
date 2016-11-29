package packets;

import java.io.Serializable;

/**
 * Created by Sharukh Hasan on 11/29/16.
 */
public class Login implements Serializable {
    private static final long serialVersionUID = -5849519141227484158L;

    public String username, passwordHash;

    public Login(String username, String passwordHash){
        this.username = username;
        this.passwordHash = passwordHash;
    }
}
