package store_System.Logs;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.*;

public class ErrorLoggerTest {

    Log ErrorLogger1=ErrorLogger.GetInstance();
    Log ErrorLogger2=ErrorLogger.GetInstance();

    @org.junit.Test
    public void log() {
        ErrorLogger1.Log("Test Test");
        ErrorLogger1.CloseLogger();
        ErrorLogger2.CloseLogger();
        File file=new File("Error_log.txt");
        try {
            Scanner scanner=new Scanner(file);
            assertEquals("Test Test",scanner.nextLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @org.junit.Test
    public void getInstance() {
        ErrorLogger1.Log("Test Test");
        ErrorLogger1.CloseLogger();
        ErrorLogger2.Log("Test Test");
        ErrorLogger2.CloseLogger();
        File file=new File("Error_log.txt");
        try {
            Scanner scanner=new Scanner(file);
            assertEquals("Test Test",scanner.nextLine());
            assertEquals("Test Test",scanner.nextLine());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}