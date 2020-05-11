package Communication.websocket.Echo.secure;

import org.glassfish.tyrus.server.Server;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Scanner;



@ServerEndpoint("/echo")
public class EchoServer {

    private static final int port = 8080;
    
    public static void main(String[] args) throws DeploymentException {

        
        Server server = new Server("localhost", port, "", EchoServer.class);

        server.start();
        System.out.println("enter a line to stop server");
        new Scanner(System.in).nextLine();
        server.stop();
    }






    public EchoServer() {
        System.out.println("class EchoServer loaded " + this.getClass());
    }

    @OnOpen
    public void onOpen(Session session){
        System.out.printf("Opened session. id: %s\n", session.getId());

        try {
            session.getBasicRemote().sendText("Hello from server! connected successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

   @OnMessage
   public void onMessage(String message, Session session){
       System.out.printf("message received. session id: %s.   Message: %s\n", session.getId(), message);

       try {
           session.getBasicRemote().sendText(createEcho(message));
       } catch (IOException e) {
           e.printStackTrace();
       }

   }





   @OnError
   public void onError(Throwable e){
        e.printStackTrace();
   }

    @OnClose
    public void onClose(Session session){
        System.out.println(String.format("closeted session. id: %s", session.getId()));
    }



    private String createEcho(String message) {
        String echoPart = message.substring(Math.max(message.length() - 2, 0));
        return message + " .. " + echoPart + " .. " + echoPart + " ..";
    }
}
