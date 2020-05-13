package Communication.websocket.App.messages.Objects.client2server;

import Communication.websocket.App.api_impl.MallProtocol;
import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.App.messages.api.Client2ServerMessage;
import Communication.websocket.App.messages.api.Message;

import java.util.Objects;

public class AddProduct2BasketMessage extends Client2ServerMessage {

    private String store;
    private String product;
    private int amount;

    public AddProduct2BasketMessage(long id, String store, String product, int amount) {
        super(Opcodes.Save2Basket, id);
        this.store = store;
        this.product = product;
        this.amount = amount;
    }


    @Override
    public Message visit(MallProtocol protocol) {
        return protocol.accept(this);
    }


    public String getStore() {
        return store;
    }

    public String getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddProduct2BasketMessage that = (AddProduct2BasketMessage) o;
        return getAmount() == that.getAmount() &&
                getStore().equals(that.getStore()) &&
                getProduct().equals(that.getProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStore(), getProduct(), getAmount());
    }

    @Override
    public String toString() {
        return "AddProduct2BasketMessage{" +
                "store='" + store + '\'' +
                ", product='" + product + '\'' +
                ", amount=" + amount +
                '}';
    }
}
