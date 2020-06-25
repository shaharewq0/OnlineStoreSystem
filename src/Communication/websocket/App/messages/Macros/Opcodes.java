package Communication.websocket.App.messages.Macros;

public class Opcodes {

    //For testing
    public static final byte Demo = 0x1;

    //Not in te usecases, but needed for implementation
    public static final byte viewOwnedStores    = 0x7;
    public static final byte memberType         = 0x70;



    // ack nack
    public static final byte Ack    = 0x2;
    public static final byte Nack   = 0x3;


    //Gusts
    public static final byte Register                   = 0x22;
    public static final byte Login                      = 0x23;
    public static final byte StoreDetails               = 0x14;
    public static final byte StoreProducts              = 0x15;
    public static final byte SearchProductByName        = 0x16;
    public static final byte SearchProductBycategory    = 0x17;
    public static final byte SearchProductByKeyword     = 0x18;
    public static final byte FilterByPrice              = 0x04;
    public static final byte FilterByRating             = 0x05;
    public static final byte FilterByStoreRating        = 0x06;
    public static final byte Save2Basket                = 0x26;
    public static final byte ProductsInCarts            = 0x27;
    public static final byte RemoveFromCart             = 0x2a;
    public static final byte Purches                    = 0x28;


    //member
    public static final byte Logout             = 0x31;
    public static final byte OpenStore          = 0x32;
    public static final byte PurchasesHistory   = 0x37;

    //manager
    public static final byte ViewMemberQustions     = 0x49;
    public static final byte Response2Qustion       = 0x4f;
    public static final byte viewAquisitionHistory  = 0x4a;
    public static final byte createDiscount         = 0x66;
    public static final byte getDiscounts           = 0x67;
    public static final byte deleteDiscount         = 0x68;

    //owner
    public static final byte AddProduct2Store           = 0x41;
    public static final byte RemoveItem                 = 0x42;
    //public static final byte EditProduct                = 0;
    public static final byte Add2Product                = 0x44;
    public static final byte Appoint                    = 0x43;
    public static final byte editMangagerPermesions     = 0x46;
    public static final byte getEditMangagerPermesions  = 0x7a;
    public static final byte FireManager                = 0x47;
    public static final byte AcceptPendingAppintment    = 0x4c;
    public static final byte PendingAppountments        = 0x4d;
    public static final byte getAcquisitions            = 0x40;
    public static final byte removeAcquisition          = 0x45;
    public static final byte addAcquisitions            = 0x48;


    //System manager
    public static final byte HistoryOfUser  = 0x64;
    public static final byte HistoryOfStore =  0x6b;
    public static final byte viewsSytsemState =  0x6c;
    public static final byte endViewsSytsemState =  0x6d;




    // opcode for userTypeResponseMessage
    public class UserType{
        public static final byte systemManager  = 0x71;
        public static final byte storeOwner     = 0x72;
        public static final byte storeManager   = 0x73;
        public static final byte regular        = 0x74;
    }
}
