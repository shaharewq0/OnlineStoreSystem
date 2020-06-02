package Communication.websocket.App.RunServer;

import Communication.websocket.App.EncoderDecoder.MessageDecoder;
import Communication.websocket.App.EncoderDecoder.MessageEncoder;
import Communication.websocket.App.api_impl.MallProtocol;
import Communication.websocket.App.messages.api.Message;
import Communication.websocket.api.MessagingProtocol;
import Domain.Store.Product;
import Domain.info.ProductDetails;
import Service_Layer.guest_accese.guest_accese;
import Service_Layer.member_accese.member_accese;
import Service_Layer.owner_accese.owner_accese;
import org.glassfish.tyrus.server.Server;
import tests.AcceptanceTests.auxiliary.StoreDetails;

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
        initSysyem();

        protocols = new ConcurrentHashMap<>();
        Server server = new Server("localhost", 8080, "", MallServer.class);

        server.start();
        new Scanner(System.in).nextLine();
        server.stop();
    }

    private static void initSysyem(){
        int guestID = guest_accese.ImNew();
        if(guest_accese.usecase2_2_guest_register("abc", "abc")){
            System.out.println("good1");
        }
        else {
            System.out.println("bad1");
        }

        if(guest_accese.usecase2_3_login(guestID,"abc", "abc")){
            System.out.println("good2");
        }
        else {
            System.out.println("bad2");
        }

        if(member_accese.usecase3_2_OpenStore("abc", "abc", new StoreDetails("ebay", "tel aviv 12", 4))){
            System.out.println("good3");
        }
        else {
            System.out.println("bad3");
        }

        List<String> lst = new LinkedList<>();
        lst.add("fruits");
        if(owner_accese.usecase4_1_1_AddingProdacsToStore("abc", "abc", "ebay", new Product(new ProductDetails("banana", lst, "ebay", 300, 20.15)))){
            System.out.println("good4");
        }
        else {
            System.out.println("bad4");
        }
    }



    /** a map of message protocols by the session */
    private static Map<Session, MessagingProtocol<Message>> protocols;

    public void send(MessagingProtocol<Message> protocol, String msg){
        protocols.forEach(((session, messageMessagingProtocol) -> {
            if(messageMessagingProtocol == protocol){
                try {
                    session.getBasicRemote().sendText(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }));
    }



    public MallServer() { }

    @OnOpen
    public void onOpen(Session session){
        System.out.printf("[" + LocalDateTime.now() + "]: " + "Opened session. id: %s\n", session.getId());

        protocols.put(session, new MallProtocol(this));
    }


    @OnMessage
    public void onMessage(Session session, Message msg){
        System.out.printf("[" + LocalDateTime.now() + "]: " + "message received. session id: %s.  Message : %s\n", session.getId(), msg.toString());

        MessagingProtocol<Message> protocol = Objects.requireNonNull(protocols.get(session));
        Message response = protocol.process(msg);

        // sand back if there is a response
        if(response != null){
            try {
                session.getBasicRemote().sendObject(response);
                System.out.printf("[" + LocalDateTime.now() + "]: " + "sending message. session id: %s.  Message : %s\n", session.getId(), response.toString());
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
