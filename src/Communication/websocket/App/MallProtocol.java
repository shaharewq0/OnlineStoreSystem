package Communication.websocket.App;

import Communication.websocket.App.messages.Objects.*;
import Communication.websocket.App.messages.api.Client2ServerMessage;
import Communication.websocket.api.MessagingProtocol;
import Communication.websocket.App.messages.api.Message;

public class MallProtocol implements MessagingProtocol<Message> {

    /** use for debugging. if true, will not procces any message, and return null */
    private final boolean debug = false;

    @Override
    public Message process(Message msg) {
        System.out.println("handling :" + msg.toString());
        return debug? null : ((Client2ServerMessage)msg).visit(this);
    }


    public Message accept(DemoMessage msg){
        return new NackMessage(msg.getId());
    }

    public Message accept(RegisterMessage msg){
        return new AckMessage(msg.getId());
    }

    public Message accept(LoginMessage msg){
        return new AckMessage(msg.getId());
    }

    public Message accept(LogoutMessage msg) {
        return new AckMessage(msg.getId());
    }
}
