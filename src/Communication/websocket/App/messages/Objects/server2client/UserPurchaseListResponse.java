package Communication.websocket.App.messages.Objects.server2client;

import Communication.websocket.App.EncoderDecoder.MessageEncoder;
import Communication.websocket.App.messages.api.Server2ClientMessage;
import Domain.UserClasses.UserPurchase;

import java.util.List;
import java.util.Objects;

public class UserPurchaseListResponse extends Server2ClientMessage {

    private List<UserPurchase> purchase;

    public UserPurchaseListResponse(byte opcode, long replayForID, List<UserPurchase> purchase) {
        super(opcode, replayForID);
        this.purchase = purchase;
    }

    @Override
    public String visit(MessageEncoder encoder) {
        return encoder.accept(this);
    }

    public List<UserPurchase> getPurchase() {
        return purchase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPurchaseListResponse that = (UserPurchaseListResponse) o;
        return getPurchase().equals(that.getPurchase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPurchase());
    }

    @Override
    public String toString() {
        return "UserPerchesListResponse{" +
                "purchase=" + purchase +
                '}';
    }
}
