package Communication.socket.Echo;

import Communication.socket.api.MessageEncoderDecoder;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;

public class EchoClient implements Closeable {

    private final MessageEncoderDecoder<String> encdec;
    private final Socket sock;
    private final BufferedInputStream in;
    private final BufferedOutputStream out;

    public EchoClient(String host, int port) throws IOException {
        sock = new Socket(host, port);
        encdec = new LineMessageEncoderDecoder();
        in = new BufferedInputStream(sock.getInputStream());
        out = new BufferedOutputStream(sock.getOutputStream());
    }

    public void send(String msg) throws IOException {
        out.write(encdec.encode(msg));
        out.flush();
    }

    public String receive() throws IOException {
        int read;
        while ((read = in.read()) >= 0) {
            String msg = encdec.decodeNextByte((byte) read);
            if (msg != null) {
                return msg;
            }
        }

        throw new IOException("disconnected before complete reading message");
    }

    @Override
    public void close() throws IOException {
        out.close();
        in.close();
        sock.close();
    }

}
