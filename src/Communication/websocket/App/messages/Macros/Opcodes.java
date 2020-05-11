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
    public static final byte StoreDetails = 4;
    public static final byte StoreProducts = 5;
    public static final byte ProductDetails = 6;
    public static final byte SearchProductByName = 7;
    public static final byte SearchProductBycategory = 8;
    public static final byte SearchProductByKeyword = 9;
    public static final byte FilterByPrice = 10;
    public static final byte FilterByRating = 11;
    public static final byte FilterByCategory = 12;
    public static final byte FilterByStoreRating = 13;
    public static final byte Save2Basket = 14;
    public static final byte ProductsInCarts = 15;
    public static final byte RemoveFromCart = 16;
    public static final byte ShopingCart = 17;
    public static final byte IsInBasket = 18;
    public static final byte EmptyCart = 19;
    public static final byte DoesStoreHaveItem = 20;


    //member
    public static final byte Logout = 21;
    public static final byte OpenStore = 22;
    public static final byte PurchasesHistory = 23;

    //owner
    public static final byte AddProduct2Store = 24;
    public static final byte RemoveItem = 25;
    public static final byte EditProduct = 26;
    public static final byte Appoint = 27;



}
