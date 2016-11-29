package packets;

import java.io.Serializable;

/**
 * Created by Sharukh Hasan on 11/29/16.
 */
public class CreateResponse implements Serializable {
    private static final long serialVersionUID = 1525631574743843821L;

    public boolean creationSuccessful;

    public CreateResponse(boolean success) {
        this.creationSuccessful = success;
    }
}
