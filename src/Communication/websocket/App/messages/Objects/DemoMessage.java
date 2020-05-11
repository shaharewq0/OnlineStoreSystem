package Communication.websocket.App.messages.Objects;

import Communication.websocket.App.EncoderDecoder.MessageEncoder;
import Communication.websocket.App.MallProtocol;
import Communication.websocket.App.messages.api.Client2ServerMessage;
import Communication.websocket.App.messages.api.Message;

import java.util.Objects;

public class DemoMessage extends Client2ServerMessage {

    /** content */
    private String content;


    public DemoMessage(byte opcode, String content) {
        super(opcode);
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "DemoMessage{" +
                "content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DemoMessage that = (DemoMessage) o;
        return getOpcode() == that.getOpcode() && getContent().equals(that.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getContent());
    }

    @Override
    public Message visit(MallProtocol protocol) {
        return protocol.accept(this);
    }
}
