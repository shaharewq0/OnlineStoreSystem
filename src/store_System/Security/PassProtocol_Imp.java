package store_System.Security;

import java.security.NoSuchAlgorithmException;
import java.util.Dictionary;
import java.security.MessageDigest;
import java.util.Enumeration;
import java.util.HashMap;

public final class PassProtocol_Imp implements PasswordProtocol{
    private static PassProtocol_Imp Instance;
    private static HashMap<String,String> table;
    private static MessageDigest messageDigest;

    private PassProtocol_Imp(){}

    @Override
    public PasswordProtocol getInstance() {
        if(Instance == null){
            Instance = new PassProtocol_Imp();
            table= new HashMap<>();
            try {
                messageDigest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return Instance;
        }



    @Override
    public boolean addRegistry(String id, String password) {
        messageDigest.update(password.getBytes());
        if(table.containsKey(id))
            return false;
        table.put(id,new String(messageDigest.digest()));
        return true;
    }

    @Override
    public boolean login(String id, String password) {
        messageDigest.update(password.getBytes());
        return table.get(id).equals(new String(messageDigest.digest()));
    }

    @Override
    public boolean deleteRegistry(String id, String password) {
        if(login(id,password)) {
            messageDigest.update(password.getBytes());
            return table.remove(id,new String(messageDigest.digest()));
        }
        return false;
    }


}
