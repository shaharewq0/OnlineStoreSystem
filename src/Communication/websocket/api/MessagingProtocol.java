package Communication.websocket.api;

public interface MessagingProtocol<T> {

    /**
     * process the given message
     *
     * @param msg the received message
     * @return the response to send or null if no response is expected by the client
     */
    T process(T msg);
}