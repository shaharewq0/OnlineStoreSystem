package Communication.websocket.App.messages.Objects.server2client;

import Communication.websocket.App.EncoderDecoder.MessageEncoder;
import Communication.websocket.App.messages.api.Server2ClientMessage;

import java.util.Objects;

public class memberTypeResponse extends Server2ClientMessage {

    public memberTypeResponse(byte opcode, long replayForID) {
        super(opcode, replayForID);
    }

    @Override
    public String visit(MessageEncoder encoder) {
        return encoder.accept(this);
    }

    @Override
    public String toString() {
        return "memberTypeResponse{" +
                "op :" + getOpcode() +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }
}
