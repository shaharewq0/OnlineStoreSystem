package Communication.websocket.App.messages.Objects.client2server;

import Communication.websocket.App.api_impl.MallProtocol;
import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.App.messages.api.Client2ServerMessage;
import Communication.websocket.App.messages.api.Message;

import java.util.Objects;

public class RemoveDiscountMessage  extends Client2ServerMessage {
    private final String storeName;
    private final int DiscountID;

    public RemoveDiscountMessage(long id, String storeName, int DiscountID) {
        super(Opcodes.deleteDiscount, id);
        this.storeName = storeName;
        this.DiscountID = DiscountID;
    }

    @Override
    public Message visit(MallProtocol protocol) {
        return protocol.accept(this);
    }

    public String getStoreName() {
        return storeName;
    }

    public int getDiscountID() {
        return DiscountID;
    }

    @Override
    public String toString() {
        return "RemoveDiscountMessage{" +
                "storeName='" + storeName + '\'' +
                ", dicountID=" + DiscountID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RemoveDiscountMessage that = (RemoveDiscountMessage) o;
        return getDiscountID() == that.getDiscountID() &&
                getStoreName().equals(that.getStoreName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStoreName(), getDiscountID());
    }
}
