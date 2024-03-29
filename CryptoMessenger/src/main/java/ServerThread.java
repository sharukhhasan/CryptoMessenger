import packets.CloseConnection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Sharukh Hasan on 11/29/16.
 */
public class ServerThread implements Runnable {
    private Socket socket;

    public ServerThread(Socket clientSocket){
        this.socket = clientSocket;
    }

    @Override
    public void run() {
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        Object request;

        try {
            out = new ObjectOutputStream(this.socket.getOutputStream());
            in = new ObjectInputStream(this.socket.getInputStream());
            while(true){
                try{
                    request = in.readObject();
                    if(request != null){
                        if(request instanceof CloseConnection){
                            break;
                        }
                        out.writeObject(CommunicationProtocol.handleInput(request));
                    }
                }catch (java.io.EOFException e){}
            }
        } catch (IOException e) {
            System.out.println("Failed to create IO streams!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
