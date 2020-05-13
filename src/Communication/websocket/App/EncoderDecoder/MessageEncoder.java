package Communication.websocket.App.EncoderDecoder;


import Communication.websocket.App.messages.Macros.Delimiters;
import Communication.websocket.App.messages.Objects.server2client.*;
import Communication.websocket.App.messages.api.Message;
import Communication.websocket.App.messages.api.Server2ClientMessage;
import Domain.info.ProductDetails;
import org.json.simple.JSONObject;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class MessageEncoder implements  Encoder.Text<Message> {

    private JSONObject json;


    @Override
    public String encode(Message msg) throws EncodeException {

        return ((Server2ClientMessage)msg).visit(this) ;
    }


    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }



    /**
     * convert a klist to array
     * @return the list to convert
     */
    private byte[] list2array(List<Byte> lst){

        int size = lst.size();
        int i = 0;

        byte[] arr = new byte[size];

       for (Byte b: lst){
           arr[i] = b;
           i++;
       }

        return  arr;
    }

    /**
     * convert a list to jason array
     * @return the list to convert
     */
    private JSONObject list2jason(List<Byte> lst){

        JSONObject jsn = new JSONObject();

        int size = lst.size();
        int i = 0;

        for (Byte b: lst){
            jsn.put(String.valueOf(i), b);
            i++;
        }

        return  jsn;
    }

    /**
     * offer the delimiter to a list of bytes
     * @param lst the list
     */
    private void offerDelimiter(LinkedList<Byte> lst){
        lst.offer(Delimiters.PARAMETER_DELIMITER);
    }

    /**
     * offer the list delimiter to a list of bytes
     * @param lst the list
     */
    private void offerListDelimiter(LinkedList<Byte> lst){
        lst.offer(Delimiters.LIST_DELIMITER);
    }


    /**
     * offer a string to a list of bytes
     * @param lst the list
     * @param s the string to offer
     */
    private void offerString( LinkedList<Byte> lst, String s){
        byte[] bytes = s.getBytes();

        for (byte aByte : bytes) {
            lst.offer(aByte);
        }
    }

    /**
     * offer a byte to a list of bytes
     * @param lst the list
     * @param b the byte to offer
     */
    private void offerByte( LinkedList<Byte> lst, byte b){
        lst.offer(b);
    }

    /**
     * offer a list to a list of bytes
     * @param lst the list
     * @param toOffer the list to offer
     */
    private void offerList( LinkedList<Byte> lst, List<String> toOffer){

        boolean first = true;

        for (String cur: toOffer) {

            if(!first)
                offerListDelimiter(lst);

            offerString(lst, cur);
            first = false;
        }
    }

    /**
     * offer a list to a list of bytes
     * @param lst the list
     * @param toOffer the list to offer
     */
    private void offerProductList( LinkedList<Byte> lst, List<ProductDetails> toOffer){

        boolean first = true;

        for (ProductDetails cur: toOffer) {

            if(!first)
                offerListDelimiter(lst);

            offerString(lst, cur.getStoreName());
            offerByte(lst, Delimiters.LIST_DELIMITER_L2);
            offerByte(lst, (byte)cur.getAmount());

            first = false;
        }
    }




    private String createJsonString(long replayForID, List<Byte> lst)
    {
        json = new JSONObject();
        json.put("cmd_id", replayForID);
        json.put("result", list2jason(lst));

        return  json.toString();
    }








    public String accept(AckMessage msg){
        json = new JSONObject();
        json.put("cmd_id", msg.getReplayForID());
        json.put("result", msg.getOpcode());

        return  json.toString();
    }

    public String accept(NackMessage msg){
        json = new JSONObject();
        json.put("cmd_id", msg.getReplayForID());
        json.put("result", msg.getOpcode());

        return  json.toString();

    }


    public String accept(StorDetailsResponseMessage msg) {
        LinkedList<Byte> lst = new LinkedList<>();

        offerString(lst, msg.getName());
        offerDelimiter(lst);

        offerString(lst, msg.getAdress());
        offerDelimiter(lst);

        offerByte(lst, msg.getRating());

        return createJsonString(msg.getReplayForID(), lst);
    }

    public String accept(StoreProductsResponseMessage msg) {
        LinkedList<Byte> lst = new LinkedList<>();

        offerList(lst, msg.getProducts());

        return createJsonString(msg.getReplayForID(), lst);
    }

    public String accept(ProductDetailsListResponse msg) {
        LinkedList<Byte> lst = new LinkedList<>();

        offerProductList(lst, msg.getProducts());

        return createJsonString(msg.getReplayForID(), lst);
    }

    public String accept(PerchesListResponse msg) {
        LinkedList<Byte> lst = new LinkedList<>();

        //offerProductList(lst, msg.getPurchase().);
        // todo : impliment

        return createJsonString(msg.getReplayForID(), lst);
    }
}

