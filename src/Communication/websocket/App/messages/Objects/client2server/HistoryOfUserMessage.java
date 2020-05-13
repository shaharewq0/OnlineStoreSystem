package Communication.websocket.App.messages.Objects.client2server;

import Communication.websocket.App.api_impl.MallProtocol;
import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.App.messages.api.Client2ServerMessage;
import Communication.websocket.App.messages.api.Message;

import java.util.Objects;

public class HistoryOfUserMessage extends Client2ServerMessage {

    private String name;

    public HistoryOfUserMessage(long id, String name) {
        super(Opcodes.HistoryOfUser, id);
        this.name = name;
    }


    @Override
    public Message visit(MallProtocol protocol) {
        return protocol.accept(this);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryOfUserMessage that = (HistoryOfUserMessage) o;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "HistoryOfUserMessage{" +
                "name='" + name + '\'' +
                '}';
    }
}
