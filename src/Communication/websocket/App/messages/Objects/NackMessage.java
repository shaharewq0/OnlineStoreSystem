package Communication.websocket.App.messages.Objects;

import Communication.websocket.App.EncoderDecoder.MessageEncoder;
import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.App.messages.api.Server2ClientMessage;

public class NackMessage extends Server2ClientMessage {

    public NackMessage() {
        super(Opcodes.Nack);
    }

    @Override
    public byte[] visit(MessageEncoder encoder) {
        return encoder.accept(this);
    }
}
