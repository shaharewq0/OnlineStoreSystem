package Communication.websocket.App.messages.Objects.server2client;

import Communication.websocket.App.EncoderDecoder.MessageEncoder;
import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.App.messages.api.Server2ClientMessage;

public class NackMessage extends Server2ClientMessage {

    public NackMessage(long replayForID) {
        super(Opcodes.Nack, replayForID);
    }

    @Override
    public String visit(MessageEncoder encoder) {
        return encoder.accept(this);
    }

    @Override
    public String toString() {
        return "NackMessage{" +
                " replyFor ='" + getReplayForID() + '\'' +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return  !(o == null || getClass() != o.getClass());
    }
}
