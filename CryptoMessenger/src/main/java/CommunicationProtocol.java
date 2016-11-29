import packets.CreateAccount;
import packets.CreateResponse;
import packets.Login;
import packets.LoginResponse;

/**
 * Created by Sharukh Hasan on 11/29/16.
 */
public class CommunicationProtocol {

    public static Object handleInput(Object object){
        if(object instanceof Login){
            if(LoginProvider.checkLogin((Login) object)){
                return new LoginResponse(true);
            }

            return new LoginResponse(false);
        } else if(object instanceof CreateAccount){
            if(LoginProvider.checkUsername((CreateAccount) object)){
                System.out.println("uname available");
                if(LoginProvider.addUser((CreateAccount) object)){
                    System.out.println("account made");
                    return new CreateResponse(true);
                }
            }

            return new CreateResponse(false);
        }
        return null;
    }
}
