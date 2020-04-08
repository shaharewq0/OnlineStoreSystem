package store_System.Logs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public final class EventLogger implements Log{
    private static EventLogger INSTANCE;
    private static File log;
    private static FileWriter fileWriter;
    private  EventLogger() {
    }

    @Override
    public void Log(String msg) {
        try {
            fileWriter.write(msg);
        } catch (IOException e) {
            System.out.println("error in printing to the event log file");
        }
    }

    @Override
    public void CloseLogger()  {
        try {
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("error in closing the event logger");
        }
    }

    @Override
    public Log GetInstance() {
        if (INSTANCE == null)
        {
            log = new File("/LOG/Event_Log.txt");
            try {
                log.createNewFile();
                fileWriter = new FileWriter("/LOG/Event_log.txt");
            }
            catch (IOException e){
                System.out.println("error in creating event log file");
            }
        }
        return  INSTANCE;
    }
}
