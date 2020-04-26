package Domain.store_System.Logs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ErrorLogger implements Log {
    private static ErrorLogger INSTANCE=new ErrorLogger();
    private static File log;
    private static FileWriter fileWriter;
    private static int OpenWriters;

    private ErrorLogger(){
        log = new File("Error_Log.txt");
        try {
            log.createNewFile();
            fileWriter = new FileWriter("Error_log.txt");
            OpenWriters=0;
        }
        catch (IOException e){
            System.out.println("error in creating error-log file");
        }
    }

    @Override
    public void Add_Log(String msg) {
        try {
            fileWriter.write(msg);
            fileWriter.write("\n");
        } catch (IOException e) {
            System.out.println("error in printing to the error-log file");
        }
    }
    

    @Override
    public void CloseLogger()  {
        OpenWriters--;
        if(OpenWriters==0) {
            try {
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("error in closing the error-logger");
            }
        }
    }

    public static ErrorLogger GetInstance() {
        OpenWriters++;
        return  INSTANCE;
    }
}
