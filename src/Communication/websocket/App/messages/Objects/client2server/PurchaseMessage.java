package Communication.websocket.App.messages.Objects.client2server;

import Communication.websocket.App.api_impl.MallProtocol;
import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.App.messages.api.Client2ServerMessage;
import Communication.websocket.App.messages.api.Message;

import java.util.Objects;

public class PurchaseMessage extends Client2ServerMessage {

    private final String creditcardNumber;
    private final String eDate;
    private final String css;
    private final String owner;
    private final String shipAdress;
    private final String ownerID;
    private final String city;
    private final String country;
    private final String zip;

    public PurchaseMessage(long id, String creditcardNumber, String EDate, String CSS, String Owner, String ownerID, String ShipAdress, String city, String country, String zip) {
        super(Opcodes.Purches, id);
        this.creditcardNumber = creditcardNumber;
        eDate = EDate;
        css = CSS;
        owner = Owner;
        shipAdress = ShipAdress;
        this.ownerID = ownerID;

        this.city = city;
        this.country = country;
        this.zip = zip;
    }

    @Override
    public Message visit(MallProtocol protocol) {
        return protocol.accept(this);
    }

    public String getCreditcardNumber() {
        return creditcardNumber;
    }

    public String geteDate() {
        return eDate;
    }

    public String getCss() {
        return css;
    }

    public String getOwner() {
        return owner;
    }
    public String getShipReciver() {
        return getOwner();
    }

    public String getShipAdress() {
        return shipAdress;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getZip() {
        return zip;
    }

    @Override
    public String toString() {
        return "PurchaseMessage{" +
                "creditcardNumber='" + creditcardNumber + '\'' +
                ", eDate='" + eDate + '\'' +
                ", css='" + css + '\'' +
                ", owner='" + owner + '\'' +
                ", shipAdress='" + shipAdress + '\'' +
                ", ownerID='" + ownerID + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseMessage that = (PurchaseMessage) o;
        return getCreditcardNumber().equals(that.getCreditcardNumber()) &&
                geteDate().equals(that.geteDate()) &&
                getCss().equals(that.getCss()) &&
                getOwner().equals(that.getOwner()) &&
                getShipAdress().equals(that.getShipAdress()) &&
                getOwnerID().equals(that.getOwnerID()) &&
                getCity().equals(that.getCity()) &&
                getCountry().equals(that.getCountry()) &&
                getZip().equals(that.getZip());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCreditcardNumber(), geteDate(), getCss(), getOwner(), getShipAdress(), getOwnerID(), getCity(), getCountry(), getZip());
    }
}
