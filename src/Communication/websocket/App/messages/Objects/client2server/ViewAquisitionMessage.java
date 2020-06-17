package Communication.websocket.App.messages.Objects.client2server;

import Communication.websocket.App.api_impl.MallProtocol;
import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.App.messages.api.Client2ServerMessage;
import Communication.websocket.App.messages.api.Message;

import java.util.Objects;

public class ViewAquisitionMessage extends Client2ServerMessage {

    private final String storename;

    public ViewAquisitionMessage(long id, String storename) {
        super(Opcodes.viewAquisitionHistory, id);
        this.storename = storename;
    }

    @Override
    public Message visit(MallProtocol protocol) {
        return protocol.accept(this);
    }

    public String getStorename() {
        return storename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViewAquisitionMessage that = (ViewAquisitionMessage) o;
        return getStorename().equals(that.getStorename());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStorename());
    }

    @Override
    public String toString() {
        return "ViewAquisitionMessage{" +
                "storename='" + storename + '\'' +
                '}';
    }
}
