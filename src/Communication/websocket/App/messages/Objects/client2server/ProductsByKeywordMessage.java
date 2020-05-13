package Communication.websocket.App.messages.Objects.client2server;

import Communication.websocket.App.api_impl.MallProtocol;
import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.App.messages.api.Client2ServerMessage;
import Communication.websocket.App.messages.api.Message;

import java.util.Objects;

public class ProductsByKeywordMessage extends Client2ServerMessage {

    private String keyword;

    public ProductsByKeywordMessage(long id, String keyword) {
        super(Opcodes.SearchProductByKeyword, id);
        this.keyword = keyword;
    }

    @Override
    public Message visit(MallProtocol protocol) {
        return protocol.accept(this);
    }

    public String getKeyword() {
        return keyword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductsByKeywordMessage that = (ProductsByKeywordMessage) o;
        return getKeyword().equals(that.getKeyword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKeyword());
    }

    @Override
    public String toString() {
        return "ProductsByKeywordMessage{" +
                "keyword='" + keyword + '\'' +
                '}';
    }
}
