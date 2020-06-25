package Communication.websocket.App.api_impl;

import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.App.messages.Macros.Permitions;
import Communication.websocket.App.messages.Objects.client2server.*;
import Communication.websocket.App.messages.Objects.server2client.*;
import Communication.websocket.App.messages.api.Client2ServerMessage;
import Communication.websocket.api.BaseServer;
import Communication.websocket.api.MessagingProtocol;
import Communication.websocket.App.messages.api.Message;
import Domain.Policies.Discounts.*;
import Domain.UserClasses.UserPurchase;
import Domain.Store.Product;
import Domain.Store.StorePurchase;
import Domain.info.ProductDetails;
import Domain.info.Question;
import Domain.info.StoreInfo;
import Domain.store_System.ClintObserver;
import Domain.store_System.MSGObservable;
import Service_Layer.guest_accese.guest_accese;
import Service_Layer.manager_accese.manager_accese;
import Service_Layer.member_accese.member_accese;
import Service_Layer.owner_accese.owner_accese;
import Service_Layer.sys_manager_accese.sys_mangaer_accese;
import Service_Layer.userAddress;
import extornal.payment.CreditCard;
import tests.AcceptanceTests.auxiliary.StoreDetails;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

class SubInstructions {

     static final int usecase4_5_appointManager_code = 0x10;
     static final int usecase4_3_appointOwner_code = 0x11;
}


public class MallProtocol implements MessagingProtocol<Message>, ClintObserver {

    private BaseServer<Message>  server;

    private int gustID;

    private String username;
    private String paasword;

    private Thread systemStateViewr = null;




    public MallProtocol(BaseServer<Message> server) {
        this.server = server;
        this.gustID = guest_accese.ImNew();
        username = "";
        paasword = "";
    }



    @Override
    public Message process(Message msg) {
        return ((Client2ServerMessage)msg).visit(this);
    }

    @Override
    public void end() {
        if(!username.equals("")){
            accept(new LogoutMessage(-55)); // make sure to logout user
        }
        gustID = -88;
    }

    @Override
    public void Notifi_me(MSGObservable observable) {
        List<String> msgs = observable.getMessges();

        for (String msg: msgs) {
            System.out.println("sending special message to :" + username);
            server.send(this, new StringResponse(msg));
        }

    }





    public Message accept(DemoMessage msg){
        return new NackMessage(msg.getId());
    }




    public Message accept(RegisterMessage msg){

        if(guest_accese.usecase2_2_guest_register(msg.getUsername(), msg.getPassword())){
            return new AckMessage(msg.getId());
        }

        return new NackMessage(msg.getId());
    }

    public Message accept(LoginMessage msg){
        if(guest_accese.usecase2_3_login(gustID, msg.getUsername(), msg.getPassword(), this)){
            username = msg.getUsername();
            paasword = msg.getPassword();

            return new AckMessage(msg.getId());
        }

        return new NackMessage(msg.getId());
    }

    public Message accept(LogoutMessage msg) {
        member_accese.usecase3_1_Logout(gustID);
        username = "";
        paasword = "";

        return new AckMessage(msg.getId());
    }

    public Message accept(StorDetailsMessage msg) {
        StoreDetails detils = guest_accese.usecase2_4A_getStoreDetails(msg.getName());

        if(detils != null) {
            return new StorDetailsResponseMessage(msg.getId(), detils.getName(), detils.getAdress(), (byte) detils.getRating());
        }

        return new NackMessage(msg.getId());
    }




    public Message accept(StoreProductsMessage msg) {
        StoreInfo detils = guest_accese.usecase2_4B_getStoreProdacts(msg.getName());

        if(detils != null) {
            return new ProductDetailsListResponse(msg.getId(), detils.products);
        }

        return new NackMessage(msg.getId());
    }

    public Message accept(ProductsByNameMessage msg) {
        List<ProductDetails> products = guest_accese.usecase2_5A_searchProductByName(msg.getName());

        if(products != null) {
            return new ProductDetailsListResponse(msg.getId(), products);
        }

        return new NackMessage(msg.getId());
    }

    public Message accept(ProductsByCategoryMessage msg) {
        List<ProductDetails> products = guest_accese.usecase2_5B_searchProductByCategory(msg.getCategory());

        if(products != null) {
            return new ProductDetailsListResponse(msg.getId(), products);
        }

        return new NackMessage(msg.getId());
    }

    public Message accept(ProductsByKeywordMessage msg) {
        List<ProductDetails> products = guest_accese.usecase2_5C_searchProductByKeyword(msg.getKeyword());

        if(products != null) {
            return new ProductDetailsListResponse(msg.getId(), products);
        }

        return new NackMessage(msg.getId());
    }



    public Message accept(AddProduct2BasketMessage msg) {
        if(guest_accese.usecase2_6_saveProductToBasket(gustID, msg.getStore(), msg.getProduct(), msg.getAmount())){
            return new AckMessage(msg.getId());
        }

        return new NackMessage(msg.getId());
    }

    public Message accept(ViewCartMessage msg) {
        List<ProductDetails> products = guest_accese.usecase2_7A_WatchProdactsInCart(gustID);
        double price = guest_accese.getCartPrice(gustID);

        if(products != null) {
            return new PrductsInCartResponse(msg.getId(), products, price);
        }

        return new NackMessage(msg.getId());
    }

    public Message accept(RemoveProductFromCartMessage msg) {
        if(guest_accese.usecase2_7b_RemoveProdactsInCart(gustID, msg.getStore(), msg.getProduct(), msg.getAmount()) > 0){
            return new AckMessage(msg.getId());
        }

        return new NackMessage(msg.getId());
    }

    public Message accept(OpenStoreMessage msg) {
        if(member_accese.usecase3_2_OpenStore(gustID, new StoreDetails(msg.getName(), msg.getAddres(), 0))){
            return new AckMessage(msg.getId());
        }

        return new NackMessage(msg.getId());
    }

    public Message accept(ViewPerchesHistory msg) {
        List<UserPurchase> history = member_accese.usecase3_7_ReviewPurchasesHistory(gustID);

        if(history != null){
            return new UserPurchaseListResponse((byte)-1,msg.getId(), history);
        }

        return new NackMessage(msg.getId());
    }

    public Message accept(HistoryOfUserMessage msg) {
        List<UserPurchase> history;
        try {
            history = sys_mangaer_accese.usecase6_4A_WatchPurchesHistoryofUser(username, paasword, msg.getName());
        } catch (Exception e){
            history = new LinkedList<>();
        }

        if(history != null){
            return new UserPurchaseListResponse((byte)-1,msg.getId(), history);
        }

        return new NackMessage(msg.getId());
    }

    public Message accept(HistoryOfStoreMessage msg) {
        List<StorePurchase> history;
        try {
            history = sys_mangaer_accese.usecase6_4B_WatchPurchesHistoryofStore(username, paasword, msg.getName());
        } catch (Exception e){
            history = new LinkedList<>();
        }

        if(history != null){
             return new StorePurchaseListResponse((byte)-1,msg.getId(), history);
        }

        return new NackMessage(msg.getId());
    }

    public Message accept(ViewMemberQustionsMessage msg) {
        List<Question> lst =  manager_accese.usecase4_9_ViewMembersQuestions(username, paasword, msg.getStorename());

        if(lst == null)
            return new NackMessage(msg.getId());

        return new QustionListResponse((byte)-1, msg.getId(), lst);
    }

    public Message accept(Response2QuestionMessage msg) {

        if(manager_accese.usecase4_9_RespondToQuestion(username, paasword, msg.getAnswer(), msg.getQustionID())){
            return new AckMessage(msg.getId());
        }

        return new NackMessage(msg.getId());
    }

    public Message accept(ViewAquisitionMessage msg) {
        List<StorePurchase> lst = manager_accese.usecase4_10_ViewAcquisitionHistory(username, paasword, msg.getStorename());

        if(lst == null){
            return new NackMessage(msg.getId());
        }

        return new StorePurchaseListResponse((byte)-1, msg.getId(), lst);
    }

    public Message accept(PurchaseMessage msg) {
        CreditCard card = new CreditCard(msg.getCreditcardNumber(), msg.geteDate(), msg.getCss(), msg.getOwner(), msg.getOwnerID());
        userAddress adress = new userAddress(msg.getCountry(), msg.getCity(), msg.getShipAdress(), msg.getZip(), msg.getShipReciver());

        if(guest_accese.usecase2_8_Purchase_products(gustID, card, adress)){
            return new AckMessage(msg.getId());
        }

        return new NackMessage(msg.getId());
    }

    public Message accept(CreateProductMessage msg) {

        ProductDetails details = new ProductDetails(msg.getName(), msg.getCategoories(), msg.getKeywords(), msg.getStoreName(), msg.getAmmount(),msg.getPrice());

        if(owner_accese.usecase4_1_1_AddingProdacsToStore(username, paasword, msg.getStoreName(), details)){
            return new AckMessage(msg.getId());
        }

        return new NackMessage(msg.getId());
    }

    public Message accept(AppointMessage msg) {

        if(msg.getRole() == SubInstructions.usecase4_5_appointManager_code){
            if(owner_accese.usecase4_5_appointManager(gustID,msg.getStorename(),msg.getUsername())){
                return new AckMessage(msg.getId());
            }
        }

        if(msg.getRole() == SubInstructions.usecase4_3_appointOwner_code){
            if(owner_accese.usecase4_3_appointOwner(gustID,msg.getStorename(),msg.getUsername())){
                return new AckMessage(msg.getId());
            }
        }

        return new NackMessage(msg.getId());
    }

    public Message accept(FireMessage msg) {
        if(owner_accese.usecase4_7_FireManager(username, paasword, msg.getStoreName(), msg.getUsername())){
            return new AckMessage(msg.getId());
        }

        return new NackMessage(msg.getId());
    }

    public Message accept(RemoveProductMessage msg) {
        if(owner_accese.usecase4_1_2_RemoveItem(username, paasword, msg.getStoreName(), msg.getProductName())){
            return new AckMessage(msg.getId());
        }

        return new NackMessage(msg.getId());
    }

    public Message accept(Add2ProductMessage msg) {
        ProductDetails details = guest_accese.searchProductByName(msg.getProduct(), msg.getStore());
        details.setAmount(details.getAmount() + msg.getAmmount());
        //Product product = new Product(details);

        if(owner_accese.usecase4_1_3_EditProduct(username, paasword, msg.getStore(), msg.getProduct(), details)){
            return new AckMessage(msg.getId());
        }

        return new NackMessage(msg.getId());
    }

    public Message accept(ViewOwnedStoresMessage msg) {
        List<String> stores = owner_accese.ownStores(gustID);

        if(stores != null)
            return new StoreListResponse((byte)-1, msg.getId(), stores);

        return  new NackMessage(msg.getId());
    }

    public Message accept(EditPermitionsMessage msg) {

        if(owner_accese.usecase4_6_editMangagerPermesions(username, paasword, msg.getStoreName(), msg.getManagerName(), msg.getPermitions())){
            return new AckMessage(msg.getId());
        }

        return new NackMessage(msg.getId());
    }

    public Message accept(memberTypeMessage msg) {
        List<String> roles = member_accese.getRoles(gustID);
        String manager = "manager", owner = "owner", systemManager = "systemManager";
        String highestRole = "regular";

        if(roles == null){
            return new NackMessage(msg.getId());
        }

        if(roles.contains(manager)){
            highestRole = manager;
        }

        if(roles.contains(owner) || roles.contains("creator")){
            highestRole = owner;
        }

        if(roles.contains(systemManager)){
            highestRole = systemManager;
        }

        switch (highestRole){
            case "regular"          : return new memberTypeResponse(Opcodes.UserType.regular        , msg.getId());
            case "manager"          : return new memberTypeResponse(Opcodes.UserType.storeManager   , msg.getId());
            case "owner"            : return new memberTypeResponse(Opcodes.UserType.storeOwner     , msg.getId());
            case "systemManager"    : return new memberTypeResponse(Opcodes.UserType.systemManager  , msg.getId());
            default                 : return new NackMessage(msg.getId());
        }
    }

    public Message accept(AcceptPendingAppointmentMessage msg) {

        if(owner_accese.accecpt_Pending_Appointment(gustID, msg.getStoreName(), msg.getAppointe())){
            return new AckMessage(msg.getId());
        }

        return new NackMessage(msg.getId());
    }

    public Message accept(GetPendingAppointments msg) {
        Collection<String> appintees = owner_accese.get_Pending_Appointment(gustID, msg.getStorename());

        if(appintees == null){
            return new NackMessage(msg.getId());
        }

        List<String> appinteeslst = new LinkedList<>(appintees);

        return new StringListResponse(msg.getId(), appinteeslst);
    }

    public Message accept(CreateDiscountMessage msg) {
       if( owner_accese.usecase4_2_AddDiscount(username, paasword, msg.getStore(), msg.getDiscount())){
           return new AckMessage(msg.getId());
       }

        return new NackMessage(msg.getId());
    }

    public Message accept(GetDiscountMessage msg) {
        String answer = owner_accese.usecase4_2_GetDiscount(username, paasword, msg.getStoreName());

        if(answer == null || answer.equals("")){
            return new NackMessage(msg.getId());
        }

        return new StringResponse((byte)-1, msg.getId(), answer);
    }

    public Message accept(RemoveDiscountMessage msg) {
        if(owner_accese.usecase4_2_RemoveDiscount(username, paasword, msg.getStoreName(), msg.getDiscountID())){
            return new AckMessage(msg.getId());
        }

        return new NackMessage(msg.getId());
    }

    public Message accept(GetAcquisitionsMessage msg) {
        String answer = owner_accese.usecase4_2_GetAcquisition(username, paasword, msg.getStoreName());

        if(answer == null || answer.equals("")){
            return new NackMessage(msg.getId());
        }

        return new StringResponse((byte)-1, msg.getId(), answer);
    }

    public Message accept(RemoveAcquisitionMessage msg) {
        if(owner_accese.usecase4_2_RemoveAcquisition(username, paasword, msg.getStoreName(), msg.getAcquisitionID())){
            return new AckMessage(msg.getId());
        }

        return new NackMessage(msg.getId());
    }

    public Message accept(AddAcquisitionMessage msg) {
        if( owner_accese.usecase4_2_AddAcquisition(username, paasword, msg.getStoreName(), msg.getAcquisition())){
            return new AckMessage(msg.getId());
        }

        return new NackMessage(msg.getId());
    }

    public Message accept(FilterMessage msg) {
        List<ProductDetails> prods = null;

        switch (msg.getType()){
            case byPrice: {
                prods = guest_accese.usecase2_5D_1_FilterbyPrice(msg.getMin(), msg.getMax(), msg.getProducts());
                break;
            }
            case byRating: {
                prods = guest_accese.usecase2_5D_2_FilterbyRating((int)msg.getMin(), (int)msg.getMax(), msg.getProducts());
                break;
            }
            case byStoreRating: {
                prods = guest_accese.usecase2_5D_4_FilterbyStoreRating((int)msg.getMin(), (int)msg.getMax(), msg.getProducts());
                break;
            }
        }

        if(prods == null){
            return new NackMessage(msg.getId());
        }

        return new ProductDetailsListResponse(msg.getId(), prods);
    }

    public Message accept(getManagerPermitions msg) {

        List<String> perms = owner_accese.usecase4_6_getMangagerPermesions(username, paasword, msg.getStore(), username);
        LinkedList<Byte> opcodedPerms = new LinkedList<>();
        Permitions permitions = new Permitions();

        if (perms != null) {
            permitions.permesions.forEach((op, text) ->{
                if(perms.contains(text)){
                    opcodedPerms.offer(op);
                }
            });

            return new ByteArrayResponse((byte)-1, msg.getId(), opcodedPerms);
        }

        return new NackMessage(msg.getId());
    }

    public Message accept(ViewSystemStateMessage msg) {

        try {
            if(systemStateViewr != null){
                systemStateViewr.interrupt();
                systemStateViewr = null;
            }
        }catch (Exception e){}


        systemStateViewr = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()){
                String state = sys_mangaer_accese.usecase4_SystemStateString(username, paasword);

                if(state != null) {
                    server.send(this, new StringResponse((byte)1, state));
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        systemStateViewr.start();

        return new AckMessage(msg.getId());
    }

    public Message accept(EndViewSystemStateMessage msg) {
        try {
            if(systemStateViewr != null){
                systemStateViewr.interrupt();
                systemStateViewr = null;
                return new AckMessage(msg.getId());
            }
            return new NackMessage(msg.getId());
        } catch (Exception e){
            return new NackMessage(msg.getId());
        }
    }
}
