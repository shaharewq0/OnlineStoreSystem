package store_System.Logs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ErrorLogger implements Log {
    private static ErrorLogger INSTANCE;
    private static File log;
    private static FileWriter fileWriter;

    @Override
    public void Log(String msg) {
        try {
            fileWriter.write(msg);
        } catch (IOException e) {
            System.out.println("error in printing to the error-log file");
        }
    }

    @Override
    public void CloseLogger()  {
        try {
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("error in closing the error-logger");
        }
    }

    @Override
    public Log GetInstance() {
        if (INSTANCE == null)
        {
            INSTANCE= new ErrorLogger();
            log = new File("/LOG/Error_Log.txt");
            try {
                log.createNewFile();
                fileWriter = new FileWriter("/LOG/Error_log.txt");
            }
            catch (IOException e){
                System.out.println("error in creating error-log file");
            }
        }
        return  INSTANCE;
    }
}
