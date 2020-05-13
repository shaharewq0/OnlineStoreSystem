package Communication.websocket.App.messages.Macros;

public class Opcodes {

    //For testing
    public static final byte Demo = 1;

    // ack nack
    public static final byte Ack = 0x2;
    public static final byte Nack = 0x3;


    //Gusts
    public static final byte Register = 0x22;
    public static final byte Login = 0x23;
    public static final byte StoreDetails = 0x14;
    public static final byte StoreProducts = 0x15;
    public static final byte SearchProductByName = 0x16;
    public static final byte SearchProductBycategory = 0x17;
    public static final byte SearchProductByKeyword = 0x18;
    public static final byte FilterByPrice = 10;
    public static final byte FilterByRating = 11;
    public static final byte FilterByCategory = 12;
    public static final byte FilterByStoreRating = 13;
    public static final byte Save2Basket = 0x26;
    public static final byte ProductsInCarts = 0x27;
    public static final byte RemoveFromCart = 0x2a;
    public static final byte ShopingCart = 17;
    public static final byte IsInBasket = 18;
    public static final byte EmptyCart = 19;
    public static final byte DoesStoreHaveItem = 20;


    //member
    public static final byte Logout = 0x31;
    public static final byte OpenStore = 0x32;
    public static final byte PurchasesHistory = 0x37;

    //owner
    public static final byte AddProduct2Store = 24;
    public static final byte RemoveItem = 25;
    public static final byte EditProduct = 26;
    public static final byte Appoint = 27;

    //System manager
    public static final byte HistoryOfUser = 0x64;
    public static final byte HistoryOfStore =  0x6b;



}
