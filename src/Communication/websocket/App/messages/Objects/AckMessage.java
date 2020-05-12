package Communication.websocket.App.messages.Objects;

import Communication.websocket.App.EncoderDecoder.MessageEncoder;
import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.App.messages.api.Server2ClientMessage;

public class AckMessage extends Server2ClientMessage {

    public AckMessage(long replayForID) {
        super(Opcodes.Ack, replayForID);
    }

    @Override
    public String visit(MessageEncoder encoder) {
        return encoder.accept(this);
    }

    @Override
    public String toString() {
        return "AckMessage{" +
                " replyFor ='" + getReplayForID() + '\'' +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return  !(o == null || getClass() != o.getClass());
    }
}
