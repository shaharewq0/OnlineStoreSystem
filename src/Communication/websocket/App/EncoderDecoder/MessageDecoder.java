package Communication.websocket.App.EncoderDecoder;

import Communication.websocket.App.messages.Macros.Delimiters;
import Communication.websocket.App.messages.Objects.client2server.*;
import Communication.websocket.App.messages.api.Client2ServerMessage;
import Communication.websocket.App.messages.api.Message;
import Communication.websocket.App.messages.Macros.Opcodes;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.util.*;

public class MessageDecoder implements Decoder.Text<Message>  {

    @Override
    public Message decode(String msg) {

        System.out.println("row message: " + msg);

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

            Client2ServerMessage m = (Client2ServerMessage) decode(bytes);
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


    private Message decode(Deque<Byte> message) {

        Deque<Deque<Byte>> parameters = parameterize(message);

        byte op = opcode(parameters);
        switch (op){
            case Opcodes.Demo : return Demo(parameters);

            //extras
            case Opcodes.viewOwnedStores : return viewOwnedStores(parameters);

            //Gust
            case Opcodes.Register : return Register(parameters);
            case Opcodes.Login : return Login(parameters);
            case Opcodes.StoreDetails : return StoreDetails(parameters);
            case Opcodes.StoreProducts : return StoreProducts(parameters);
            case Opcodes.SearchProductByName : return productByName(parameters);
            case Opcodes.SearchProductBycategory : return productByCategory(parameters);
            case Opcodes.SearchProductByKeyword : return productByKeyword(parameters);
            case Opcodes.Save2Basket : return save2Basket(parameters);
            case Opcodes.ProductsInCarts : return ProductsInCarts(parameters);
            case Opcodes.RemoveFromCart : return RemoveFromCart(parameters);
            case Opcodes.Purches : return Purches(parameters);

            //member
            case Opcodes.Logout : return Logout(parameters);
            case Opcodes.OpenStore : return OpenStore(parameters);
            case Opcodes.PurchasesHistory : return PurchasesHistory(parameters);

            // manager
            case  Opcodes.ViewMemberQustions : return ViewMemberQustions(parameters);
            case  Opcodes.Response2Qustion : return Response2Qustion(parameters);
            case  Opcodes.viewAquisitionHistory : return viewAquisitionHistory(parameters);

            // owner
            case Opcodes.AddProduct2Store :  return AddProduct2Store(parameters);
            case Opcodes.RemoveItem : return RemoveItem(parameters);
            case Opcodes.Add2Product : return Add2Product(parameters);
            case Opcodes.Appoint : return Appoint(parameters);
            case Opcodes.FireManager : return FireManager(parameters);
            case  Opcodes.editMangagerPermesions : return editMangagerPermesions(parameters);


            // system manager
            case Opcodes.HistoryOfUser : return HistoryOfUser(parameters);
            case Opcodes.HistoryOfStore : return HistoryOfStore(parameters);


            default:  throw new IllegalArgumentException("unknown opcode : " + op );
        }
    }


    /**
     * recive a message ad split it into the parameters, and convert to lists
     * @param message the mesaage
     * @return a list of the parameters by order
     */
    private Deque<Deque<Byte>> parameterize(Deque<Byte> message){
        return parameterize(message, Delimiters.PARAMETER_DELIMITER);
    }

    private Deque<Deque<Byte>> parameterize(Deque<Byte> message, byte delimiter){

        Deque<Byte> curParamList;
        Deque<Deque<Byte>> parametes = new LinkedList<>();
        Iterator<Byte> iter = message.iterator();
        byte cur;

        //opcode
        curParamList = new LinkedList<>();
        curParamList.offer(iter.next());
        parametes.offer(curParamList);

        // rest
        while (iter.hasNext()) {
            curParamList = new LinkedList<>();

            cur = iter.next();
            while (iter.hasNext() && cur != delimiter){
                curParamList.offer(cur);
                cur = iter.next();
            }

            if(!iter.hasNext()){
                curParamList.offer(cur);
            }

            parametes.offer(curParamList);
        }

        return  parametes;
    }

    private Deque<Deque<Byte>> parameterizeNoOp(Deque<Byte> message, byte delimiter){

        Deque<Byte> curParamList;
        Deque<Deque<Byte>> parametes = new LinkedList<>();
        Iterator<Byte> iter = message.iterator();
        byte cur;

        // rest
        while (iter.hasNext()) {
            curParamList = new LinkedList<>();

            cur = iter.next();
            while (iter.hasNext() && cur != delimiter){
                curParamList.offer(cur);
                cur = iter.next();
            }

            if(!iter.hasNext()){
                curParamList.offer(cur);
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


    private byte[] list2array(Deque<Byte> param) {
        byte[] ret = new byte[param.size()];

        int pos = 0;

        for (byte b :param) {
            ret[pos] = b;
            pos++;
        }
        return ret;
    }





    // ------------------------------------------------------------- poppers -------------------------------------------------------------


    /**
     * pop an opcode from the parameters. removes the parameter from the list.
     * @param parameters the parameters
     * @return the first parameter as byte
     */
    private Byte popOpcode(Deque<Deque<Byte>> parameters){
        return popByte(parameters);
    }

    /**
     * pop an opcode from the parameters. removes the parameter from the list.
     * @param parameters the parameters
     * @return the first parameter as byte
     */
    private Byte popByte(Deque<Deque<Byte>> parameters){
        Deque<Byte> param = parameters.poll();

        if(param == null || param.size() != 1) {
            throw new IllegalArgumentException("could not pop the byte!");
        }

        return param.getFirst();
    }




    private int popInt(Deque<Deque<Byte>> parameters){
        return Integer.parseInt(popString(parameters));
    }

    private double popDouble(Deque<Deque<Byte>> parameters){
        return Double.parseDouble(popString(parameters));
    }



    /**
     * pop a string from the parameters. removes the parameter from the list.
     * @param parameters the parameters
     * @return the first parameter as string
     */
    private String popString(Deque<Deque<Byte>> parameters){
        Deque<Byte> param = parameters.poll();

        if(param == null)
            throw new IllegalArgumentException("could not decode message.");

        return new String(list2array(param));
    }

    private List<String> popStringListL1(Deque<Deque<Byte>> parameters) {
        Deque<Byte> param = parameters.poll();
        LinkedList<String> ret = new LinkedList<>();

        if (param == null)
            throw new IllegalArgumentException("could not decode message.");

        Deque<Deque<Byte>> lst = parameterizeNoOp(param, Delimiters.LIST_DELIMITER);

        while (lst.size() > 0) {
            String curr = popString(lst);
            ret.offer(curr);
        }

        return ret;
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


    private Message StoreDetails(Deque<Deque<Byte>> parameters) {

        Byte    op   = popOpcode(parameters);
        String  name = popString(parameters);

        if(parameters.size() > 0){
            throw new IllegalArgumentException("to much parameter fo a store details object!");
        }

        if(op != Opcodes.StoreDetails){
            throw new IllegalArgumentException("to much parameter fo a store details object!");
        }

        return new StorDetailsMessage(-1, name);
    }

    private Message StoreProducts(Deque<Deque<Byte>> parameters) {

        Byte    op   = popOpcode(parameters);
        String  name = popString(parameters);

        if(parameters.size() > 0){
            throw new IllegalArgumentException("to much parameter fo a store products object!");
        }

        if(op != Opcodes.StoreProducts){
            throw new IllegalArgumentException("to much parameter fo a store products object!");
        }

        return new StoreProductsMessage(-1, name);
    }

    private Message OpenStore(Deque<Deque<Byte>> parameters) {
        Byte    op   = popOpcode(parameters);
        String  name = popString(parameters);
        String  adress = popString(parameters);

        if(parameters.size() > 0){
            throw new IllegalArgumentException("to much parameter!");
        }

        if(op != Opcodes.OpenStore){
            throw new IllegalArgumentException("wrong opcode (SERVER ERROR)!");
        }

        return new OpenStoreMessage(-1, name, adress);
    }

    private Message RemoveFromCart(Deque<Deque<Byte>> parameters) {
        Byte    op   = popOpcode(parameters);
        String  store = popString(parameters);
        String  product = popString(parameters);
        Byte    amount = popByte(parameters);

        if(parameters.size() > 0){
            throw new IllegalArgumentException("to much parameter!");
        }

        if(op != Opcodes.RemoveFromCart){
            throw new IllegalArgumentException("wrong opcode (SERVER ERROR)!");
        }

        return new RemoveProductFromCartMessage(-1,store, product, amount);
    }

    private Message ProductsInCarts(Deque<Deque<Byte>> parameters) {
        Byte    op   = popOpcode(parameters);

        if(parameters.size() > 0){
            throw new IllegalArgumentException("to much parameter!");
        }

        if(op != Opcodes.ProductsInCarts){
            throw new IllegalArgumentException("wrong opcode (SERVER ERROR)!");
        }
        return new ViewCartMessage(-1);
    }

    private Message save2Basket(Deque<Deque<Byte>> parameters) {
        Byte    op   = popOpcode(parameters);
        String  store = popString(parameters);
        String  product = popString(parameters);
        int  amount = popInt(parameters);

        if(parameters.size() > 0){
            throw new IllegalArgumentException("to much parameter!");
        }

        if(op != Opcodes.Save2Basket){
            throw new IllegalArgumentException("wrong opcode (SERVER ERROR)!");
        }
        return new AddProduct2BasketMessage(-1, store, product,amount);
    }

    private Message productByKeyword(Deque<Deque<Byte>> parameters) {
        Byte    op   = popOpcode(parameters);
        String  keyword = popString(parameters);

        if(parameters.size() > 0){
            throw new IllegalArgumentException("to much parameter!");
        }

        if(op != Opcodes.SearchProductByKeyword){
            throw new IllegalArgumentException("wrong opcode (SERVER ERROR)!");
        }
        return new ProductsByKeywordMessage(-1,keyword);
    }

    private Message productByCategory(Deque<Deque<Byte>> parameters) {
        Byte    op   = popOpcode(parameters);
        String  cat = popString(parameters);

        if(parameters.size() > 0){
            throw new IllegalArgumentException("to much parameter!");
        }

        if(op != Opcodes.SearchProductBycategory){
            throw new IllegalArgumentException("wrong opcode (SERVER ERROR)!");
        }
        return new ProductsByCategoryMessage(-1,cat);
    }

    private Message productByName(Deque<Deque<Byte>> parameters) {
        Byte    op   = popOpcode(parameters);
        String  name = popString(parameters);

        if(parameters.size() > 0){
            throw new IllegalArgumentException("to much parameter!");
        }

        if(op != Opcodes.SearchProductByName){
            throw new IllegalArgumentException("wrong opcode (SERVER ERROR)!");
        }
        return new ProductsByNameMessage(-1,name);
    }

    private Message PurchasesHistory(Deque<Deque<Byte>> parameters) {
        Byte    op   = popOpcode(parameters);

        if(parameters.size() > 0){
            throw new IllegalArgumentException("to much parameter!");
        }

        if(op != Opcodes.PurchasesHistory){
            throw new IllegalArgumentException("wrong opcode (SERVER ERROR)!");
        }
        return new ViewPerchesHistory(-1);
    }


    private Message HistoryOfStore(Deque<Deque<Byte>> parameters) {
        Byte    op   = popOpcode(parameters);
        String  name = popString(parameters);

        if(parameters.size() > 0){
            throw new IllegalArgumentException("to much parameter!");
        }

        if(op != Opcodes.HistoryOfStore){
            throw new IllegalArgumentException("wrong opcode (SERVER ERROR)!");
        }
        return new HistoryOfStoreMessage(-1,name);
    }

    private Message HistoryOfUser(Deque<Deque<Byte>> parameters) {
        Byte    op   = popOpcode(parameters);
        String  name = popString(parameters);

        if(parameters.size() > 0){
            throw new IllegalArgumentException("to much parameter!");
        }

        if(op != Opcodes.HistoryOfUser){
            throw new IllegalArgumentException("wrong opcode (SERVER ERROR)!");
        }
        return new HistoryOfUserMessage(-1,name);
    }

    private Message ViewMemberQustions(Deque<Deque<Byte>> parameters) {
        Byte    op   = popOpcode(parameters);
        String  name = popString(parameters);

        if(parameters.size() > 0){
            throw new IllegalArgumentException("to much parameter!");
        }

        if(op != Opcodes.ViewMemberQustions){
            throw new IllegalArgumentException("wrong opcode (SERVER ERROR)!");
        }
        return new ViewMemberQustionsMessage(-1,name);
    }


    private Message Response2Qustion(Deque<Deque<Byte>> parameters) {
        Byte    op   = popOpcode(parameters);
        String  name = popString(parameters);
        byte    qid = popByte(parameters);


        if(parameters.size() > 0){
            throw new IllegalArgumentException("to much parameter!");
        }

        if(op != Opcodes.Response2Qustion){
            throw new IllegalArgumentException("wrong opcode (SERVER ERROR)!");
        }

        return new Response2QuestionMessage(-1,name, qid);
    }

    private Message Purches(Deque<Deque<Byte>> parameters) {
        Byte    op      = popOpcode(parameters);
        String  card    = popString(parameters);
        String  edate   = popString(parameters);
        String  css     = popString(parameters);
        String  owner   = popString(parameters);
        String  adress  = popString(parameters);


        if(parameters.size() > 0){
            throw new IllegalArgumentException("to much parameter!");
        }

        if(op != Opcodes.PurchasesHistory){
            throw new IllegalArgumentException("wrong opcode (SERVER ERROR)!");
        }

        return new PurchaseMessage(-1,card, edate, css, owner, adress);
    }

    private Message AddProduct2Store(Deque<Deque<Byte>> parameters) {
        Byte    op          = popOpcode(parameters);
        String  name        = popString(parameters);
        double  price       = popDouble(parameters);
        List<String>  cats  = popStringListL1(parameters);
        List<String>  keyws = popStringListL1(parameters);
        String  storename   = popString(parameters);
        int ammount         = popInt(parameters);


        if(parameters.size() > 0){
            throw new IllegalArgumentException("to much parameter!");
        }

        if(op != Opcodes.AddProduct2Store){
            throw new IllegalArgumentException("wrong opcode (SERVER ERROR)!");
        }

        return new CreateProductMessage(-1,name,price, cats, keyws, storename, ammount);
    }

    private Message Appoint(Deque<Deque<Byte>> parameters) {
        Byte    op          = popOpcode(parameters);
        String  username    = popString(parameters);
        String  storename   = popString(parameters);
        byte    role        = popByte(parameters);


        if(parameters.size() > 0){
            throw new IllegalArgumentException("to much parameter!");
        }

        if(op != Opcodes.Appoint){
            throw new IllegalArgumentException("wrong opcode (SERVER ERROR)!");
        }

        return new AppointMessage(-1,username, storename,role);
    }

    private Message FireManager(Deque<Deque<Byte>> parameters) {
        Byte    op          = popOpcode(parameters);
        String  username    = popString(parameters);
        String  store       = popString(parameters);


        if(parameters.size() > 0){
            throw new IllegalArgumentException("to much parameter!");
        }

        if(op != Opcodes.FireManager){
            throw new IllegalArgumentException("wrong opcode (SERVER ERROR)!");
        }

        return new FireMessage(-1,username, store);
    }

    private Message RemoveItem(Deque<Deque<Byte>> parameters) {
        Byte    op      = popOpcode(parameters);
        String  store   = popString(parameters);
        String  product = popString(parameters);


        if(parameters.size() > 0){
            throw new IllegalArgumentException("to much parameter!");
        }

        if(op != Opcodes.RemoveItem){
            throw new IllegalArgumentException("wrong opcode (SERVER ERROR)!");
        }

        return new RemoveProductMessage(-1,store, product);
    }

    private Message Add2Product(Deque<Deque<Byte>> parameters) {
        Byte    op      = popOpcode(parameters);
        String  product = popString(parameters);
        String  store   = popString(parameters);
        byte    ammount = popByte(parameters); // TODO


        if(parameters.size() > 0){
            throw new IllegalArgumentException("to much parameter!");
        }

        if(op != Opcodes.Add2Product){
            throw new IllegalArgumentException("wrong opcode (SERVER ERROR)!");
        }

        return new Add2ProductMessage(-1,product, store, ammount);
    }

    private Message viewOwnedStores(Deque<Deque<Byte>> parameters) {
        Byte op = popOpcode(parameters);


        if(parameters.size() > 0){
            throw new IllegalArgumentException("to much parameter!");
        }

        if(op != Opcodes.viewOwnedStores){
            throw new IllegalArgumentException("wrong opcode (SERVER ERROR)!");
        }

        return new ViewOwnedStoresMessage((byte)-1);
    }

    private Message viewAquisitionHistory(Deque<Deque<Byte>> parameters) {
        Byte    op      = popOpcode(parameters);
        String  store   = popString(parameters);

        if(parameters.size() > 0){
            throw new IllegalArgumentException("to much parameter!");
        }

        if(op != Opcodes.viewAquisitionHistory){
            throw new IllegalArgumentException("wrong opcode (SERVER ERROR)!");
        }

        return new ViewAquisitionMessage((byte)-1, store);
    }

    private Message editMangagerPermesions(Deque<Deque<Byte>> parameters) {
        Byte            op          = popOpcode(parameters);
        String          store       = popString(parameters);
        String          manager     = popString(parameters);
        List<String>    permotions  = popStringListL1(parameters);

        if(parameters.size() > 0){
            throw new IllegalArgumentException("to much parameter!");
        }

        if(op != Opcodes.editMangagerPermesions){
            throw new IllegalArgumentException("wrong opcode (SERVER ERROR)!");
        }

        return new EditPermitionsMessage((byte)-1, store,manager, permotions);
    }
}
