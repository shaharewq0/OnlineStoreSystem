package Communication.websocket.App.messages.Objects.server2client;

import Communication.websocket.App.EncoderDecoder.MessageEncoder;
import Communication.websocket.App.messages.api.Server2ClientMessage;
import Domain.Store.StorePurchase;

import java.util.List;
import java.util.Objects;

public class StorePurchaseListResponse extends Server2ClientMessage {

    private final List<StorePurchase> purchases;

    public StorePurchaseListResponse(byte opcode, long replayForID, List<StorePurchase> purchases) {
        super(opcode, replayForID);
        this.purchases = purchases;
    }

    @Override
    public String visit(MessageEncoder encoder) {
        return encoder.accept(this);
    }

    public List<StorePurchase> getPurchases() {
        return purchases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StorePurchaseListResponse that = (StorePurchaseListResponse) o;
        return getPurchases().equals(that.getPurchases());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPurchases());
    }

    @Override
    public String toString() {
        return "StorePurchaseListResponse{" +
                "purchases=" + purchases +
                '}';
    }
}
