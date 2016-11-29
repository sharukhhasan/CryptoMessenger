/**
 * Created by sharukhhasan on 11/29/16.
 */
public class CommunicationProtocol {

    public static Object handleInput(Object object){
        if(object instanceof PacketLogin){
            if(LoginProvider.checkLogin((PacketLogin)object)){
                return new PacketLoginResponse(true);
            }
            return new PacketLoginResponse(false);
        }
        else if(object instanceof PacketCreateAccount){
            if(LoginProvider.checkUsername((PacketCreateAccount)object)){
                System.out.println("uname available");
                if(LoginProvider.addUser((PacketCreateAccount)object)){
                    System.out.println("account made");
                    return new PacketCreateAccountResponse(true);
                }
            }
            return new PacketCreateAccountResponse(false);
        }
        return null;
    }
}
