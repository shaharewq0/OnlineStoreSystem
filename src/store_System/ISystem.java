package store_System;

public interface ISystem {
    public boolean register(int id, String password);
    public boolean login(int id, String password);
}
