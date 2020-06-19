package Communication.websocket.App.messages.Objects.client2server;

import Communication.websocket.App.api_impl.MallProtocol;
import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.App.messages.api.Client2ServerMessage;
import Communication.websocket.App.messages.api.Message;

import java.util.List;
import java.util.Objects;

public class GetPendingAppointments extends Client2ServerMessage {

    private final String storename;

    public GetPendingAppointments(long id, String storename) {
        super(Opcodes.PendingAppountments, id);
        this.storename = storename;
    }

    @Override
    public Message visit(MallProtocol protocol) {
        return protocol.accept(this);
    }


    public String getStorename() {
        return storename;
    }

    @Override
    public String toString() {
        return "getPendingAppointments{" +
                "appintees=" + storename +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetPendingAppointments that = (GetPendingAppointments) o;
        return getStorename().equals(that.getStorename());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStorename());
    }
}
