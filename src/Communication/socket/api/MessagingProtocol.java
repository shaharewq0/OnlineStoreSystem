package Communication.socket.api;

import Communication.socket.server.ConnectionHandler;

public interface MessagingProtocol<T> {
 
    /**
     * process the given message 
     * @param msg the received message
     * @return the response to send or null if no response is expected by the client
     */
    T process(T msg);

    /**
     * tell the protical who is the connection handler the resposable of it.
     * @param handler the Connection Handler
     */
    void setConnectionHandler(ConnectionHandler<T> handler);

    /**
     * @return true if the connection should be terminated
     */
    boolean shouldTerminate();
 
}