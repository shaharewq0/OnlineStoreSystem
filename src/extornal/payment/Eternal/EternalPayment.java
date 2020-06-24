package extornal.payment.Eternal;

import extornal.ExternalHandler;
import extornal.ExternalLog.ExternalLog;
import extornal.payment.CreditCard;
import extornal.payment.PaymentMethed;

public class EternalPayment implements PaymentMethed {

    static public String name = "EternalPayment";

    private ExternalHandler handler=ExternalHandler.getInstance();;


    public int pay(CreditCard card, double amount)
    {
        int output = pay(card.getCardNumber(), card.getExpirationDate(), card.getCardOwner(), card.getCSS(), card.getOwnerID());
        return  output;
    }


    private int pay(String card, String date, String owner, String cvv, String OwnerID) {
        String response = handler.pay(card, date, owner, cvv, OwnerID);

        if(response == null){
            return  -1;
        }

        int tranactionID = Integer.parseInt(response);

        if(tranactionID < 10000 || tranactionID > 100000){
            System.out.println("\n\n\nlook like an error in the external system!#1\n\n\n");
            ExternalLog.GetInstance().Add_Log("\n\n\nlook like an error in the external system!#1\n\n\n");
            return -1;
        }

        ExternalLog.GetInstance().Add_Log("transaction " + tranactionID + " was payed successful!");
        return tranactionID;
    }


    public boolean cancel_pay(int tranactionID) {
        boolean response =  "1".equals(handler.cancel_pay(tranactionID));

        if(!response){
            ExternalLog.GetInstance().Add_Log("MOST IMPORTANT!!!!!!!!!! tranaction #" + tranactionID + "attempted to be canceled, but failed!");
        }

        return  response;
    }





}
