package Communication.websocket.App.messages.Objects.server2client;

import Communication.websocket.App.EncoderDecoder.MessageEncoder;
import Communication.websocket.App.messages.api.Server2ClientMessage;

import java.util.Objects;

public class StorDetailsResponseMessage extends Server2ClientMessage {

    private String name;
    private String adress;
    private byte rating;

    public StorDetailsResponseMessage(long replayForID, String name, String adress, byte rating) {
        super((byte)-1, replayForID);
        this.name = name;
        this.adress = adress;
        this.rating = rating;
    }

    @Override
    public String visit(MessageEncoder encoder) {
        return encoder.accept(this);
    }

    public String getAdress() {
        return adress;
    }

    public String getName() {
        return name;
    }

    public byte getRating() {
        return rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StorDetailsResponseMessage that = (StorDetailsResponseMessage) o;
        return getRating() == that.getRating() &&
                getName().equals(that.getName()) &&
                getAdress().equals(that.getAdress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAdress(), getRating());
    }

    @Override
    public String toString() {
        return "StorDetailsResponseMessage{" +
                "name='" + name + '\'' +
                ", adress='" + adress + '\'' +
                ", rating=" + rating +
                '}';
    }
}
