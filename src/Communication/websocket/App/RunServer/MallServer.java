package Communication.websocket.App.RunServer;

import Communication.websocket.App.EncoderDecoder.MessageDecoder;
import Communication.websocket.App.EncoderDecoder.MessageEncoder;
import Communication.websocket.App.api_impl.MallProtocol;
import Communication.websocket.App.messages.api.Message;
import org.glassfish.tyrus.server.Server;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(
        value = "/mall",
        encoders = { MessageEncoder.class },
        decoders = { MessageDecoder.class }
)
public class MallServer {


    // IMPORTANT !!!!
    // In order to allow row wss, on need to run the following command in the terminal (from the RunServer folder)
    // sudo haproxy -f ws.cfg

    // Domain :
    // wss://workshopv2.ddnsking.com/mall


    public static void main(String[] args) throws DeploymentException {
        run();
    }

    public static void run() throws DeploymentException {
        protocols = new ConcurrentHashMap<>();

        Server server = new Server("localhost", 8080, "", MallServer.class);

        server.start();
        new Scanner(System.in).nextLine();
        server.stop();
    }



    /** a map of message protocols by the session */
    private static Map<Session, MallProtocol> protocols;

    public MallServer() { }

    @OnOpen
    public void onOpen(Session session){
        System.out.printf("[" + LocalDateTime.now() + "]: " + "Opened session. id: %s\n", session.getId());
        protocols.put(session, new MallProtocol());
    }


    @OnMessage
    public void onMessage(Session session, Message msg){
        System.out.printf("[" + LocalDateTime.now() + "]: " + "message received. session id: %s.  Message : %s\n", session.getId(), msg.toString());

        MallProtocol protocol = Objects.requireNonNull(protocols.get(session));
        Message response = protocol.process(msg);

        // sand back if there is a response
        if(response != null){
            try {
                session.getBasicRemote().sendObject(response);
                System.out.printf("[" + LocalDateTime.now() + "]: " + "sending message. session id: %s.  Message : %s", session.getId(), response.toString());
            } catch (IOException | EncodeException e) {
                e.printStackTrace();
            }
        }
    }

    @OnError
    public void onError(Throwable e){
        e.printStackTrace();
    }

    @OnClose
    public void onClose(Session session){
        System.out.println(String.format("closeted session. id: %s", session.getId()));
        protocols.remove(session);
    }



    /**
     * convert a klist to array
     * @return the list to convert
     */
    private byte[] list2array(Deque<Byte> lst){

        int size = lst.size();
        int i = 0;

        byte[] arr = new byte[size];

        for (Byte b: lst){
            arr[i] = b;
            i++;
        }

        return  arr;
    }
}
