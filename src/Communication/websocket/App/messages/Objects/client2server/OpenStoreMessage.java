package Communication.websocket.App.messages.Objects.client2server;

import Communication.websocket.App.api_impl.MallProtocol;
import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.App.messages.api.Client2ServerMessage;
import Communication.websocket.App.messages.api.Message;

import java.util.Objects;

public class OpenStoreMessage extends Client2ServerMessage {

   private String name;
   private String address;

    public OpenStoreMessage(long id, String name, String addres) {
        super(Opcodes.OpenStore, id);
        this.name = name;
        this.address = addres;
    }

    @Override
    public Message visit(MallProtocol protocol) {
        return protocol.accept(this);
    }

    public String getName() {
        return name;
    }

    public String getAddres() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpenStoreMessage that = (OpenStoreMessage) o;
        return getName().equals(that.getName()) &&
                getAddres().equals(that.getAddres());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAddres());
    }

    @Override
    public String toString() {
        return "OpenStoreMessage{" +
                "name='" + name + '\'' +
                ", addres='" + address + '\'' +
                '}';
    }
}
