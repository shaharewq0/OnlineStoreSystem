package extornal.Security;

import DAL.Password_DA;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

 public final class PassProtocol_Imp implements PasswordProtocol{
    public static PassProtocol_Imp Instance=null;
    public static List<Password> table;
    public static MessageDigest messageDigest;
    //public static Password_DA da;


    private PassProtocol_Imp(){
        //da= new Password_DA();
        table= new LinkedList<>();
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error: creating hash function");
        }
    }

    @Override
    synchronized public boolean addRegistry(String userID, String password) {
        for(Password pass: table){
            if(pass.getUserID().equals(userID))
                return false;
        }
        messageDigest.update(password.getBytes());
        String hash=new String(messageDigest.digest());
        Password pass = new Password(userID,hash);
        table.add(pass);
        //da.add(pass);
        return true;
    }

    @Override
    synchronized public boolean login(String userID, String password) {
        messageDigest.update(password.getBytes());
        String hash=new String(messageDigest.digest());
        for(Password pass: table){
            if(pass.getUserID().equals(userID))
                return pass.getPasswordHash().equals(hash);
        }
        return false;
    }

    @Override
    public boolean deleteRegistry(String userID, String password) {
        messageDigest.update(password.getBytes());
        String hash=new String(messageDigest.digest());

        for(Password pass: table){
            if(pass.getUserID().equals(userID) && pass.getPasswordHash().equals(hash)) {
                table.remove(pass);
                //da.delete(pass);
                return true;
            }
        }

        return false;
    }

    public static PassProtocol_Imp getInstance() {
        if (Instance == null) {
            Instance = new PassProtocol_Imp();
        }
        return Instance;
    }

    public void reset() {
        Instance = null;    //TODO: temp
    }
}
