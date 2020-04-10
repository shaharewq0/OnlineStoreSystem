package store_System.Logs;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.*;

public class EventLoggerTest {

    Log EventLogger1=EventLogger.GetInstance();
    Log EventLogger2=EventLogger.GetInstance();

    @Test
    public void log() {
        EventLogger1.Log("Test Test");
        EventLogger1.CloseLogger();
        EventLogger2.CloseLogger();
        File file=new File("Event_log.txt");
        try {
            Scanner scanner=new Scanner(file);
            assertEquals("Test Test",scanner.nextLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getInstance() {
        EventLogger1.Log("Test Test");
        EventLogger1.CloseLogger();
        EventLogger2.Log("Test Test");
        EventLogger2.CloseLogger();
        File file=new File("Event_log.txt");
        try {
            Scanner scanner=new Scanner(file);
            assertEquals("Test Test",scanner.nextLine());
            assertEquals("Test Test",scanner.nextLine());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}