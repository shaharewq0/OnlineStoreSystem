package Communication.socket.Echo;

import Communication.socket.server.Server;

public class EchoServer {

    public static void main(String[] args) {
        Server.reactor(Runtime.getRuntime().availableProcessors(), 8080, EchoProtocol::new, LineMessageEncoderDecoder::new).serve();
    }
}
