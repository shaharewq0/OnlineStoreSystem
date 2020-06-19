package Communication.websocket.App.messages.Objects.client2server;

import Communication.websocket.App.api_impl.MallProtocol;
import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.App.messages.api.Client2ServerMessage;
import Communication.websocket.App.messages.api.Message;

import java.util.Objects;

public class AcceptPendingAppointmentMessage extends Client2ServerMessage {

    private final String storeName;
    private final String appointe;

    public AcceptPendingAppointmentMessage(long id, String storeName, String appointe) {
        super(Opcodes.AcceptPendingAppintment, id);
        this.storeName = storeName;
        this.appointe = appointe;
    }

    @Override
    public Message visit(MallProtocol protocol) {
        return protocol.accept(this);
    }

    public String getStoreName() {
        return storeName;
    }

    public String getAppointe() {
        return appointe;
    }

    @Override
    public String toString() {
        return "AcceptPendingAppointmentMessage{" +
                "storeName='" + storeName + '\'' +
                ", appointe='" + appointe + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcceptPendingAppointmentMessage that = (AcceptPendingAppointmentMessage) o;
        return getStoreName().equals(that.getStoreName()) &&
                getAppointe().equals(that.getAppointe());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStoreName(), getAppointe());
    }
}
