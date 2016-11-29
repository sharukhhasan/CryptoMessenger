import packets.CreateAccount;
import packets.Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Sharukh Hasan on 11/29/16.
 */
public class LoginProvider {
    static Connection connection = null;
    static Statement statement = null;

    public static void connectToDb(){
        try {
            connection = DriverManager.getConnection("http://cryptomessenger.crak0tcyjztl.us-east-1.rds.amazonaws.com", "sharukhhasan", "seniordesign");
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Connection to sql server failed!");
            e.printStackTrace();
        }
    }

    public static boolean checkLogin(Login login){
        if(isValid(login.username)){
            ResultSet result;
            try {
                result = statement.executeQuery("SELECT password FROM accounts WHERE username = '" + login.username + "';");
                result.first();
                if(login.passwordHash.equals(result.getString(1))){
                    System.out.println("User '" + login.username + "' logged in!");
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean checkUsername(CreateAccount login){
        if(isValid(login.username)){
            ResultSet result;
            try {
                result = statement.executeQuery("SELECT * FROM accounts WHERE username = '" + login.username + "';");
                if(result.next() == false){
                    return true;
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("Account creation failed for username '" + login.username + "'");
        return false;
    }

    public static boolean addUser(CreateAccount login){
        try {
            statement.execute("INSERT INTO accounts(username, password) VALUES ('" + login.username + "','" + login.passwordHash + "');");
            System.out.println("Created account for user " + login.username);
            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isValid(String username){
        if(!username.contains(";")){
            return true;
        }
        return false;
    }

}