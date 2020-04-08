package store_System.Logs;

public interface Log {
    void Log(String msg);
    void CloseLogger();
    Log GetInstance();
}
