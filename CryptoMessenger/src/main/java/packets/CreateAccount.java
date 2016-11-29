package packets;

import java.io.Serializable;

/**
 * Created by Sharukh Hasan on 11/29/16.
 */
public class CreateAccount implements Serializable {
    private static final long serialVersionUID = 6200495373556789975L;

    public String username, passwordHash;

    public CreateAccount(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }
}
