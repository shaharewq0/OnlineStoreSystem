package Communication.websocket.App.EncoderDecoder;

import Communication.websocket.App.messages.Macros.Delimiters;
import Communication.websocket.App.messages.Objects.client2server.*;
import Communication.websocket.App.messages.api.Client2ServerMessage;
import Communication.websocket.App.messages.api.Message;
import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.Logger.ServerLogger;
import Domain.Policies.Acquisitions.*;
import Domain.Policies.Discounts.*;
import Domain.info.ProductDetails;
import Service_Layer.guest_accese.guest_accese;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


class DiscountAcquisitionDecoder{
    private static DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static final byte AND = 0x10;
    static final byte OR = 0x11;
    static final byte XOR = 0x12;

    protected static LocalDate toDate(String date) {
        return LocalDate.parse(date, format);
    }

    protected static Stack<String> stringSplitToStack(String str, String regex) {
        List<String> lst = Arrays.asList(str.split(regex));
        Collections.reverse(lst);
        Stack<String> stack = new Stack<>();
        stack.addAll(lst);
        return stack;
    }
}


class DiscountFactory extends DiscountAcquisitionDecoder{
    private static String REGEX = "\1";

    static Discount discountFactory(String discount) {
        try {
            return discountFactory(stringSplitToStack(discount, REGEX));
        } catch (Exception e) {
            return null;
        }
    }

    private static Discount discountFactory(Stack<String> params) throws Exception {

        Discount curr = popDiscount(params);

        while (!params.empty()){

            String poped = params.pop();
            int next = poped.charAt(0);
            params.push("" + poped.charAt(1));

            switch (next) {

                case AND: //composite and
                    curr = new AndDiscount(Arrays.asList(curr, popDiscount(params))); break;

                case OR: //composite or
                    curr = new OrDiscount(Arrays.asList(curr, popDiscount(params))); break;

                case XOR: //composite xor
                    curr = new XorDiscount(Arrays.asList(curr, popDiscount(params))); break;

            }
        }

       return curr;
    }

    private static Discount popDiscount(Stack<String> params) throws Exception {

        int type = params.pop().charAt(0);
        String productName = null;
        int percentage = 0;
        double min = 0;
        LocalDate date = null;

        if(type > 0x12) {
            productName = params.pop();
            percentage = Integer.parseInt(params.pop());
            min = Double.parseDouble(params.pop());
            date = toDate(params.pop());
        }

        switch (type) {
            case 0x13: //visible
                return new VisibleDiscount(productName,percentage, date);

            case 0x14: //conditional amount
                return new ConditionalAmountDiscount(productName, percentage, date, (int) min);

            case 0x15: //conditional price
                return new ConditionalPriceDiscount(productName, percentage, date, min);

            default:
                throw new Exception();
        }
    }
}





class AcquisitionFactory extends DiscountAcquisitionDecoder{
    private static String REGEX = "\1";

    static Acquisition acquisitionFactory(String discount) {
        try {
            return acquisitionFactory(stringSplitToStack(discount, REGEX));
        } catch (Exception e) {
            return null;
        }
    }

    private static Acquisition acquisitionFactory(Stack<String> params) throws Exception {

        Acquisition curr = popAcquisition(params);

        while (!params.empty()){

            String poped = params.pop();
            int next = poped.charAt(0);
            params.push("" + poped.charAt(1));

            switch (next) {

                case AND: //composite and
                    curr = new AndAcq(Arrays.asList(curr, popAcquisition(params))); break;

                case OR: //composite or
                    curr = new OrAcq(Arrays.asList(curr, popAcquisition(params))); break;

                case XOR: //composite xor
                    curr = new XorAcq(Arrays.asList(curr, popAcquisition(params))); break;

            }
        }

        return curr;
    }

    private static Acquisition popAcquisition(Stack<String> params) throws Exception {

        int     type        = params.pop().charAt(0);
        String  productName = params.pop();
        int     condition   = Integer.parseInt(params.pop()) - 0x10;

        switch (type) {
            case 0x10: //min amount
                return new AcqMinAmount(productName, condition);

            case 0x11: //max amount
                return new AcqMaxAmount(productName, condition);

            default:
                throw new Exception();
        }
    }
}
















public class MessageDecoder implements Decoder.Text<Message>  {

    @Override
    public Message decode(String msg) {

        System.out.println("row message: " + msg);
        ServerLogger.GetInstance().Add_Log("row message: " + msg);

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
            case Opcodes.FilterByPrice              : return FilterByPrice(parameters);
            case Opcodes.FilterByRating             : return FilterByRating(parameters);
            case Opcodes.FilterByStoreRating        : return FilterByStoreRating(parameters);


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
            case Opcodes.getDiscounts               : return getDiscounts(parameters);
            case Opcodes.deleteDiscount             : return deleteDiscount(parameters);
            case Opcodes.getAcquisitions            : return getAcquisitions(parameters);
            case Opcodes.removeAcquisition          : return removeAcquisition(parameters);
            case Opcodes.addAcquisitions            : return addAcquisitions(parameters);


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

    private List<ProductDetails> popProductDetailsList(Deque<Deque<Byte>> parameters) {
        Deque<Byte> param = parameters.poll();
        LinkedList<ProductDetails> ret = new LinkedList<>();

        if (param == null)
            throw new IllegalArgumentException("could not decode message.");

        Deque<Deque<Byte>> lst = parameterizeNoOp(param, Delimiters.LIST_DELIMITER);

        while (lst.size() > 0) {
            String curr = popString(lst);
            String[] prdDetails = curr.split("" + Delimiters.LIST_DELIMITER_L2);
            String prod = prdDetails[0];
            String store = prdDetails[1];

            ret.offer(guest_accese.getProductDetails(store, prod));
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
        Byte    op      = popOpcode(parameters);
        String  store   = popString(parameters);
        String  product = popString(parameters);
        int     amount  = popInt(parameters);


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

        // billing info
        String  card    = popString(parameters);
        String  edate   = popString(parameters);
        String  css     = popString(parameters);
        String  owner   = popString(parameters);
        String  id      = popString(parameters);

        // shipping info
        String  adress  = popString(parameters);
        String  city    = popString(parameters);
        String  country = popString(parameters);
        String  zip     = popString(parameters);


        finalCheck(parameters);
        return new PurchaseMessage(-1, card, edate, css, owner, id, adress, city, country, zip);
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
        return new CreateDiscountMessage((byte)-1, store, DiscountFactory.discountFactory(discs));
    }

    private Message getDiscounts(Deque<Deque<Byte>> parameters) {
        Byte    op      = popOpcode(parameters);
        String  store   = popString(parameters);

        finalCheck(parameters);
        return new GetDiscountMessage((byte)-1, store);
    }

    private Message deleteDiscount(Deque<Deque<Byte>> parameters) {
        Byte    op      = popOpcode(parameters);
        String  store   = popString(parameters);
        int     id      = popInt(parameters);

        finalCheck(parameters);
        return new RemoveDiscountMessage((byte)-1, store, id);
    }

    private Message getAcquisitions(Deque<Deque<Byte>> parameters) {
        Byte    op      = popOpcode(parameters);
        String  store   = popString(parameters);

        finalCheck(parameters);
        return new GetAcquisitionsMessage((byte)-1, store);
    }

    private Message removeAcquisition(Deque<Deque<Byte>> parameters) {
        Byte    op      = popOpcode(parameters);
        String  store   = popString(parameters);
        int     id      = popInt(parameters);

        finalCheck(parameters);
        return new RemoveAcquisitionMessage((byte)-1, store, id);
    }

    private Message addAcquisitions(Deque<Deque<Byte>> parameters) {
        Byte    op      = popOpcode(parameters);
        String  store   = popString(parameters);
        String  aqcs    = popString(parameters);

        finalCheck(parameters);
        return new AddAcquisitionMessage((byte)-1, store, AcquisitionFactory.acquisitionFactory(aqcs));
    }

    private Message Filter(Deque<Deque<Byte>> parameters) {
        Byte    op      = popOpcode(parameters);
        double  min     = popDouble(parameters);
        double  max     = popDouble(parameters);
        List<ProductDetails> prods = popProductDetailsList(parameters);

        finalCheck(parameters);
        return new FilterMessage((byte)-1, prods, min, max, FilterMessage.FiltrtType.get(op));

    }

    private Message FilterByPrice(Deque<Deque<Byte>> parameters) {
        return Filter(parameters);
    }

    private Message FilterByRating(Deque<Deque<Byte>> parameters) {
        return Filter(parameters);
    }

    private Message FilterByStoreRating(Deque<Deque<Byte>> parameters) {
        return Filter(parameters);
    }
}
