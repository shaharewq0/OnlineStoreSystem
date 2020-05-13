package Communication.websocket.App.messages.api;

import Communication.websocket.App.EncoderDecoder.MessageEncoder;

public abstract class Server2ClientMessage extends OpcededMessage {

    /** what is the id of the message this reply is sent for? */
    private long replayForID;


    public long getReplayForID() {
        return replayForID;
    }

    public Server2ClientMessage(byte opcode, long replayForID) {
        super(opcode);
        this.replayForID = replayForID;
    }


    /**
     * visit the message encoder. Visitor design pattern.
     * @param encoder the message encoder
     * @return the encode
     */
    public abstract String visit(MessageEncoder encoder);
}
