package Communication.websocket.App.messages.Objects.server2client;

import Communication.websocket.App.EncoderDecoder.MessageEncoder;
import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.App.messages.api.Server2ClientMessage;

import java.util.List;
import java.util.Objects;

public class StoreProductsResponseMessage extends Server2ClientMessage {

    private List<String> products;

    public StoreProductsResponseMessage(long replayForID, List<String> products) {
        super(Opcodes.StoreProducts, replayForID);
        this.products = products;
    }


    @Override
    public String visit(MessageEncoder encoder) {
        return encoder.accept(this);
    }

    public List<String> getProducts() {
        return products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreProductsResponseMessage that = (StoreProductsResponseMessage) o;
        return getProducts().equals(that.getProducts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProducts());
    }

    @Override
    public String toString() {
        return "StoreProductsResponseMessage{" +
                "products=" + products +
                '}';
    }
}
