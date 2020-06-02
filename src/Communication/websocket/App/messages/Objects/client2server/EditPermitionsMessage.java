package Communication.websocket.App.messages.Objects.client2server;

import Communication.websocket.App.api_impl.MallProtocol;
import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.App.messages.api.Client2ServerMessage;
import Communication.websocket.App.messages.api.Message;

import java.util.List;
import java.util.Objects;

public class EditPermitionsMessage extends Client2ServerMessage {
    private final String storeName;
    private final String managerName;
    private final List<String> permitions;

    public EditPermitionsMessage(long id, String storeName, String managerName, List<String> permitions) {
        super(Opcodes.editMangagerPermesions, id);
        this.storeName = storeName;
        this.managerName = managerName;
        this.permitions = permitions;
    }

    @Override
    public Message visit(MallProtocol protocol) {
        return protocol.accept(this);
    }

    public String getStoreName() {
        return storeName;
    }

    public String getManagerName() {
        return managerName;
    }

    public List<String> getPermitions() {
        return permitions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EditPermitionsMessage that = (EditPermitionsMessage) o;
        return getStoreName().equals(that.getStoreName()) &&
                getManagerName().equals(that.getManagerName()) &&
                getPermitions().equals(that.getPermitions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStoreName(), getManagerName(), getPermitions());
    }

    @Override
    public String toString() {
        return "EditPermitionsMessage{" +
                "storeName='" + storeName + '\'' +
                ", manaferNane='" + managerName + '\'' +
                ", permotions=" + permitions +
                '}';
    }
}
