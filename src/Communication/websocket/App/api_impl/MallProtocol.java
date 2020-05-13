package Communication.websocket.App.api_impl;

import Communication.websocket.App.messages.Objects.client2server.*;
import Communication.websocket.App.messages.Objects.server2client.*;
import Communication.websocket.App.messages.api.Client2ServerMessage;
import Communication.websocket.api.MessagingProtocol;
import Communication.websocket.App.messages.api.Message;
import Domain.Store.Purchase;
import Domain.info.ProductDetails;
import Domain.info.StoreInfo;
import Service_Layer.guest_accese.guest_accese;
import Service_Layer.member_accese.member_accese;
import Service_Layer.sys_manager_accese.sys_mangaer_accese;
import tests.AcceptanceTests.auxiliary.StoreDetails;

import java.util.List;

public class MallProtocol implements MessagingProtocol<Message> {

    private int gustID;

    private String username;
    private String paasword;

    private guest_accese guest;
    private member_accese memeber;
    private sys_mangaer_accese manager;

    public int getGustID() {
        return gustID;
    }

    public MallProtocol(int gustID) {
        this.gustID = gustID;
        username = "";
        paasword = "";
        guest = new guest_accese();
        memeber = new member_accese();
        manager = new sys_mangaer_accese();
    }



    @Override
    public Message process(Message msg) {
        System.out.println("handling :" + msg.toString());
        return ((Client2ServerMessage)msg).visit(this);
    }







    public Message accept(DemoMessage msg){
        return new NackMessage(msg.getId());
    }

    public Message accept(RegisterMessage msg){

        if(guest.usecase2_2_guest_register(msg.getUsername(), msg.getPassword())){
            return new AckMessage(msg.getId());
        }

        return new NackMessage(msg.getId());
    }

    public Message accept(LoginMessage msg){
        if(guest.usecase2_3_login(msg.getUsername(), msg.getPassword())){
            username = msg.getUsername();
            paasword = msg.getPassword();

            return new AckMessage(msg.getId());
        }
        else {
            return new NackMessage(msg.getId());
        }
    }

    public Message accept(LogoutMessage msg) {
        memeber.usecase3_1_Logout(username, paasword);
        username = "";
        paasword = "";

        return new AckMessage(msg.getId());
    }

    public Message accept(StorDetailsMessage msg) {
        StoreInfo detils = guest.usecase2_4A_getStoreDetails(msg.getName());

        if(detils != null) {
            return new StorDetailsResponseMessage(msg.getId(), detils.name, detils.address, (byte) detils.rating);
        }

        return new NackMessage(msg.getId());
    }

    public Message accept(StoreProductsMessage msg) {
        StoreInfo detils = guest.usecase2_4B_getStoreProdacts(msg.getName());

        if(detils != null) {
            return new StoreProductsResponseMessage(msg.getId(), detils.products);
        }

        return new NackMessage(msg.getId());
    }

    public Message accept(ProductsByNameMessage msg) {
        List<ProductDetails> products = guest.usecase2_5A_searchProductByName(msg.getName());

        if(products != null) {
            return new ProductDetailsListResponse(msg.getId(), products);
        }

        return new NackMessage(msg.getId());
    }

    public Message accept(ProductsByCategoryMessage msg) {
        List<ProductDetails> products = guest.usecase2_5B_searchProductByCategory(msg.getCategory());

        if(products != null) {
            return new ProductDetailsListResponse(msg.getId(), products);
        }

        return new NackMessage(msg.getId());
    }

    public Message accept(ProductsByKeywordMessage msg) {
        List<ProductDetails> products = guest.usecase2_5C_searchProductByKeyword(msg.getKeyword());

        if(products != null) {
            return new ProductDetailsListResponse(msg.getId(), products);
        }

        return new NackMessage(msg.getId());
    }

    public Message accept(AddProduct2BasketMessage msg) {
        if(guest.usecase2_6_saveProductToBasket(gustID, msg.getStore(), msg.getProduct(), msg.getAmount())){
            return new AckMessage(msg.getId());
        }

        return new NackMessage(msg.getId());
    }

    public Message accept(ViewCartMessage msg) {
        List<ProductDetails> products = guest.usecase2_7A_WatchProdactsInCart(gustID);

        if(products != null) {
            return new ProductDetailsListResponse(msg.getId(), products);
        }

        return new NackMessage(msg.getId());
    }

    public Message accept(RemoveProductFromCartMessage msg) {
        if(guest.usecase2_7b_RemoveProdactsInCart(gustID, msg.getStore(), msg.getProduct(), msg.getAmount()) > 0){
            return new AckMessage(msg.getId());
        }

        return new NackMessage(msg.getId());
    }

    public Message accept(OpenStoreMessage msg) {
        if(memeber.usecase3_2_OpenStore(username, paasword, new StoreDetails(msg.getName(), msg.getAddres()))){
            return new AckMessage(msg.getId());
        }

        return new NackMessage(msg.getId());
    }

    public Message accept(ViewPerchesHistory msg) {
        List<Purchase> history = memeber.usecase3_7_ReviewPurchasesHistory(username, paasword);

        if(history != null){
            return new PerchesListResponse((byte)-1,msg.getId(), history);
        }

        return new NackMessage(msg.getId());
    }

    public Message accept(HistoryOfUserMessage msg) {
        List<Purchase> history = manager.usecase6_4A_WatchPurchesHistoryofUser(username, paasword, msg.getName());

        if(history != null){
            return new PerchesListResponse((byte)-1,msg.getId(), history);
        }

        return new NackMessage(msg.getId());
    }

    public Message accept(HistoryOfStoreMessage msg) {
        List<Purchase> history = manager.usecase6_4B_WatchPurchesHistoryofStore(username, paasword, msg.getName());

        if(history != null){
            return new PerchesListResponse((byte)-1,msg.getId(), history);
        }

        return new NackMessage(msg.getId());
    }
}
