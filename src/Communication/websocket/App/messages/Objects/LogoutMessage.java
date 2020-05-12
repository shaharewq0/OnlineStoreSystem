package Communication.websocket.App.messages.Objects;

import Communication.websocket.App.MallProtocol;
import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.App.messages.api.Client2ServerMessage;
import Communication.websocket.App.messages.api.Message;

public class LogoutMessage extends Client2ServerMessage {

    public LogoutMessage(long id) {
        super(Opcodes.Logout, id);
    }

    @Override
    public Message visit(MallProtocol protocol) {
        return protocol.accept(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return  !(o == null || getClass() != o.getClass());
    }

    @Override
    public String toString() {
        return "LogoutMessage{" +
                ", id ='" + getId() + '\'' +
                "}";

    }
}
