package Domain.store_System.Logs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public final class EventLogger implements Log{
    private static EventLogger INSTANCE=new EventLogger();
    private static File log;
    private static FileWriter fileWriter;
    private static int OpenWriters;

    private  EventLogger() {
        log = new File("Event_Log.txt");
        try {
            log.createNewFile();
            fileWriter = new FileWriter("Event_log.txt");
            OpenWriters=0;
        }
        catch (IOException e){
            System.out.println("error in creating event log file");
        }
    }


    @Override
    public void Add_Log(String msg) {
        try {
            fileWriter.write(msg);
            fileWriter.write("\n");
        } catch (IOException e) {
            System.out.println("error in printing to the event log file");
        }
    }

    @Override
    public void CloseLogger()  {
        OpenWriters--;
        if(OpenWriters==0) {
            try {
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("error in closing the event logger");
            }
        }
    }

    public static EventLogger GetInstance() {
        OpenWriters++;
        return  INSTANCE;
    }
}
