package Communication.websocket.App.EncoderDecoder;


import Communication.websocket.App.messages.Macros.Delimiters;
import Communication.websocket.App.messages.Objects.server2client.*;
import Communication.websocket.App.messages.api.Message;
import Communication.websocket.App.messages.api.Server2ClientMessage;
import Communication.websocket.Logger.ServerLogger;
import Domain.UserClasses.UserPurchase;
import Domain.Store.StorePurchase;
import Domain.info.ProductDetails;
import Domain.info.Question;
import org.json.simple.JSONObject;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.util.LinkedList;
import java.util.List;

public class MessageEncoder implements  Encoder.Text<Message> {

    @Override
    public String encode(Message msg) throws EncodeException {

        String response = ((Server2ClientMessage)msg).visit(this);
        System.out.println("row response :" + response);
        ServerLogger.GetInstance().Add_Log("row response :" + response);

        return  response;
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
    public JSONObject list2jason(List<Byte> lst){

        JSONObject jsn = new JSONObject();

        int i = 0;

        for (Byte b: lst){
            jsn.put(String.valueOf(i), b);
            i++;
        }

        return  jsn;
    }

    public static String string2jason(String msg){

        JSONObject jsn = new JSONObject();

        int i = 0;

        for (Byte b: msg.getBytes()){
            jsn.put(String.valueOf(i), b);
            i++;
        }

        return  jsn.toString();
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


    private void offerInt( LinkedList<Byte> lst, int i){
        offerString(lst, String.valueOf(i));
    }

    private void offerDouble( LinkedList<Byte> lst, double d){
        offerString(lst, String.valueOf(d));
    }

    /**
     * offer a list to a list of bytes
     * @param lst the list
     * @param toOffer the list to offer
     */
    private void offerList( LinkedList<Byte> lst, List<String> toOffer){
        offerList(lst,toOffer, Delimiters.LIST_DELIMITER);
    }

    private void offerList( LinkedList<Byte> lst, List<String> toOffer, byte delemiter){

        boolean first = true;

        for (String cur: toOffer) {

            if(!first)
                offerByte(lst, delemiter);

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

            offerString(lst, cur.getName());
            offerByte(lst, Delimiters.LIST_DELIMITER_L2);
            offerDouble(lst, cur.getPrice());
            offerByte(lst, Delimiters.LIST_DELIMITER_L2);
            offerString(lst, cur.getStoreName());

            first = false;
        }
    }

    private void offerCartList( LinkedList<Byte> lst, List<ProductDetails> toOffer){

        boolean first = true;

        for (ProductDetails cur: toOffer) {

            if(!first)
                offerListDelimiter(lst);

            offerString(lst, cur.getName());
            offerByte(lst, Delimiters.LIST_DELIMITER_L2);
            offerInt(lst, cur.getAmount());
            offerByte(lst, Delimiters.LIST_DELIMITER_L2);
            offerDouble(lst, cur.getTotalPrice());
            offerByte(lst, Delimiters.LIST_DELIMITER_L2);
            offerString(lst, cur.getStoreName());

            first = false;
        }
    }

    /**
     * offer a list to a list of bytes
     * @param lst the list
     * @param toOffer the list to offer
     */
    private void offerUserPurchesList( LinkedList<Byte> lst, List<UserPurchase> toOffer){
        boolean first = true;

        for (UserPurchase cur: toOffer) {

            if(!first)
                offerDelimiter(lst);

            offerStorePurchesList(lst, cur.eachPurchase, Delimiters.LIST_DELIMITER, Delimiters.LIST_DELIMITER_L2, Delimiters.LIST_DELIMITER_L3, Delimiters.LIST_DELIMITER_L4);
            first = false;
        }

    }

    private void offerUserPurchesList_string( LinkedList<Byte> lst, List<UserPurchase> purches){
        offerString(lst, offerUserPurchesList_string(purches));
    }

    private String offerUserPurchesList_string(List<UserPurchase> purches) {
        StringBuilder sb = new StringBuilder();

        for (UserPurchase pur: purches) {
            sb.append(offerStorePurchesList_string(pur.eachPurchase)).append("\n");
            sb.append("Total Price : ").append(pur.TotalPrice).append("\n\n");
        }

        return sb.toString();
    }

    private void offerStorePurchesList( LinkedList<Byte> lst, List<StorePurchase> purches){
        offerStorePurchesList(lst, purches, Delimiters.LIST_DELIMITER, Delimiters.LIST_DELIMITER_L2, Delimiters.LIST_DELIMITER_L3, Delimiters.LIST_DELIMITER_L4);
    }

    private void offerStorePurchesList_string(LinkedList<Byte> lst, List<StorePurchase> purches){
        offerString(lst, offerStorePurchesList_string(purches));
    }

    private String offerStorePurchesList_string(List<StorePurchase> purches){
        StringBuilder sb = new StringBuilder();

        for (StorePurchase pur: purches) {
            sb.append("Store : ").append(pur.get_Store_Name()).append("\n");

            for (ProductDetails item:pur.getItems()) {
                sb.append("---Item Name : ").append(item.getName()).append("\n");
                sb.append("------Amount : ").append(item.getAmount()).append("\n");
                sb.append("------Price : ").append(item.getPrice()).append("\n");
            }

            sb.append("---store Total price : ").append(pur.getPrice()).append("\n");
        }

        return sb.toString();
    }

    private void offerStorePurchesList( LinkedList<Byte> lst, List<StorePurchase> toOffer, byte deleliter1, byte deleliter2, byte deleliter3, byte deleliter4){

        boolean first = true;

        for (StorePurchase cur: toOffer) {

            if(!first)
                offerByte(lst, deleliter1);

            OfferStorePurchase(lst, cur, deleliter2, deleliter3, deleliter4);
            first = false;
        }
    }

    private void OfferStorePurchase(LinkedList<Byte> lst, StorePurchase toOffer, byte deleliter2, byte deleliter3, byte deleliter4){
        offerString(lst, toOffer.get_Store_Name());
        offerByte(lst, deleliter2);
        offerProducts(lst, toOffer.getItemsCopy(), deleliter3, deleliter4);
        offerByte(lst, deleliter2);
        offerDouble(lst, toOffer.getPrice());
    }

    private void offerProductsNames( LinkedList<Byte> lst, List<ProductDetails> toOffer){

        boolean first = true;

        for (ProductDetails cur: toOffer) {

            if(!first)
                offerListDelimiter(lst);

            offerString(lst, cur.getName());

            first = false;
        }
    }

    private void offerProducts( LinkedList<Byte> lst, List<ProductDetails> toOffer, byte deleliter){
        offerProducts(lst, toOffer, deleliter, Delimiters.LIST_DELIMITER);
    }

    private void offerProducts( LinkedList<Byte> lst, List<ProductDetails> toOffer, byte deleliter3, byte deleliter4){

        boolean first = true;

        for (ProductDetails cur: toOffer) {

            if(!first)
                offerByte(lst, deleliter4);

            offerString(lst, cur.getName());
            offerByte(lst, deleliter3);
            offerInt(lst, cur.getAmount());
            offerByte(lst, deleliter3);
            offerDouble(lst, cur.getPrice());

            first = false;
        }
    }

    private void offerQustionList( LinkedList<Byte> lst, List<Question> toOffer){

        boolean first = true;

        for (Question cur: toOffer) {

            if(!first)
                offerListDelimiter(lst);

            offerList(lst, cur.getAnswers(), Delimiters.LIST_DELIMITER_L2);

            first = false;
        }
    }


    private String jasonfy(long id, Object o){
        JSONObject json;

        json = new JSONObject();
        json.put("cmd_id", id);
        json.put("result", o);

        return  json.toString();
    }


    private String createJsonString(long replayForID, List<Byte> lst) {
        return jasonfy(replayForID, list2jason(lst));
    }

    private String opcode_encode(Server2ClientMessage msg){
        return jasonfy(msg.getReplayForID(), msg.getOpcode());
    }








    public String accept(AckMessage msg){
        return  opcode_encode(msg);
    }

    public String accept(NackMessage msg){
        return  opcode_encode(msg);
    }

    public String accept(memberTypeResponse msg) {
        return  opcode_encode(msg);
    }



    public String accept(StringResponse msg) {
        LinkedList<Byte> lst = new LinkedList<>();

        offerString(lst, msg.getMsg());

        return createJsonString(msg.getReplayForID(), lst);
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

    public String accept(ProductDetailsListResponse msg) {
        LinkedList<Byte> lst = new LinkedList<>();

        offerProductList(lst, msg.getProducts());

        return createJsonString(msg.getReplayForID(), lst);
    }


    public String accept(UserPurchaseListResponse msg) {
        LinkedList<Byte> lst = new LinkedList<>();

        offerUserPurchesList_string(lst, msg.getPurchase());

        return createJsonString(msg.getReplayForID(), lst);
    }

    public String accept(QustionListResponse msg) {
        LinkedList<Byte> lst = new LinkedList<>();

        offerQustionList(lst, msg.getQuestions());

        return createJsonString(msg.getReplayForID(), lst);
    }

    public String accept(StorePurchaseListResponse msg) {
        LinkedList<Byte> lst = new LinkedList<>();

        offerStorePurchesList_string(lst, msg.getPurchases());

        return createJsonString(msg.getReplayForID(), lst);
    }

    public String accept(StoreListResponse msg) {
        LinkedList<Byte> lst = new LinkedList<>();

        offerList(lst, msg.getStores());

        return createJsonString(msg.getReplayForID(), lst);
    }

    public String accept(PrductsInCartResponse msg) {
        LinkedList<Byte> lst = new LinkedList<>();

        if(!msg.getProducts().isEmpty()) {
            offerDouble(lst, msg.getPrice());
            offerDelimiter(lst);
        }
        offerCartList(lst, msg.getProducts());

        return createJsonString(msg.getReplayForID(), lst);
    }

    public String accept(StringListResponse msg) {
        LinkedList<Byte> lst = new LinkedList<>();

        offerList(lst, msg.getLst());

        return createJsonString(msg.getReplayForID(), lst);
    }

    public String accept(ByteArrayResponse msg) {
        return createJsonString(msg.getReplayForID(), msg.getBytes());
    }
}

