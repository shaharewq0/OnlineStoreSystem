package Communication.websocket.App.messages.Objects.client2server;

import Communication.websocket.App.api_impl.MallProtocol;
import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.App.messages.api.Client2ServerMessage;
import Communication.websocket.App.messages.api.Message;
import Domain.info.ProductDetails;

import java.util.List;
import java.util.Objects;

//TODO
public class FilterMessage extends Client2ServerMessage {

    public enum FiltrtType{
        byPrice {
            @Override
            public byte getOpcode() {
                return Opcodes.FilterByPrice;
            }

            @Override
            public String toString(){
                return "byPrice";
            }
        },
        byRating {
            @Override
            public byte getOpcode() {
                return Opcodes.FilterByRating;
            }

            @Override
            public String toString(){
                return "byRating";
            }
        },
        byStoreRating {
            @Override
            public byte getOpcode() {
                return Opcodes.FilterByStoreRating;
            }

            @Override
            public String toString(){
                return "byStoreRating";
            }
        };

        public static FiltrtType get(int op){
            if(op == byPrice.getOpcode()){
                return  byPrice;
            }

            if(op == byRating.getOpcode()){
                return  byRating;
            }

            if(op == byStoreRating.getOpcode()){
                return  byStoreRating;
            }

            throw new IllegalArgumentException("unknown filter opcode");
        }

        public abstract byte getOpcode();
    }

    private final List<ProductDetails> products;
    private final double min;
    private final double max;
    private final FiltrtType type;

    public FilterMessage(long id, List<ProductDetails> products, double min, double max, FiltrtType type) {
        super(type.getOpcode(), id);
        this.products = products;
        this.min = min;
        this.max = max;
        this.type = type;
    }

    @Override
    public Message visit(MallProtocol protocol) {
        return protocol.accept(this);
    }

    public List<ProductDetails> getProducts() {
        return products;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public FiltrtType getType() {
        return type;
    }



    @Override
    public String toString() {
        return "FilterMessage{" +
                "products=" + products +
                ", min=" + min +
                ", max=" + max +
                ", type=" + type.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilterMessage that = (FilterMessage) o;
        return Double.compare(that.getMin(), getMin()) == 0 &&
                Double.compare(that.getMax(), getMax()) == 0 &&
                getProducts().equals(that.getProducts()) &&
                getType() == that.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProducts(), getMin(), getMax(), getType());
    }
}
