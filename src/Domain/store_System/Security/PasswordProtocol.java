package Domain.store_System.Security;

public interface PasswordProtocol {
        boolean addRegistry(String id,String password);
        boolean login(String id,String password);
        boolean deleteRegistry(String id,String password);
}