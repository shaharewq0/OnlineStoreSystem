package Communication.websocket.App.messages.Objects.client2server;

import Communication.websocket.App.api_impl.MallProtocol;
import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.App.messages.api.Client2ServerMessage;
import Communication.websocket.App.messages.api.Message;

import java.util.Objects;

public class AppointMessage extends Client2ServerMessage {
    private final String username;
    private final String storename;
    private final byte role;

    public AppointMessage(long id, String username, String storename, byte role) {
        super(Opcodes.Appoint, id);
        this.username = username;
        this.storename = storename;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getStorename() {
        return storename;
    }

    public byte getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppointMessage that = (AppointMessage) o;
        return getRole() == that.getRole() &&
                getUsername().equals(that.getUsername()) &&
                getStorename().equals(that.getStorename());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getStorename(), getRole());
    }

    @Override
    public String toString() {
        return "AppointMessage{" +
                "username='" + username + '\'' +
                ", storename='" + storename + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public Message visit(MallProtocol protocol) {
        return protocol.accept(this);
    }
}
