package Communication.websocket.App.messages.Objects.server2client;

import Communication.websocket.App.EncoderDecoder.MessageEncoder;
import Communication.websocket.App.messages.api.Server2ClientMessage;

import java.util.List;
import java.util.Objects;

public class StringListResponse extends Server2ClientMessage {

    private final List<String> lst;

    public StringListResponse(long replayForID, List<String> lst) {
        super((byte)-1, replayForID);
        this.lst = lst;
    }

    @Override
    public String visit(MessageEncoder encoder) {
        return encoder.accept(this);
    }

    public List<String> getLst() {
        return lst;
    }

    @Override
    public String toString() {
        return "StringListResponse{" +
                "lst=" + lst +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringListResponse that = (StringListResponse) o;
        return getLst().equals(that.getLst());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLst());
    }
}
