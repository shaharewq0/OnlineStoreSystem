package Store;

public interface IUser {
    public boolean register(int id, String password);
    public boolean login(int id, String password);
}
