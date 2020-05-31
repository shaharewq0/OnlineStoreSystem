package Communication.websocket.App.messages.Objects.client2server;

import Communication.websocket.App.api_impl.MallProtocol;
import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.App.messages.api.Client2ServerMessage;
import Communication.websocket.App.messages.api.Message;

import java.util.Objects;

public class Add2ProductMessage extends Client2ServerMessage {


    private final String product;
    private final String store;
    private final int ammount;

    public Add2ProductMessage(long id, String Product, String store, int ammount) {
        super(Opcodes.Add2Product, id);
        product = Product;
        this.store = store;
        this.ammount = ammount;
    }

    public String getProduct() {
        return product;
    }

    public String getStore() {
        return store;
    }

    public int getAmmount() {
        return ammount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Add2ProductMessage that = (Add2ProductMessage) o;
        return getAmmount() == that.getAmmount() &&
                getProduct().equals(that.getProduct()) &&
                getStore().equals(that.getStore());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProduct(), getStore(), getAmmount());
    }

    @Override
    public String toString() {
        return "Add2ProductMessage{" +
                "product='" + product + '\'' +
                ", store='" + store + '\'' +
                ", ammount=" + ammount +
                '}';
    }

    @Override
    public Message visit(MallProtocol protocol) {
        return protocol.accept(this);
    }
}
