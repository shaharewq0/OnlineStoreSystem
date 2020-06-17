package extornal.Security;

public class Password {
    private int id;
    private String userID;
    private String passwordHash;

    public Password(){};

    public Password(String userID,String passwordHash){
        this.userID=userID;
        this.passwordHash=passwordHash;
    }

    public int getId() {
        return id;
    }

    public String getUserID() {
        return userID;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
