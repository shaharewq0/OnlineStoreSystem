/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication.socket.server;

import java.io.Closeable;

/**
 * The ConnectionHandler interface for Message of type T
 */
public interface ConnectionHandler<T> extends Closeable {

    /**
     * there is a message to send to the client (Bio-directional communication)
     * receive a message and deliver it to the client
     * @param msg the message to send to the client
     */
    void sendClient(T msg);
}
