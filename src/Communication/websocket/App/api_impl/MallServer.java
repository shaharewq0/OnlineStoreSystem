package Communication.websocket.App.api_impl;

import Communication.websocket.App.EncoderDecoder.MessageDecoder;
import Communication.websocket.App.EncoderDecoder.MessageEncoder;
import Communication.websocket.App.messages.api.Message;
import Communication.websocket.api.BaseServer;
import Communication.websocket.api.MessagingProtocol;


import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.Closeable;
import java.io.IOException;


@ServerEndpoint(
        value = "/mall",
        encoders = { MessageEncoder.class },
        decoders = { MessageDecoder.class }
)
public class MallServer implements Closeable {

    private BaseServer<Message> server;


    public MallServer() {
        server = new BaseServer<>(MallProtocol::new);
    }

    @OnOpen
    public void onOpen(Session session){
        server.onOpen(session);
    }


    @OnMessage
    public void onMessage(Session session, Message msg){
        server.onMessage(session, msg);
    }

    @OnError
    public void onError(Session session, Throwable e){
        server.onError(session, e);
    }

    @OnClose
    public void onClose(Session session){
        server.onClose(session);
    }



    @Override
    public void close() throws IOException {
        server.close();
        server = null;
    }
}
