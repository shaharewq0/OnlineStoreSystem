package Communication.websocket.App.EncoderDecoder;

import Communication.websocket.App.messages.Macros.Delimiters;
import Communication.websocket.App.messages.Objects.client2server.*;
import Communication.websocket.App.messages.api.Client2ServerMessage;
import Communication.websocket.App.messages.api.Message;
import Communication.websocket.App.messages.Macros.Opcodes;
import Domain.Policies.Discounts.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


class DiscountFactory{

    private static DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static String REGEX = "\1";

    static Discount discountFactory(String discount) {
        try {
            return discountFactory(stringSplitToStack(discount, REGEX));
        } catch (Exception e) {
            return null;
        }
    }

    private static Discount discountFactory(Stack<String> params) throws Exception {
        int         type        = Integer.parseInt(params.pop());
        String      productName = params.pop();
        int         percentage  = Integer.parseInt(params.pop());
        double      min         = Double.parseDouble(params.pop());
        LocalDate date        = toDate(params.pop());

        switch (type) {
            case 0: //visible
                return new VisibleDiscount(productName,percentage, date);

            case 1: //conditional amount
                return new ConditionalAmountDiscount(productName, percentage, date, (int) min);

            case 2: //conditional price
                return new ConditionalPriceDiscount(productName, percentage, date, min);

            case 10: //composite and
                return new AndDiscount(parseDiscountList(params));

            case 11: //composite or
                return new OrDiscount(parseDiscountList(params));

            case 12: //composite xor
                return new XorDiscount(parseDiscountList(params));

            default:
                throw new Exception();
        }
    }

    private static LocalDate toDate(String date) {
        return LocalDate.parse(date, format);
    }

    private static List<Discount> parseDiscountList(Stack<String> params) throws Exception {
        List<Discount> discountList = new LinkedList<>();
        int n = 1;//Integer.parseInt(params.pop());
        for (int i = 0; i < n; i++) {
            discountList.add(discountFactory(params));
        }
        return discountList;
    }

    protected static Stack<String> stringSplitToStack(String str, String regex) {
        List<String> lst = Arrays.asList(str.split(regex));
        Collections.reverse(lst);
        Stack<String> stack = new Stack<>();
        stack.addAll(lst);
        return stack;
    }

}







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

            // test command
            case Opcodes.Demo : return Demo(parameters);

            //extras
            case Opcodes.viewOwnedStores            : return viewOwnedStores(parameters);
            case Opcodes.memberType                 : return memberType(parameters);

            //Gust
            case Opcodes.Register                   : return Register(parameters);
            case Opcodes.Login                      : return Login(parameters);
            case Opcodes.StoreDetails               : return StoreDetails(parameters);
            case Opcodes.StoreProducts              : return StoreProducts(parameters);
            case Opcodes.SearchProductByName        : return productByName(parameters);
            case Opcodes.SearchProductBycategory    : return productByCategory(parameters);
            case Opcodes.SearchProductByKeyword     : return productByKeyword(parameters);
            case Opcodes.Save2Basket                : return save2Basket(parameters);
            case Opcodes.ProductsInCarts            : return ProductsInCarts(parameters);
            case Opcodes.RemoveFromCart             : return RemoveFromCart(parameters);
            case Opcodes.Purches                    : return Purches(parameters);

            //member
            case Opcodes.Logout                     : return Logout(parameters);
            case Opcodes.OpenStore                  : return OpenStore(parameters);
            case Opcodes.PurchasesHistory           : return PurchasesHistory(parameters);

            // manager
            case  Opcodes.ViewMemberQustions        : return ViewMemberQustions(parameters);
            case  Opcodes.Response2Qustion          : return Response2Qustion(parameters);
            case  Opcodes.viewAquisitionHistory     : return viewAquisitionHistory(parameters);

            // owner
            case Opcodes.AddProduct2Store           : return AddProduct2Store(parameters);
            case Opcodes.RemoveItem                 : return RemoveItem(parameters);
            case Opcodes.Add2Product                : return Add2Product(parameters);
            case Opcodes.Appoint                    : return Appoint(parameters);
            case Opcodes.FireManager                : return FireManager(parameters);
            case Opcodes.editMangagerPermesions     : return editMangagerPermesions(parameters);
            case Opcodes.AcceptPendingAppintment    : return AcceptPendingAppintment(parameters);
            case Opcodes.PendingAppountments        : return PendingAppountments(parameters);
            case Opcodes.createDiscount             : return createDiscount(parameters);


            // system manager
            case Opcodes.HistoryOfUser              : return HistoryOfUser(parameters);
            case Opcodes.HistoryOfStore             : return HistoryOfStore(parameters);



            // error - unown opcode
            default                                 : System.out.println("unknown opcode recived :" + op); throw new IllegalArgumentException("unknown opcode : " + op );
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

    private void finalCheck(Iterable<?> parameters){
        if(parameters.iterator().hasNext()){
            throw new IllegalArgumentException("to much parameter!");
        }
    }


    // ------------------------------------------------------------- messages builders -------------------------------------------------------------

    private Message Demo( Deque<Deque<Byte>> parameters){
        Byte    op   = popOpcode(parameters);
        String  content = popString(parameters);

        finalCheck(parameters);
        return new DemoMessage(op, content);
    }




    private Message Register( Deque<Deque<Byte>> parameters){
        Byte    op   = popOpcode(parameters);
        String  name = popString(parameters);
        String  pass = popString(parameters);

        finalCheck(parameters);
        return new RegisterMessage(-1, name, pass);
    }

    private Message Login( Deque<Deque<Byte>> parameters){
        Byte    op   = popOpcode(parameters);
        String  name = popString(parameters);
        String  pass = popString(parameters);

        finalCheck(parameters);
        return new LoginMessage(-1, name, pass);
    }



    private Message Logout(Deque<Deque<Byte>> parameters) {
        Byte    op   = popOpcode(parameters);

        finalCheck(parameters);
        return new LogoutMessage(-1);
    }


    private Message StoreDetails(Deque<Deque<Byte>> parameters) {
        Byte    op   = popOpcode(parameters);
        String  name = popString(parameters);

        finalCheck(parameters);
        return new StorDetailsMessage(-1, name);
    }

    private Message StoreProducts(Deque<Deque<Byte>> parameters) {
        Byte    op   = popOpcode(parameters);
        String  name = popString(parameters);

        finalCheck(parameters);
        return new StoreProductsMessage(-1, name);
    }

    private Message OpenStore(Deque<Deque<Byte>> parameters) {
        Byte    op   = popOpcode(parameters);
        String  name = popString(parameters);
        String  adress = popString(parameters);


        finalCheck(parameters);
        return new OpenStoreMessage(-1, name, adress);
    }

    private Message RemoveFromCart(Deque<Deque<Byte>> parameters) {
        Byte    op   = popOpcode(parameters);
        String  store = popString(parameters);
        String  product = popString(parameters);
        Byte    amount = popByte(parameters);


        finalCheck(parameters);
        return new RemoveProductFromCartMessage(-1,store, product, amount);
    }

    private Message ProductsInCarts(Deque<Deque<Byte>> parameters) {
        Byte    op   = popOpcode(parameters);

        finalCheck(parameters);
        return new ViewCartMessage(-1);
    }

    private Message save2Basket(Deque<Deque<Byte>> parameters) {
        Byte    op   = popOpcode(parameters);
        String  store = popString(parameters);
        String  product = popString(parameters);
        int  amount = popInt(parameters);

        finalCheck(parameters);
        return new AddProduct2BasketMessage(-1, store, product,amount);
    }

    private Message productByKeyword(Deque<Deque<Byte>> parameters) {
        Byte    op   = popOpcode(parameters);
        String  keyword = popString(parameters);

        finalCheck(parameters);
        return new ProductsByKeywordMessage(-1,keyword);
    }

    private Message productByCategory(Deque<Deque<Byte>> parameters) {
        Byte    op   = popOpcode(parameters);
        String  cat = popString(parameters);

        finalCheck(parameters);
        return new ProductsByCategoryMessage(-1,cat);
    }

    private Message productByName(Deque<Deque<Byte>> parameters) {
        Byte    op   = popOpcode(parameters);
        String  name = popString(parameters);

        finalCheck(parameters);
        return new ProductsByNameMessage(-1,name);
    }

    private Message PurchasesHistory(Deque<Deque<Byte>> parameters) {
        Byte    op   = popOpcode(parameters);

        finalCheck(parameters);
        return new ViewPerchesHistory(-1);
    }


    private Message HistoryOfStore(Deque<Deque<Byte>> parameters) {
        Byte    op   = popOpcode(parameters);
        String  name = popString(parameters);

        finalCheck(parameters);
        return new HistoryOfStoreMessage(-1,name);
    }

    private Message HistoryOfUser(Deque<Deque<Byte>> parameters) {
        Byte    op   = popOpcode(parameters);
        String  name = popString(parameters);

        finalCheck(parameters);
        return new HistoryOfUserMessage(-1,name);
    }

    private Message ViewMemberQustions(Deque<Deque<Byte>> parameters) {
        Byte    op   = popOpcode(parameters);
        String  name = popString(parameters);

        finalCheck(parameters);
        return new ViewMemberQustionsMessage(-1,name);
    }


    private Message Response2Qustion(Deque<Deque<Byte>> parameters) {
        Byte    op      = popOpcode(parameters);
        String  name    = popString(parameters);
        byte    qid     = popByte(parameters);

        finalCheck(parameters);
        return new Response2QuestionMessage(-1,name, qid);
    }

    private Message Purches(Deque<Deque<Byte>> parameters) {
        Byte    op      = popOpcode(parameters);
        String  card    = popString(parameters);
        String  edate   = popString(parameters);
        String  css     = popString(parameters);
        String  owner   = popString(parameters);
        String  adress  = popString(parameters);


        finalCheck(parameters);
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

        finalCheck(parameters);
        return new CreateProductMessage(-1,name,price, cats, keyws, storename, ammount);
    }

    private Message Appoint(Deque<Deque<Byte>> parameters) {
        Byte    op          = popOpcode(parameters);
        String  username    = popString(parameters);
        String  storename   = popString(parameters);
        byte    role        = popByte(parameters);


        finalCheck(parameters);
        return new AppointMessage(-1,username, storename,role);
    }

    private Message FireManager(Deque<Deque<Byte>> parameters) {
        Byte    op          = popOpcode(parameters);
        String  username    = popString(parameters);
        String  store       = popString(parameters);


        finalCheck(parameters);
        return new FireMessage(-1,username, store);
    }

    private Message RemoveItem(Deque<Deque<Byte>> parameters) {
        Byte    op      = popOpcode(parameters);
        String  store   = popString(parameters);
        String  product = popString(parameters);

        finalCheck(parameters);
        return new RemoveProductMessage(-1,store, product);
    }

    private Message Add2Product(Deque<Deque<Byte>> parameters) {
        Byte    op      = popOpcode(parameters);
        String  product = popString(parameters);
        String  store   = popString(parameters);
        int     ammount = popInt(parameters);

        finalCheck(parameters);
        return new Add2ProductMessage(-1,product, store, ammount);
    }

    private Message viewOwnedStores(Deque<Deque<Byte>> parameters) {
        Byte op = popOpcode(parameters);

        finalCheck(parameters);
        return new ViewOwnedStoresMessage((byte)-1);
    }

    private Message viewAquisitionHistory(Deque<Deque<Byte>> parameters) {
        Byte    op      = popOpcode(parameters);
        String  store   = popString(parameters);

        finalCheck(parameters);
        return new ViewAquisitionMessage((byte)-1, store);
    }

    private Message editMangagerPermesions(Deque<Deque<Byte>> parameters) {
        Byte            op          = popOpcode(parameters);
        String          store       = popString(parameters);
        String          manager     = popString(parameters);
        List<String>    permotions  = popStringListL1(parameters);

        finalCheck(parameters);
        return new EditPermitionsMessage((byte)-1, store,manager, permotions);
    }

    private Message memberType(Deque<Deque<Byte>> parameters) {
        Byte op = popOpcode(parameters);

        finalCheck(parameters);
        return new memberTypeMessage((byte)-1);
    }

    private Message AcceptPendingAppintment(Deque<Deque<Byte>> parameters) {
        Byte    op      = popOpcode(parameters);
        String  store   = popString(parameters);
        String  apointe = popString(parameters);

        finalCheck(parameters);
        return  new AcceptPendingAppointmentMessage((byte)-1, store, apointe);
    }

    private Message PendingAppountments(Deque<Deque<Byte>> parameters) {
        Byte    op      = popOpcode(parameters);
        String  store   = popString(parameters);

        finalCheck(parameters);
        return new GetPendingAppointments((byte)-1, store);
    }

    private Message createDiscount(Deque<Deque<Byte>> parameters) {
        Byte    op      = popOpcode(parameters);
        String  store   = popString(parameters);
        String  discs   = popString(parameters);

        finalCheck(parameters);
        return new createDiscount((byte)-1, store, DiscountFactory.discountFactory(discs));
    }
}
