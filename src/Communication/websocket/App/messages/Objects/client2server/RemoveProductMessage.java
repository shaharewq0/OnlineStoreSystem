package Communication.websocket.App.messages.Objects.client2server;

import Communication.websocket.App.api_impl.MallProtocol;
import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.App.messages.api.Client2ServerMessage;
import Communication.websocket.App.messages.api.Message;

import java.util.Objects;

public class RemoveProductMessage extends Client2ServerMessage {

    private final String storeName;
    private final String productName;

    public RemoveProductMessage(long id, String storeName, String productName) {
        super(Opcodes.RemoveItem, id);
        this.storeName = storeName;
        this.productName = productName;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getProductName() {
        return productName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RemoveProductMessage that = (RemoveProductMessage) o;
        return getStoreName().equals(that.getStoreName()) &&
                getProductName().equals(that.getProductName());
    }

    @Override
    public String toString() {
        return "RemoveProductMessage{" +
                "storeName='" + storeName + '\'' +
                ", productName='" + productName + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStoreName(), getProductName());
    }

    @Override
    public Message visit(MallProtocol protocol) {
        return protocol.accept(this);
    }
}
