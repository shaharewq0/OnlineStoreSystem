package Communication.websocket.Logger;

import Domain.Logs.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


public class ServerLogger implements Log {
    private static ServerLogger INSTANCE;

    //private static FileWriter fileWriter;
    private static final String fileName = "ServerLogger.txt";

    private ServerLogger() {
        try {
            new File(fileName).createNewFile();
            //fileWriter = new FileWriter(fileName);
        } catch (IOException e) {
            System.out.println("error in creating server log file");
        }
    }

    @Override
    public void Add_Log(String msg) {
        try {
            //fileWriter.write(msg + "\n");
            //fileWriter.flush();
            Files.write(Paths.get(fileName), (msg + "\n").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("error in printing to the server log file");
        }
    }


    @Override
    public void CloseLogger() {
        /*try {
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("error in closing the server logr");
        }*/
    }

    public static ServerLogger GetInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServerLogger();
        }

        return INSTANCE;
    }
}
