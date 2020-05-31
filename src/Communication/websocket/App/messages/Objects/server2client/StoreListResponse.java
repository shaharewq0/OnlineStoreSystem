package Communication.websocket.App.messages.Objects.server2client;

import Communication.websocket.App.EncoderDecoder.MessageEncoder;
import Communication.websocket.App.messages.api.Server2ClientMessage;

import java.util.List;
import java.util.Objects;

public class StoreListResponse extends Server2ClientMessage {

    private final List<String> stores;

    public StoreListResponse(byte opcode, long replayForID, List<String> stores) {
        super(opcode, replayForID);
        this.stores = stores;
    }

    public List<String> getStores() {
        return stores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreListResponse that = (StoreListResponse) o;
        return getStores().equals(that.getStores());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStores());
    }

    @Override
    public String toString() {
        return "StoreListResponse{" +
                "stores=" + stores +
                '}';
    }

    @Override
    public String visit(MessageEncoder encoder) {
        return encoder.accept(this);
    }
}
