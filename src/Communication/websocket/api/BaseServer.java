package Communication.websocket.api;


import Communication.websocket.Logger.ServerErrorLog;
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


    /** logger */
    private Log logger;

    /** singleton */
    private static BaseServer<?> instance = null;

    @SuppressWarnings("all")
    public static<E> BaseServer<E> createServer(Function<BaseServer<E>, MessagingProtocol<E>> protocolSupplier) {

        if(instance == null) {
            instance = new BaseServer<>(protocolSupplier);
        }

        return (BaseServer<E>)instance;
    }


    private BaseServer(Function<BaseServer<T>, MessagingProtocol<T>> protocolSupplier) {
        protocols = new ConcurrentHashMap<>();
        this.protocolSupplier = protocolSupplier;
        this.logger = ServerLogger.GetInstance();

       print(timeString() + " server is now online!");
    }


    @Override
    public void close() {
        logger.CloseLogger();
        protocols = null;
        protocolSupplier = null;
    }

    // ------------------------------------------------ server api ------------------------------------------------

    public void onOpen(Session session){
        print(String.format( timeString() + "Opened session. id: %s", session.getId()));

        protocols.put(session, protocolSupplier.apply(this));
    }


    public void onMessage(Session session, T msg){
        print(String.format( timeString() + "message received. session id: %s.  Message : %s", session.getId(), msg.toString()));

        MessagingProtocol<T> protocol = Objects.requireNonNull(protocols.get(session));
        T response = protocol.process(msg);

        // sand back if there is a response
        if(response != null){
            send(session, response);
            print(String.format( timeString() + "sending message. session id: %s.  Message : %s\n", session.getId(), response.toString()));
        }
    }

    public void onError(Session session, Throwable e){
        String err = String.format( timeString() + "ERROR in session (id: %s) : %s", session.getId(), e.getMessage());
        print(err);
        ServerErrorLog.GetInstance().Add_Log(err);
        endSession(session);
    }

    public void onClose(Session session){
        print(String.format( timeString() + "closeted session. id: %s", session.getId()));
        endSession(session);

    }



    // ------------------------------------------------ bio directional communication api ------------------------------------------------

    public void send(MessagingProtocol<T> protocol, T msg){
        protocols.forEach(((session, messageMessagingProtocol) -> {
            if(messageMessagingProtocol == protocol){
                print(String.format(timeString() + "sending special message : %s session : %s", msg, session.getId()));
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

    private String timeString(){
        return  "[" + LocalDateTime.now() + "]: ";
    }
}
