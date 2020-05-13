package Communication.websocket.App.messages.Objects.client2server;

import Communication.websocket.App.api_impl.MallProtocol;
import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.App.messages.api.Client2ServerMessage;
import Communication.websocket.App.messages.api.Message;

import java.util.Objects;

public class ViewPerchesHistory extends Client2ServerMessage {

    public ViewPerchesHistory( long id) {
        super(Opcodes.PurchasesHistory, id);
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
        return "ViewPerchesHistory{}";
    }
}
