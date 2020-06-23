package Communication.websocket.App.messages.Objects.client2server;

import Communication.websocket.App.api_impl.MallProtocol;
import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.App.messages.api.Client2ServerMessage;
import Communication.websocket.App.messages.api.Message;
import Domain.Policies.Acquisitions.Acquisition;

import java.util.Objects;

public class AddAcquisitionMessage extends Client2ServerMessage {
    private final String storeName;
    private final Acquisition acquisition;

    public AddAcquisitionMessage(long id, String storeName, Acquisition acquisition) {
        super(Opcodes.addAcquisitions, id);
        this.storeName = storeName;
        this.acquisition = acquisition;
    }

    @Override
    public Message visit(MallProtocol protocol) {
        return protocol.accept(this);
    }

    public String getStoreName() {
        return storeName;
    }

    public Acquisition getAcquisition() {
        return acquisition;
    }

    @Override
    public String toString() {
        return "AddAcquisitionMessage{" +
                "storwName='" + getStoreName() + '\'' +
                ", acquisition=" + acquisition +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddAcquisitionMessage that = (AddAcquisitionMessage) o;
        return getStoreName().equals(that.getStoreName()) &&
                getAcquisition().equals(that.getAcquisition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStoreName(), getAcquisition());
    }
}
