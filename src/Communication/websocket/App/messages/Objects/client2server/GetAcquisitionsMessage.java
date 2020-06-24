package Communication.websocket.App.messages.Objects.client2server;

import Communication.websocket.App.api_impl.MallProtocol;
import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.App.messages.api.Client2ServerMessage;
import Communication.websocket.App.messages.api.Message;

import java.util.Objects;

public class GetAcquisitionsMessage extends Client2ServerMessage {
    private final String storeName;

    public GetAcquisitionsMessage(long id, String storeName) {
        super(Opcodes.getAcquisitions, id);
        this.storeName = storeName;
    }

    @Override
    public Message visit(MallProtocol protocol) {
        return protocol.accept(this);
    }

    public String getStoreName() {
        return storeName;
    }

    @Override
    public String toString() {
        return "GetAcquisitionsMessage{" +
                "storeName='" + storeName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetAcquisitionsMessage that = (GetAcquisitionsMessage) o;
        return getStoreName().equals(that.getStoreName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStoreName());
    }
}
