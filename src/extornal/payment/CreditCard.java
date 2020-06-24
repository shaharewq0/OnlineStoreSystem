package extornal.payment;

import java.util.Objects;

public class CreditCard {

    private String cardNumber;
    private String expirationDate;
    private String CSS;
    private String cardOwner;
    private String ownerID;

    public CreditCard(String cardNumber) {
        this.cardNumber = cardNumber;
        expirationDate = "null";
        CSS = "null";
        cardOwner = "null";
    }

    public CreditCard(String cardNumber, String expirationDate, String css, String cardOwner, String ownerID) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.CSS = css;
        this.cardOwner = cardOwner;
        this.ownerID = ownerID;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getCSS() {
        return CSS;
    }

    public String getCardOwner() {
        return cardOwner;
    }

    public String getOwnerID() {
        return ownerID;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", CSS='" + CSS + '\'' +
                ", cardOwner='" + cardOwner + '\'' +
                ", ownerID='" + ownerID + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return Objects.equals(getCardNumber(), that.getCardNumber()) &&
                Objects.equals(getExpirationDate(), that.getExpirationDate()) &&
                Objects.equals(getCSS(), that.getCSS()) &&
                Objects.equals(getCardOwner(), that.getCardOwner()) &&
                Objects.equals(getOwnerID(), that.getOwnerID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCardNumber(), getExpirationDate(), getCSS(), getCardOwner(), getOwnerID());
    }
}
