package Communication.websocket.App.RunServer;

import Communication.websocket.App.api_impl.MallServer;
import Service_Layer.InitSystemFromFile;
import org.glassfish.tyrus.server.Server;

import javax.websocket.DeploymentException;
import java.util.Scanner;

public class RunServer {

    // IMPORTANT !!!!
    // In order to allow  wss, one need to run the following command in the terminal (from the RunServer folder)
    // sudo haproxy -f ws.cfg
    // it is possible to not do it, but then connecting only with ip:8080 (no domain, port 8080 must be specified)

    // Domain :
    // wss://workshopv2.ddnsking.com/mall

    // final vertion domain :
    // wss://workshopfinal.ddnsking.com/mall


    public static void main(String[] args) throws DeploymentException {
        run();
    }

    public static void run() throws DeploymentException {
        initSystem();

        Server server = new Server("localhost", 8080, "", MallServer.class);

        server.start();
        new Scanner(System.in).nextLine();
        server.stop();
    }

    private static void initSystem(){
        InitSystemFromFile.init("initFile.txt");
    }

}
