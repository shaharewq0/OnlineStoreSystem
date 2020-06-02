package Communication.websocket.App.api_impl;

import Communication.websocket.App.messages.Objects.client2server.*;
import Communication.websocket.App.messages.Objects.server2client.*;
import Communication.websocket.App.messages.api.Client2ServerMessage;
import Communication.websocket.api.MessagingProtocol;
import Communication.websocket.App.messages.api.Message;
import Domain.Notifier.Notifier;
import Domain.RedClasses.UserPurchase;
import Domain.Store.Product;
import Domain.Store.StorePurchase;
import Domain.info.ProductDetails;
import Domain.info.Question;
import Domain.info.StoreInfo;
import Service_Layer.guest_accese.guest_accese;
import Service_Layer.manager_accese.manager_accese;
import Service_Layer.member_accese.member_accese;
import Service_Layer.owner_accese.owner_accese;
import Service_Layer.sys_manager_accese.sys_mangaer_accese;
import extornal.payment.CreditCard;
import tests.AcceptanceTests.auxiliary.StoreDetails;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

class SubInstructions {

     static final int usecase4_5_appointManager_code = 10;
     static final int usecase4_3_appointOwner_code = 11;
}



public class MallProtocol implements MessagingProtocol<Message>, Observer {

    private int gustID;

    private String username;
    private String paasword;

    public MallProtocol() {
        this.gustID = guest_accese.ImNew();
        username = "";
        paasword = "";
        Notifier.getInstance().addObserver(this); // register to the notifier
    }



    @Override
    public Message process(Message msg) {
        System.out.println("handling :" + msg.toString());
        return ((Client2ServerMessage)msg).visit(this);
    }

    @Override
    public void update(Observable o, Object arg) {

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
        if(guest_accese.usecase2_3_login(gustID, msg.getUsername(), msg.getPassword())){
            username = msg.getUsername();
            paasword = msg.getPassword();

            return new AckMessage(msg.getId());
        }
        else {
            return new NackMessage(msg.getId());
        }
    }

    public Message accept(LogoutMessage msg) {
        member_accese.usecase3_1_Logout(gustID);
        username = "";
        paasword = "";
        gustID = -777;

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

        if(products != null) {
            return new ProductDetailsListResponse(msg.getId(), products);
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
        List<UserPurchase> history = sys_mangaer_accese.usecase6_4A_WatchPurchesHistoryofUser(username, paasword, msg.getName());

        if(history != null){
            return new UserPurchaseListResponse((byte)-1,msg.getId(), history);
        }

        return new NackMessage(msg.getId());
    }

    public Message accept(HistoryOfStoreMessage msg) {
        List<StorePurchase> history = sys_mangaer_accese.usecase6_4B_WatchPurchesHistoryofStore(username, paasword, msg.getName());

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
        CreditCard card = new CreditCard(msg.getCreditcardNumber(), msg.geteDate(), msg.getCss(), msg.getOwner());

        if(guest_accese.usecase2_8_Purchase_products(gustID, card, msg.getShipAdress())){
            return new AckMessage(msg.getId());
        }

        return new NackMessage(msg.getId());
    }

    public Message accept(CreateProductMessage msg) {

        ProductDetails details = new ProductDetails(msg.getName(), msg.getCategoories(), msg.getKeywords(), msg.getStoreName(), msg.getAmmount(),msg.getPrice());
        Product prod = new Product(details);

        if(owner_accese.usecase4_1_1_AddingProdacsToStore(username, paasword, msg.getStoreName(), prod)){
            return new AckMessage(msg.getId());
        }

        return new NackMessage(msg.getId());
    }

    public Message accept(AppointMessage msg) {

        if(msg.getRole() == SubInstructions.usecase4_5_appointManager_code){
            if(owner_accese.usecase4_5_appointManager(username, paasword,msg.getStorename(),msg.getUsername())){
                return new AckMessage(msg.getId());
            }
        }

        if(msg.getRole() == SubInstructions.usecase4_3_appointOwner_code){
            if(owner_accese.usecase4_3_appointOwner(username, paasword,msg.getStorename(),msg.getUsername())){
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
        Product product = new Product(details);

        if(owner_accese.usecase4_1_3_EditProduct(username, paasword, msg.getStore(), msg.getProduct(), product)){
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
}
