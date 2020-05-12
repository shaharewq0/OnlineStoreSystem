package Communication.websocket.App.EncoderDecoder;


import Communication.websocket.App.messages.Macros.Delimiters;
import Communication.websocket.App.messages.Objects.*;
import Communication.websocket.App.messages.api.Message;
import Communication.websocket.App.messages.api.Server2ClientMessage;
import org.json.simple.JSONObject;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.util.LinkedList;
import java.util.List;

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





    // ------------------------------------------------- offers -------------------------------------------------

    /**
     * offer the paramete delimiter to a list of bytes
     * @param lst the list
     */
    private void offerSDelimiter( LinkedList<Byte> lst){
        lst.offer(Delimiters.PARAMETER_DELIMITER);
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



}

