package store_System.Security;

import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.util.HashMap;

public final class PassProtocol_Imp implements PasswordProtocol{
    public static PassProtocol_Imp Instance=new PassProtocol_Imp();
    public static HashMap<String,String> table;
    public static MessageDigest messageDigest;

    private PassProtocol_Imp(){
        table= new HashMap<>();
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error: creating hash function");
        }
    }

    @Override
    public boolean addRegistry(String id, String password) {
        if(table.containsKey(id))
            return false;
        messageDigest.update(password.getBytes());
        table.put(id,new String(messageDigest.digest()));
        return true;
    }

    @Override
    public boolean login(String id, String password) {
        messageDigest.update(password.getBytes());
        String pass=new String(messageDigest.digest());
        if(!table.containsKey(id))
            return false;
        return table.get(id).equals(pass);
    }

    @Override
    public boolean deleteRegistry(String id, String password) {
        if(login(id,password)) {
            table.remove(id);
            return true;
        }
        return false;
    }

    public static PassProtocol_Imp getInstance() {
        return Instance;
    }


}
