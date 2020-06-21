package Communication.websocket.App.messages.Objects.client2server;

import Communication.websocket.App.api_impl.MallProtocol;
import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.App.messages.api.Client2ServerMessage;
import Communication.websocket.App.messages.api.Message;

import java.util.Objects;

public class createDiscount extends Client2ServerMessage {
    private final String store;
    private final String discountString;

    public createDiscount(long id, String store, String discountString) {
        super(Opcodes.createDiscount, id);
        this.store = store;
        this.discountString = discountString;
    }

    @Override
    public Message visit(MallProtocol protocol) {
        return protocol.accept(this);
    }

    public String getStore() {
        return store;
    }

    public String getDiscountString() {
        return discountString;
    }

    @Override
    public String toString() {
        return "createDiscount{" +
                "store='" + store + '\'' +
                ", discountString='" + discountString + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        createDiscount that = (createDiscount) o;
        return getStore().equals(that.getStore()) &&
                getDiscountString().equals(that.getDiscountString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStore(), getDiscountString());
    }
}
