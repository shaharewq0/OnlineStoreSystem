package Communication.websocket.App.messages.Objects.client2server;

import Communication.websocket.App.api_impl.MallProtocol;
import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.App.messages.api.Client2ServerMessage;
import Communication.websocket.App.messages.api.Message;

public class EndViewSystemStateMessage extends Client2ServerMessage {
    public EndViewSystemStateMessage(long id) {
        super(Opcodes.endViewsSytsemState, id);
    }

    @Override
    public Message visit(MallProtocol protocol) {
        return protocol.accept(this);
    }

    @Override
    public String toString() {
        return "EndViewSystemStateMessage{}";
    }
}
