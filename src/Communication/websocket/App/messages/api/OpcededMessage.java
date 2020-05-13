package Communication.websocket.App.messages.api;

/** a message thea have an opcode */
public abstract class OpcededMessage implements Message {

    /** message opcode */
    private byte opcode;


    public OpcededMessage(byte opcode) {
        this.opcode = opcode;
    }

    public byte getOpcode() {
        return opcode;
    }
}
