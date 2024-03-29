package util;

import packets.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Sharukh Hasan on 11/29/16.
 */
public class Network {
    private static Socket socket = null;
    private static ObjectOutputStream out = null;
    private static ObjectInputStream in = null;

    public static void connect(String server){
        try {
            socket = new Socket(server, 3306);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static boolean login(String username, String passwordHash){
        try {
            out.writeObject(new Login(username, passwordHash));
            Object response;
            response = in.readObject();
            if(response != null){
                if(response instanceof LoginResponse){
                    LoginResponse loginResponse = (LoginResponse)response;
                    if(loginResponse.loginSuccessful){
                        System.out.println("Login Successful!");
                        out.writeObject(new CloseConnection());

                    }else{
                        System.out.println("Login Failed!");
                        out.writeObject(new CloseConnection());

                    }
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public static boolean createAccount(String username, String passwordHash) {
        try {
            out.writeObject(new CreateAccount(username, passwordHash));
            Object response;
            //while(true){
            response = in.readObject();
            if(response != null){
                if(response instanceof CreateResponse){
                    CreateResponse accountResponse = (CreateResponse)response;
                    if(accountResponse.creationSuccessful){
                        System.out.println("Account Creation Successful!");
                        out.writeObject(new CloseConnection());
                        //break;
                    }else{
                        System.out.println("Account Creation Failed!");
                        out.writeObject(new CloseConnection());
                        //break;
                    }
                }
            }
            //}
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
}
