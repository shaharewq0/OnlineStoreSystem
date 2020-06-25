package Communication.websocket.App.messages.Macros;

import java.util.HashMap;
import java.util.Map;

public class Permitions {

    public final Map<Byte, String> permesions;

    public Permitions(){
        permesions = new HashMap<>();

        permesions.put((byte)0x38, "getDiscountsString");
        permesions.put((byte)0x39, "removeDiscount");
        permesions.put((byte)0x3a, "addDiscount");

        permesions.put((byte)0x3b, "getacquisition");
        permesions.put((byte)0x3c, "removeacquisition");
        permesions.put((byte)0x3d, "addacquisition");

        permesions.put((byte)0x33, "getPurchaseHistory");

        permesions.put((byte)0x34, "addItem");
        permesions.put((byte)0x35, "removeItem");
        permesions.put((byte)0x36, "editItem");

        permesions.put((byte)0x4d, "fire");

        permesions.put((byte)0x4c, "appointManager");





       /* permesions.put(1, "editManagerPermesions");
        permesions.put(1, "appointOwner");
        permesions.put(1, "getfire");

        permesions.put(1, "viewQuestions");
        permesions.put(1, "giveRespond");*/

    }
}
