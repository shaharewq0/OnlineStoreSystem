package Communication.websocket.App.messages.Objects.server2client;

import Communication.websocket.App.EncoderDecoder.MessageEncoder;
import Communication.websocket.App.messages.api.Server2ClientMessage;

public class memberTypeResponse extends Server2ClientMessage {

    public memberTypeResponse(byte opcode, long replayForID) {
        super(opcode, replayForID);
    }

    @Override
    public String visit(MessageEncoder encoder) {
        return encoder.accept(this);
    }
}
