package Communication.websocket.Logger;

import Domain.Logs.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class ServerErrorLog implements Log {
    private static ServerErrorLog INSTANCE;

    private static FileWriter fileWriter;
    private static final String fileName = "ServerErrorLogger.txt";

    private ServerErrorLog(){
        try {
            new File(fileName).createNewFile();
            fileWriter = new FileWriter(fileName);
        }
        catch (IOException e){
            System.out.println("error in creating server log file");
        }
    }

    @Override
    public void Add_Log(String msg) {
        try {
            fileWriter.write(msg + "\n");
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println("error in printing to the server log file");
        }
    }


    @Override
    public void CloseLogger()  {
        try {
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("error in closing the server logr");
        }
    }

    public static ServerErrorLog GetInstance() {
        if(INSTANCE == null){
            INSTANCE = new ServerErrorLog();
        }

        return  INSTANCE;
    }
}
