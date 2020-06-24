package Communication.websocket.App.messages.Objects.server2client;

import Communication.websocket.App.EncoderDecoder.MessageEncoder;
import Communication.websocket.App.messages.api.Server2ClientMessage;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ByteArrayResponse extends Server2ClientMessage {
    private final List<Byte> bytes;

    public ByteArrayResponse(byte opcode, long replayForID, List<Byte> bytes) {
        super(opcode, replayForID);
        this.bytes = bytes;
    }

    @Override
    public String visit(MessageEncoder encoder) {
        return encoder.accept(this);
    }

    public List<Byte> getBytes() {
        return bytes;
    }

    @Override
    public String toString() {
        return "ByteArrayResponse{" +
                "bytes=" +bytes +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ByteArrayResponse that = (ByteArrayResponse) o;
        return Objects.equals(getBytes(), that.getBytes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBytes());
    }
}
