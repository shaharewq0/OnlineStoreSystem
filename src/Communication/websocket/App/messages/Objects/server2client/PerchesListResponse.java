package Communication.websocket.App.messages.Objects.server2client;

import Communication.websocket.App.EncoderDecoder.MessageEncoder;
import Communication.websocket.App.messages.api.Server2ClientMessage;
import Domain.Store.Purchase;

import java.util.List;
import java.util.Objects;

public class PerchesListResponse extends Server2ClientMessage {

    private List<Purchase> purchase;

    public PerchesListResponse(byte opcode, long replayForID, List<Purchase> purchase) {
        super(opcode, replayForID);
        this.purchase = purchase;
    }

    @Override
    public String visit(MessageEncoder encoder) {
        return encoder.accept(this);
    }

    public List<Purchase> getPurchase() {
        return purchase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PerchesListResponse that = (PerchesListResponse) o;
        return getPurchase().equals(that.getPurchase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPurchase());
    }

    @Override
    public String toString() {
        return "PerchesListResponse{" +
                "purchase=" + purchase +
                '}';
    }
}
