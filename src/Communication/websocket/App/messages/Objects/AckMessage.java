package Communication.websocket.App.messages.Objects;

import Communication.websocket.App.EncoderDecoder.MessageEncoder;
import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.App.messages.api.Server2ClientMessage;

public class AckMessage extends Server2ClientMessage {

    public AckMessage() {
        super(Opcodes.Ack);
    }

    @Override
    public byte[] visit(MessageEncoder encoder) {
        return encoder.accept(this);
    }
}
