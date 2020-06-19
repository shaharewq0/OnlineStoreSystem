package Communication.websocket.api;


import Communication.websocket.Logger.ServerLogger;
import Domain.Logs.Log;

import javax.websocket.EncodeException;
import javax.websocket.Session;

import java.io.Closeable;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;


public class BaseServer<T> implements Closeable {

    /** a map of message protocols by the session */
    private Map<Session, MessagingProtocol<T>> protocols;

    /** suply a protocol fp each client */
    private Function<BaseServer<T>, MessagingProtocol<T>> protocolSupplier;

    // logger
    private Log logger;


    public BaseServer(Function<BaseServer<T>, MessagingProtocol<T>> protocolSupplier) {
        protocols = new ConcurrentHashMap<>();
        this.protocolSupplier = protocolSupplier;
        this.logger = ServerLogger.GetInstance();
    }


    @Override
    public void close() {
        logger.CloseLogger();
        protocols = null;
        protocolSupplier = null;
    }

    // ------------------------------------------------ server api ------------------------------------------------

    public void onOpen(Session session){
        print(String.format("[" + LocalDateTime.now() + "]: " + "Opened session. id: %s\n", session.getId()));

        protocols.put(session, protocolSupplier.apply(this));
    }


    public void onMessage(Session session, T msg){
        print(String.format("[" + LocalDateTime.now() + "]: " + "message received. session id: %s.  Message : %s\n", session.getId(), msg.toString()));

        MessagingProtocol<T> protocol = Objects.requireNonNull(protocols.get(session));
        T response = protocol.process(msg);

        // sand back if there is a response
        if(response != null){
            send(session, response);
            print(String.format("[" + LocalDateTime.now() + "]: " + "sending message. session id: %s.  Message : %s\n\n", session.getId(), response.toString()));
        }
    }

    public void onError(Session session, Throwable e){
        print(String.format("ERROR in session (id: %s) : %s", session.getId(), e.getMessage()));
        endSession(session);
    }

    public void onClose(Session session){
        print(String.format("closeted session. id: %s", session.getId()));
        endSession(session);

    }



    // ------------------------------------------------ bio directional communication api ------------------------------------------------

    public void send(MessagingProtocol<T> protocol, T msg){
        protocols.forEach(((session, messageMessagingProtocol) -> {
            if(messageMessagingProtocol == protocol){
                print(String.format("sending special message : %s", msg));
                send(session, msg);
            }
        }));
    }


    // ------------------------------------------------ private methods ------------------------------------------------


    private void send(Session session, T msg) {
        try {
            session.getBasicRemote().sendObject(msg);
        } catch (IOException | EncodeException e) {
            e.printStackTrace();
        }
    }

    private void print(String msg){
        System.out.println(msg);
        logger.Add_Log(msg);
    }

    private void endSession(Session session){
        MessagingProtocol<T> protocol = getProtocol(session);

        if(protocol != null) {
            protocol.end();
            protocols.remove(session);
        }
    }

    private MessagingProtocol<T> getProtocol(Session session){
        return protocols.get(session);
    }
}
