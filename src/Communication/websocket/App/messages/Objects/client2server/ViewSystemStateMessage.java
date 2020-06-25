package Communication.websocket.App.messages.Objects.client2server;

import Communication.websocket.App.api_impl.MallProtocol;
import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.App.messages.api.Client2ServerMessage;
import Communication.websocket.App.messages.api.Message;

public class ViewSystemStateMessage extends Client2ServerMessage {

    public ViewSystemStateMessage(long id) {
        super(Opcodes.viewsSytsemState, id);
    }

    @Override
    public Message visit(MallProtocol protocol) {
        return protocol.accept(this);
    }

    @Override
    public String toString() {
        return "ViewSystemStateMessage{}";
    }
}
