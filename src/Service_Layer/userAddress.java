package Service_Layer;

import extornal.supply.Packet_Of_Prodacts;
import extornal.supply.inventory;

import java.util.LinkedList;
import java.util.List;

public class userAddress implements inventory {
    private final String country;
    private final String city;
    private String address;
    private final String zipcode;

    List<Packet_Of_Prodacts> packeges = new LinkedList<Packet_Of_Prodacts>();
    public userAddress(String country, String city, String address, String zipcode){
        this.country = country;
        this.city = city;
        this.address = address;
        this.zipcode = zipcode;
    }


    @Override
    public boolean recive_item(Packet_Of_Prodacts pack) {
        if (pack == null || pack.items == null || pack.items.isEmpty())
            return false;
        packeges.add(pack);

        return true;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getZipcode() {
        return zipcode;
    }

    @Override
    public String toString() {
        return "userAddress{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }
}
