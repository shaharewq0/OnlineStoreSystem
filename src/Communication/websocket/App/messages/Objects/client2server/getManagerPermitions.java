package Communication.websocket.App.messages.Objects.client2server;

import Communication.websocket.App.api_impl.MallProtocol;
import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.App.messages.api.Client2ServerMessage;
import Communication.websocket.App.messages.api.Message;

import java.util.Objects;

public class getManagerPermitions extends Client2ServerMessage {

    private final String store;
    private final String manager;

    public getManagerPermitions(long id, String store, String manager) {
        super(Opcodes.getEditMangagerPermesions, id);
        this.store = store;
        this.manager = manager;
    }

    @Override
    public Message visit(MallProtocol protocol) {
        return protocol.accept(this);
    }

    public String getStore() {
        return store;
    }

    public String getManager() {
        return manager;
    }

    @Override
    public String toString() {
        return "getManagerPermitions{" +
                "store='" + store + '\'' +
                ", manager='" + manager + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        getManagerPermitions that = (getManagerPermitions) o;
        return Objects.equals(getStore(), that.getStore()) &&
                Objects.equals(getManager(), that.getManager());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStore(), getManager());
    }
}
