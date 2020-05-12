package Communication.websocket.App.messages.api;

import Communication.websocket.App.MallProtocol;

/** a message from client to server */
public abstract class Client2ServerMessage extends OpcededMessage {

    /** message id */
    private long id;

    public Client2ServerMessage(byte opcode, long id) {
        super(opcode);
        this.id = id;
    }

    /**
     * visit the messaging protocol. Visitor design pattern.
     * @param protocol the messaging protocol
     * @return the response
     */
    public abstract Message visit(MallProtocol protocol);

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
