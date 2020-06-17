package Communication.websocket.App.messages.Objects.client2server;

import Communication.websocket.App.api_impl.MallProtocol;
import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.App.messages.api.Client2ServerMessage;
import Communication.websocket.App.messages.api.Message;

import java.util.Objects;

public class FireMessage extends Client2ServerMessage {
    private final String username;
    private final String storeName;

    public FireMessage(long id, String username, String storeName) {
        super(Opcodes.FireManager, id);
        this.username = username;
        this.storeName = storeName;
    }

    public String getUsername() {
        return username;
    }

    public String getStoreName() {
        return storeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FireMessage that = (FireMessage) o;
        return getUsername().equals(that.getUsername()) &&
                getStoreName().equals(that.getStoreName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getStoreName());
    }


    @Override
    public String toString() {
        return "FireMessage{" +
                "username='" + username + '\'' +
                ", storeName='" + storeName + '\'' +
                '}';
    }

    @Override
    public Message visit(MallProtocol protocol) {
        return protocol.accept(this);
    }
}
