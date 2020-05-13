package Communication.websocket.App.messages.Objects.client2server;

import Communication.websocket.App.api_impl.MallProtocol;
import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.App.messages.api.Client2ServerMessage;
import Communication.websocket.App.messages.api.Message;

import java.util.Objects;

public class ProductsByCategoryMessage extends Client2ServerMessage {

   private String category;

    public ProductsByCategoryMessage(long id, String category) {
        super(Opcodes.SearchProductBycategory, id);
        this.category = category;
    }

    @Override
    public Message visit(MallProtocol protocol) {
        return protocol.accept(this);
    }

    public String getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductsByCategoryMessage that = (ProductsByCategoryMessage) o;
        return getCategory().equals(that.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategory());
    }

    @Override
    public String toString() {
        return "ProductsByCategoryMessage{" +
                "category='" + category + '\'' +
                '}';
    }
}
