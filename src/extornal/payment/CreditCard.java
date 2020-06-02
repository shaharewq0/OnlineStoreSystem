package extornal.payment;

import java.util.Objects;

public class CreditCard {

    private String cardNumber;
    private String expirationDate;
    private String CSS;
    private String cardOwner;

    public CreditCard(String cardNumber) {
        this.cardNumber = cardNumber;
        expirationDate = "null";
        CSS = "null";
        cardOwner = "null";
    }

    public CreditCard(String cardNumber, String expirationDate, String css, String cardOwner) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.CSS = css;
        this.cardOwner = cardOwner;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return getCardNumber().equals(that.getCardNumber()) &&
                getExpirationDate().equals(that.getExpirationDate()) &&
                getCSS().equals(that.getCSS()) &&
                getCardOwner().equals(that.getCardOwner());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCardNumber(), getExpirationDate(), getCSS(), getCardOwner());
    }

    @Override
    public String toString() {
        return "CredotCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", CSS='" + CSS + '\'' +
                ", cardOwner='" + cardOwner + '\'' +
                '}';
    }
}
