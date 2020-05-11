package Communication.websocket.App.messages.api;

import Communication.websocket.App.EncoderDecoder.MessageEncoder;

public abstract class Server2ClientMessage extends OpcededMessage {

    public Server2ClientMessage(byte opcode) {
        super(opcode);
    }

    /**
     * visit the message encoder. Visitor design pattern.
     * @param encoder the message encoder
     * @return the encode
     */
    public abstract byte[] visit(MessageEncoder encoder);
}
