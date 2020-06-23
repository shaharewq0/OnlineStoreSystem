package extornal.supply.Eternal;

import Service_Layer.userAddress;
import extornal.ExternalHandler;
import extornal.ExternalLog.ExternalLog;
import extornal.supply.Packet_Of_Prodacts;
import extornal.supply.Supplyer;
import extornal.supply.inventory;

public class EternalSupply implements Supplyer {

    public static final String name = "EternalSupply";

    private ExternalHandler handler = ExternalHandler.getInstance();;
    @Override
    public int order(Packet_Of_Prodacts items, inventory destantion) {

        return -1;
    }

    @Override
    public int order(Packet_Of_Prodacts items, userAddress whereToSend) {
        // int suply = EternalProxy.getInstance().supply(whereToSend.getCountry(),
        // whereToSend.getCity(), whereToSend.getAddress(), whereToSend.getZipcode(), whereToSend.getReciver()); //boolean didSupplay = System.getInstance().navigateSupply().order(pack, whereToSend);

        return supply(whereToSend.getCountry(), whereToSend.getCity(), whereToSend.getAddress(),
                whereToSend.getZipcode(), whereToSend.getReciver());
    }

    public int supply(String country, String city, String adress, String zipcoode, String reciver) {
        String response = handler.supply(country, city, adress, zipcoode, reciver);

        int tranactionID = Integer.parseInt(response);

        if(tranactionID < 10000 || tranactionID > 100000){
            System.out.println("\n\n\nlook like an error in the external system!#2\n\n\n");
            ExternalLog.GetInstance().Add_Log("\n\n\nlook like an error in the external system!#2\n\n\n");
            return -1;
        }

        return tranactionID;
    }

}
