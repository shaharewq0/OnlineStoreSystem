package Communication.websocket.App.messages.Objects;

import Communication.websocket.App.EncoderDecoder.MessageEncoder;
import Communication.websocket.App.MallProtocol;
import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.App.messages.api.Client2ServerMessage;
import Communication.websocket.App.messages.api.Message;

import java.util.Objects;

public class RegisterMessage extends Client2ServerMessage {

    /** The user name */
    private String username;

    /** The password */
    private String password;


    public RegisterMessage(String username, String password) {
        super(Opcodes.Register);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterMessage that = (RegisterMessage) o;

        return  getUsername().equals(that.getUsername()) &&
                getPassword().equals(that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword());
    }

    @Override
    public String toString() {
        return "RegisterMessage{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


    @Override
    public Message visit(MallProtocol protocol) {
        return protocol.accept(this);
    }
}
