package Communication.socket.Echo;

import Communication.socket.api.MessagingProtocol;
import Communication.socket.server.ConnectionHandler;

import java.time.LocalDateTime;
import java.util.Random;

public class EchoProtocol implements MessagingProtocol<String> {

    private boolean shouldTerminate = false;

    private ConnectionHandler<String> handler;

    @Override
    public String process(String msg) {
        shouldTerminate = "bye".equals(msg);
        System.out.println("[" + LocalDateTime.now() + "]: " + msg);

        if(msg.equals("surprise me!")){
           new Thread(() ->
           {
               int rnd = new Random().nextInt(10000);
               try {
                   Thread.sleep(rnd);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }

               handler.sendClient("surprise!");
           }).start();
        }

        return createEcho(msg);
    }

    @Override
    public void setConnectionHandler(ConnectionHandler<String> handler) {
        this.handler = handler;
    }

    private String createEcho(String message) {
        String echoPart = message.substring(Math.max(message.length() - 2, 0));
        return message + " .. " + echoPart + " .. " + echoPart + " ..";
    }

    @Override
    public boolean shouldTerminate() {
        return shouldTerminate;
    }
}
