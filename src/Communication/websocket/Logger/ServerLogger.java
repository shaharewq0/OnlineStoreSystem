package Communication.websocket.Logger;

import Domain.Logs.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class ServerLogger implements Log {
    private static ServerLogger INSTANCE=new ServerLogger();
    private static File log;
    private static FileWriter fileWriter;
    private static int OpenWriters;
    private static  String fileName = "ServerLogger.txt";

    private ServerLogger(){
        log = new File(fileName);
        try {
            log.createNewFile();
            fileWriter = new FileWriter(fileName);
            OpenWriters=0;
        }
        catch (IOException e){
            System.out.println("error in creating server log file");
        }
    }

    @Override
    public void Add_Log(String msg) {
        try {
            fileWriter.write(msg);
            fileWriter.write("\n");
        } catch (IOException e) {
            System.out.println("error in printing to the server log file");
        }
    }


    @Override
    public void CloseLogger()  {
        OpenWriters--;
        if(OpenWriters==0) {
            try {
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("error in closing the server logr");
            }
        }
    }

    public static ServerLogger GetInstance() {
        OpenWriters++;
        return  INSTANCE;
    }
}
