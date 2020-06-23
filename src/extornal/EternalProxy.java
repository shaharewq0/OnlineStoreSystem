package extornal;

import extornal.ExternalLog.ExternalLog;
import extornal.payment.CreditCard;
import extornal.payment.MyPaymentSystem;
import extornal.payment.PaymentMethed;

public class EternalProxy {

    private static EternalProxy instance;

    private ExternalHandler handler;

    private EternalProxy() {}

    public static EternalProxy getInstance(){
        if(instance == null){
            instance = new EternalProxy();
            instance.handler = ExternalHandler.getInstance();
        }

        return instance;
    }

//TODO only function that needs to stay here - needs to be static function
    public boolean handshake() {
        boolean response = "OK".equals(handler.handshake());

        if(!response){
            ExternalLog.GetInstance().Add_Log("handshake: no external systems!");
        }

        return  response;
    }



    //TODO was moved
//    public int pay(CreditCard card, double amount)
//    {
//        int output = pay(card.getCardNumber(), card.getExpirationDate(), card.getCardOwner(), card.getCSS(), card.getOwnerID());
//        return  output;
//    }

    //TODO was moved
//    public int pay(String card, String date, String owner, String cvv, String OwnerID) {
//        String response = handler.pay(card, date, owner, cvv, OwnerID);
//
//        if(response == null){
//            return  -1;
//        }
//
//        int tranactionID = Integer.parseInt(response);
//
//        if(tranactionID < 10000 || tranactionID > 100000){
//            System.out.println("\n\n\nlook like an error in the external system!#1\n\n\n");
//            ExternalLog.GetInstance().Add_Log("\n\n\nlook like an error in the external system!#1\n\n\n");
//            return -1;
//        }
//
//        ExternalLog.GetInstance().Add_Log("transaction " + tranactionID + " was payed successful!");
//        return tranactionID;
//    }

    //TODO was moved
//    public boolean cancel_pay(int tranactionID) {
//        boolean response =  "1".equals(handler.cancel_pay(tranactionID));
//
//        if(!response){
//            ExternalLog.GetInstance().Add_Log("MOST IMPORTANT!!!!!!!!!! tranaction #" + tranactionID + "attempted to be canceled, but failed!");
//        }
//
//        return  response;
//    }



//    public int supply(String country, String city, String adress, String zipcoode, String reciver) {
//        String response = handler.supply(country, city, adress, zipcoode, reciver);
//
//        int tranactionID = Integer.parseInt(response);
//
//        if(tranactionID < 10000 || tranactionID > 100000){
//            System.out.println("\n\n\nlook like an error in the external system!#2\n\n\n");
//            ExternalLog.GetInstance().Add_Log("\n\n\nlook like an error in the external system!#2\n\n\n");
//            return -1;
//        }
//
//        return tranactionID;
//    }

    //TODO was moved
    public boolean cancel_supply(int tranactionID) {
        return "1".equals(handler.cancel_supply(tranactionID));
    }


}
