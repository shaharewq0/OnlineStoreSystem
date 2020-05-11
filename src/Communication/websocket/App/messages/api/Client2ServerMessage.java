package Communication.websocket.App.messages.api;

import Communication.websocket.App.MallProtocol;

/** a message from client to server */
public abstract class Client2ServerMessage extends OpcededMessage {

    public Client2ServerMessage(byte opcode) {
        super(opcode);
    }

    /**
     * visit the messaging protocol. Visitor design pattern.
     * @param protocol the messaging protocol
     * @return the response
     */
    public abstract Message visit(MallProtocol protocol);
}
