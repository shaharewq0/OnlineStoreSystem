package Service_Layer;

import extornal.supply.Packet_Of_Prodacts;
import extornal.supply.inventory;

import java.util.LinkedList;
import java.util.List;

public class userAddress implements inventory {
    public String address;

    List<Packet_Of_Prodacts> packeges = new LinkedList<Packet_Of_Prodacts>();
    public userAddress(String address){
        this.address = address;
    }


    @Override
    public boolean recive_item(Packet_Of_Prodacts pack) {
        if (pack == null || pack.items == null || pack.items.isEmpty())
            return false;
        packeges.add(pack);

        return true;

    }
}
