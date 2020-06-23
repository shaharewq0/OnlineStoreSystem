package Communication.websocket.App.messages.Objects.client2server;

import Communication.websocket.App.api_impl.MallProtocol;
import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.App.messages.api.Client2ServerMessage;
import Communication.websocket.App.messages.api.Message;
import Domain.Policies.Discounts.Discount;

import java.util.Objects;

public class createDiscount extends Client2ServerMessage {
    private final String store;
    private final Discount discount;

    public createDiscount(long id, String store, Discount discount) {
        super(Opcodes.createDiscount, id);
        this.store = store;
        this.discount= discount;
    }

    @Override
    public Message visit(MallProtocol protocol) {
        return protocol.accept(this);
    }

    public String getStore() {
        return store;
    }

    public Discount getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        return "createDiscount{" +
                "store='" + store + '\'' +
                ", discountString='" + discount + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        createDiscount that = (createDiscount) o;
        return getStore().equals(that.getStore()) &&
                getDiscount().equals(that.getDiscount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStore(), getDiscount());
    }
}
