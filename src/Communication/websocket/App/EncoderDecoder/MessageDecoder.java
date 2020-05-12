package Communication.websocket.App.EncoderDecoder;

import Communication.websocket.App.messages.Macros.Delimiters;
import Communication.websocket.App.messages.Objects.DemoMessage;
import Communication.websocket.App.messages.Objects.LoginMessage;
import Communication.websocket.App.messages.Objects.LogoutMessage;
import Communication.websocket.App.messages.api.Client2ServerMessage;
import Communication.websocket.App.messages.api.Message;
import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.App.messages.Objects.RegisterMessage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.util.Deque;
import java.util.LinkedList;

public class MessageDecoder implements Decoder.Text<Message>  {


    @Override
    public Message decode(String msg) throws DecodeException {

        try{
            JSONParser parser = new JSONParser();
            JSONObject received = (JSONObject)parser.parse(msg);
            JSONObject message = (JSONObject)(received.get("json_data"));
            Deque<Byte> bytes = new LinkedList<>();

            long id = (long)received.get("cmd_id");

            for (int i = 0; true ; i++) {
                Object obj = message.get(String.valueOf(i));

                if(obj == null)
                    break;

                long cur = (long)obj;
                byte b = (byte)cur;
                bytes.offer(b);
            }

            Client2ServerMessage m = (Client2ServerMessage) decode(list2array(bytes));
            m.setId(id);

            return m;

        }
        catch(Exception pe) {
            System.out.println("[!!! IMPORTANT !!!] Received unknown message: : " + msg);
        }

        return null;
    }

    @Override
    public boolean willDecode(String byteBuffer) {
        return true;
    }



    @Override
    public void init(EndpointConfig endpointConfig) {
    }

    @Override
    public void destroy() {

    }


    // ------------------------------------------------------------- general decode -------------------------------------------------------------


    private Message decode(byte[] message) {

        Deque<Deque<Byte>> parameters = parameterize(message);

        byte op = opcode(parameters);
        switch (op){
            case Opcodes.Demo : return Demo(parameters);

            case Opcodes.Register : return Register(parameters);
            case Opcodes.Login : return Login(parameters);


            case  Opcodes.Logout : return Logout(parameters);


            default:  throw new IllegalArgumentException("unknown opcode : " + op );
        }
    }


    /**
     * recive a message ad split it into the parameters, and convert to lists
     * @param message the mesaage
     * @return a list of the parameters by order
     */
    private Deque<Deque<Byte>> parameterize(byte[] message){

        Deque<Byte> curParamList;
        Deque<Deque<Byte>> parametes = new LinkedList<>();
        int curIndex = 0;

        //opcode
        curParamList = new LinkedList<>();
        curParamList.offer(message[curIndex]);
        parametes.offer(curParamList);
        curIndex++;

        // rest
        for (; curIndex <  message.length; curIndex++) {
            curParamList = new LinkedList<>();

            for (; curIndex <  message.length && message[curIndex] != Delimiters.PARAMETER_DELIMITER; curIndex++) {
                curParamList.offer(message[curIndex]);
            }

            parametes.offer(curParamList);
        }

        return  parametes;
    }


    /**
     * retruve the opcode
     * @param parameters the parameters
     * @return the opcode
     */
    private Byte opcode(Deque<Deque<Byte>> parameters) {
        return parameters.getFirst().getFirst();
    }


    /**
     * convert a klist to array
     * @return the list to convert
     */
    private byte[] list2array(Deque<Byte> lst){

        int size = lst.size();
        int i = 0;

        byte[] arr = new byte[size];

        for (Byte b: lst){
            arr[i] = b;
            i++;
        }

        return  arr;
    }




    // ------------------------------------------------------------- poppers -------------------------------------------------------------


    /**
     * pop an opcode from the parameters. removes the parameter from the list.
     * @param parameters the parameters
     * @return the first parameter as byte
     */
    private Byte popOpcode(Deque<Deque<Byte>> parameters){
        Deque<Byte> param = parameters.poll();

        if(param == null || param.size() != 1) {
            throw new IllegalArgumentException("could not pop the opcode!");
        }

        return param.getFirst();
    }

    /**
     * pop a string from the parameters. removes the parameter from the list.
     * @param parameters the parameters
     * @return the first parameter as string
     */
    private String popString(Deque<Deque<Byte>> parameters){
        Deque<Byte> param = parameters.poll();

        return new String(list2array(param));
    }



    // ------------------------------------------------------------- messages builders -------------------------------------------------------------

    private Message Demo( Deque<Deque<Byte>> parameters){
        Byte    op   = popOpcode(parameters);
        String  content = popString(parameters);

        if(parameters.size() > 0){
            throw new IllegalArgumentException("to much parameter fo a demo object!");
        }

        return new DemoMessage(op, content);
    }

    private Message Register( Deque<Deque<Byte>> parameters){
        Byte    op   = popOpcode(parameters);
        String  name = popString(parameters);
        String  pass = popString(parameters);

        if(parameters.size() > 0){
            throw new IllegalArgumentException("to much parameter fo a register object!");
        }

        if(op != Opcodes.Register){
            throw new IllegalArgumentException("to much parameter fo a register object!");
        }

        return new RegisterMessage(-1, name, pass);
    }

    private Message Login( Deque<Deque<Byte>> parameters){
        Byte    op   = popOpcode(parameters);
        String  name = popString(parameters);
        String  pass = popString(parameters);

        if(parameters.size() > 0){
            throw new IllegalArgumentException("to much parameter fo a register object!");
        }

        if(op != Opcodes.Login){
            throw new IllegalArgumentException("to much parameter fo a register object!");
        }

        return new LoginMessage(-1, name, pass);
    }



    private Message Logout(Deque<Deque<Byte>> parameters) {
        Byte    op   = popOpcode(parameters);

        if(parameters.size() > 0){
            throw new IllegalArgumentException("to much parameter fo a register object!");
        }

        if(op != Opcodes.Logout){
            throw new IllegalArgumentException("to much parameter fo a register object!");
        }

        return new LogoutMessage(-1);
    }
}
