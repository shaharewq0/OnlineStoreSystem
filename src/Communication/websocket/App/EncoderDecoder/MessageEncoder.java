package Communication.websocket.App.EncoderDecoder;


import Communication.websocket.App.messages.Macros.Delimiters;
import Communication.websocket.App.messages.Objects.*;
import Communication.websocket.App.messages.api.Client2ServerMessage;
import Communication.websocket.App.messages.api.Message;
import Communication.websocket.App.messages.api.Server2ClientMessage;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;

public class MessageEncoder implements  Encoder.Binary<Message> {


    @Override
    public ByteBuffer encode(Message msg) throws EncodeException {
        return ByteBuffer.wrap(((Server2ClientMessage)msg).visit(this));
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

    public byte[] accept(AckMessage msg){
        LinkedList<Byte> lst = new LinkedList<>();

        lst.offer(msg.getOpcode());

        return list2array(lst);
    }

    public byte[] accept(NackMessage msg){
        LinkedList<Byte> lst = new LinkedList<>();

        lst.offer(msg.getOpcode());

        return list2array(lst);
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

