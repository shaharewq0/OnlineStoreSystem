package extornal.payment;

import extornal.EternalProxy;
import extornal.payment.Eternal.EternalPayment;

import java.util.HashMap;

public class MyPaymentSystem_Driver implements MyPaymentSystem {

    private PaymentMethed current_supplyer = null;

    static HashMap<String, PaymentMethed> list = null;

    static {
        list = new HashMap<String, PaymentMethed>();
        list.put(EternalPayment.name, new EternalPayment());
        list.put(Pay_pal.name, new Pay_pal());
        list.put(Isracard.name, new Isracard());

    }

    public MyPaymentSystem_Driver() {
        current_supplyer = list.get(EternalPayment.name);
    }

    public int pay(CreditCard card_num, int amount) {
        if (card_num == null || amount < 0)
            return -1;
        current_supplyer.pay(card_num, amount);
        return 1;
    }

    public void changePaymentMethed(String name) {
        if (list.containsKey(name)) {
            current_supplyer = list.get(name);
            // TODO add log
            System.out.println("ordering from " + name);
        } else {
            // TODO add log
            System.out.println("supplyer " + name + " dont exsist");
        }

    }

    @Override
    public PaymentMethed getPaymentMethed() {
        return current_supplyer;
    }
}
