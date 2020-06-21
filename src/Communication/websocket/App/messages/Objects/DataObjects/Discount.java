package Communication.websocket.App.messages.Objects.DataObjects;

import java.util.Objects;

public class Discount {

    private byte type;
    private String productName;
    private String storeName;
    private double percent;
    private  int min;
    private  String date;


    public Discount(byte type, String productName, String storeName, double percent, int min, String date) {
        this.type = type;
        this.productName = productName;
        this.storeName = storeName;
        this.percent = percent;
        this.min = min;
        this.date = date;
    }

    public byte getType() {
        return type;
    }

    public String getProductName() {
        return productName;
    }

    public String getStoreName() {
        return storeName;
    }

    public double getPercent() {
        return percent;
    }

    public int getMin() {
        return min;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "type=" + type +
                ", productName='" + productName + '\'' +
                ", storeName='" + storeName + '\'' +
                ", percent=" + percent +
                ", min=" + min +
                ", date='" + date + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discount discount = (Discount) o;
        return getType() == discount.getType() &&
                Double.compare(discount.getPercent(), getPercent()) == 0 &&
                getMin() == discount.getMin() &&
                getProductName().equals(discount.getProductName()) &&
                getStoreName().equals(discount.getStoreName()) &&
                getDate().equals(discount.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getProductName(), getStoreName(), getPercent(), getMin(), getDate());
    }
}
