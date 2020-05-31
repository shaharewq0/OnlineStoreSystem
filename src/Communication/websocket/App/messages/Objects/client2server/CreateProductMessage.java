package Communication.websocket.App.messages.Objects.client2server;

import Communication.websocket.App.api_impl.MallProtocol;
import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.App.messages.api.Client2ServerMessage;
import Communication.websocket.App.messages.api.Message;

import java.util.List;
import java.util.Objects;

public class CreateProductMessage extends Client2ServerMessage {
    private final String name;
    private final double price;
    private final List<String> categoories;
    private final List<String> keywords;
    private final String storeName;
    private final int ammount;

    public CreateProductMessage(long id, String name, double price, List<String> categoories, List<String> keywords, String storeName, int ammount) {
        super(Opcodes.AddProduct2Store, id);
        this.name = name;
        this.price = price;
        this.categoories = categoories;
        this.keywords = keywords;
        this.storeName = storeName;
        this.ammount = ammount;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public List<String> getCategoories() {
        return categoories;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public String getStoreName() {
        return storeName;
    }

    public int getAmmount() {
        return ammount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateProductMessage that = (CreateProductMessage) o;
        return Double.compare(that.getPrice(), getPrice()) == 0 &&
                getAmmount() == that.getAmmount() &&
                getName().equals(that.getName()) &&
                getCategoories().equals(that.getCategoories()) &&
                getKeywords().equals(that.getKeywords()) &&
                getStoreName().equals(that.getStoreName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPrice(), getCategoories(), getKeywords(), getStoreName(), getAmmount());
    }

    @Override
    public String toString() {
        return "CreateProductMessage{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", categoories=" + categoories +
                ", keywords=" + keywords +
                ", storeName='" + storeName + '\'' +
                ", ammount=" + ammount +
                '}';
    }

    @Override
    public Message visit(MallProtocol protocol) {
        return protocol.accept(this);
    }
}
