package extornal.Security;

public interface PasswordProtocol {
        boolean addRegistry(String id,String password);
        boolean login(String id,String password);
        boolean deleteRegistry(String id,String password);
        void reset();
}
