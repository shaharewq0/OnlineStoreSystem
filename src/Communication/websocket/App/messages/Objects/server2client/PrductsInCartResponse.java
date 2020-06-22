package Communication.websocket.App.messages.Objects.server2client;

import Communication.websocket.App.EncoderDecoder.MessageEncoder;
import Communication.websocket.App.messages.api.Server2ClientMessage;
import Domain.info.ProductDetails;

import java.util.List;
import java.util.Objects;

public class PrductsInCartResponse extends Server2ClientMessage {

    private List<ProductDetails> products;
    private final double price;


    public PrductsInCartResponse(long replayForID,List<ProductDetails> products, double price) {
        super((byte)-1, replayForID);

        this.products = products;
        this.price = price;
    }

    @Override
    public String visit(MessageEncoder encoder) {
        return encoder.accept(this);
    }


    public List<ProductDetails> getProducts() {
        return products;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "PrductsInCartResponse{" +
                "products=" + products +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrductsInCartResponse that = (PrductsInCartResponse) o;
        return Double.compare(that.getPrice(), getPrice()) == 0 &&
                Objects.equals(getProducts(), that.getProducts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProducts(), getPrice());
    }
}
