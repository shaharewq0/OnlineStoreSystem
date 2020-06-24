package Communication.websocket.App.messages.Objects.client2server;

import Communication.websocket.App.api_impl.MallProtocol;
import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.App.messages.api.Client2ServerMessage;
import Communication.websocket.App.messages.api.Message;

import java.util.Objects;

public class RemoveAcquisitionMessage extends Client2ServerMessage {
    private final String storeName;
    private final int acquisitionID;

    public RemoveAcquisitionMessage(long id, String storeName, int acquisitionID) {
        super(Opcodes.removeAcquisition, id);
        this.storeName = storeName;
        this.acquisitionID = acquisitionID;
    }

    @Override
    public Message visit(MallProtocol protocol) {
        return protocol.accept(this);
    }

    public String getStoreName() {
        return storeName;
    }

    public int getAcquisitionID() {
        return acquisitionID;
    }

    @Override
    public String toString() {
        return "RemoveAcquisitionMessage{" +
                "storeName='" + storeName + '\'' +
                ", acquisitionID=" + acquisitionID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RemoveAcquisitionMessage that = (RemoveAcquisitionMessage) o;
        return getAcquisitionID() == that.getAcquisitionID() &&
                getStoreName().equals(that.getStoreName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStoreName(), getAcquisitionID());
    }
}
