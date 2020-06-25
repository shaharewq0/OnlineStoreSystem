package tests.UnitTests.CommunicationLayer;

import Communication.websocket.App.EncoderDecoder.MessageDecoder;
import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.App.messages.Objects.client2server.*;
import Communication.websocket.App.messages.api.Message;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.websocket.DecodeException;
import javax.websocket.EncodeException;


public class MessageDecoderTest {

    private MessageDecoder dec;

    @Before
    public void setUp() {
        dec = new MessageDecoder();
    }

    @After
    public void tearDown() {
        dec = null;
    }

    @Test
    public void encode_Login() throws DecodeException {

        long id = 115;
        String user = "a";
        String pass = "b";
        byte op = Opcodes.Login;


        Assert.assertEquals(new LoginMessage(id, user, pass), dec.decode(String.format("{\"cmd_id\":%d,\"json_data\":{\"0\"%d,\"1\":%d,\"2\":0,\"3\":%d}}",id,op, (byte)user.charAt(0),(byte)pass.charAt(0))));
    }

    @Test
    public void encode_Register() throws DecodeException {

        long id = 115;
        String user = "d";
        String pass = "m";
        byte op = Opcodes.Register;

        Assert.assertEquals(new RegisterMessage(id, user, pass), dec.decode(String.format("{\"cmd_id\":%d,\"json_data\":{\"0\"%d,\"1\":%d,\"2\":0,\"3\":%d}}",id,op, (byte)user.charAt(0),(byte)pass.charAt(0))));
    }

    @Test
    public void encode_Logout() throws DecodeException {

        long id = 33;
        byte op = Opcodes.Logout;

        Assert.assertEquals(new LogoutMessage(id), dec.decode(String.format("{\"cmd_id\":%d,\"json_data\":{\"0\"%d}}",id,op)));
    }

    @Test
    public void RemoveProductFromCartMessage() throws DecodeException {

        long id = 33;
        String sotre = "ball";
        String product = "s1";
        int anmmount = 11;

        test(new RemoveProductFromCartMessage(id, product,sotre, anmmount), "{\"cmd_id\":26,\"json_data\":{\"0\":42,\"1\":115,\"2\":49,\"3\":0,\"4\":98,\"5\":97,\"6\":108,\"7\":108,\"8\":0,\"9\":49,\"10\":49}}");
    }

    @Test
    public void ViewCartMessage() throws DecodeException {

        long id = 33;
        test(new ViewCartMessage(id), "{\"cmd_id\":29,\"json_data\":{\"0\":39}}");
    }

    @Test
    public void AddProduct2BasketMessage() throws DecodeException {

        long id = 33;
        String sotre = "ball";
        String product = "s1";
        int anmmount = 4;

        test(new AddProduct2BasketMessage(id, product,sotre, anmmount), "{\"cmd_id\":3,\"json_data\":{\"0\":38,\"1\":115,\"2\":49,\"3\":0,\"4\":98,\"5\":97,\"6\":108,\"7\":108,\"8\":0,\"9\":52}}");
    }


    @Test
    public void ProductsByKeywordMessage() throws DecodeException {

        long id = 33;
        String key = "round";

        test(new ProductsByKeywordMessage(id, key), "{\"cmd_id\":5,\"json_data\":{\"0\":24,\"1\":114,\"2\":111,\"3\":117,\"4\":110,\"5\":100}}");
    }

    @Test
    public void ViewPerchesHistory() throws DecodeException {

        long id = 33;

        test(new ViewPerchesHistory(id), "{\"cmd_id\":23,\"json_data\":{\"0\":55}}");
    }

    @Test
    public void PurchaseMessage() throws DecodeException {

        long id = 33;

        test(new PurchaseMessage(id, "2341-3413-4123-4123", "12/34","234",  "visitor", "100000000","aaa", "qiriat ono", "qqq", "234"), "{\"cmd_id\":33,\"json_data\":{\"0\":40,\"1\":50,\"2\":51,\"3\":52,\"4\":49,\"5\":45,\"6\":51,\"7\":52,\"8\":49,\"9\":51,\"10\":45,\"11\":52,\"12\":49,\"13\":50,\"14\":51,\"15\":45,\"16\":52,\"17\":49,\"18\":50,\"19\":51,\"20\":0,\"21\":49,\"22\":50,\"23\":47,\"24\":51,\"25\":52,\"26\":0,\"27\":50,\"28\":51,\"29\":52,\"30\":0,\"31\":118,\"32\":105,\"33\":115,\"34\":105,\"35\":116,\"36\":111,\"37\":114,\"38\":0,\"39\":49,\"40\":48,\"41\":48,\"42\":48,\"43\":48,\"44\":48,\"45\":48,\"46\":48,\"47\":48,\"48\":0,\"49\":97,\"50\":97,\"51\":97,\"52\":0,\"53\":113,\"54\":105,\"55\":114,\"56\":105,\"57\":97,\"58\":116,\"59\":32,\"60\":111,\"61\":110,\"62\":111,\"63\":0,\"64\":113,\"65\":113,\"66\":113,\"67\":0,\"68\":50,\"69\":51,\"70\":52}}");
    }

    @Test
    public void AppointMessage() throws DecodeException {

        long id = 33;

        test(new AppointMessage(id, "u3", "s1", (byte) 17), "{\"cmd_id\":11,\"json_data\":{\"0\":67,\"1\":117,\"2\":51,\"3\":0,\"4\":115,\"5\":49,\"6\":0,\"7\":17}}");
    }

    @Test
    public void Add2ProductMessage() throws DecodeException {

        long id = 33;

        test(new Add2ProductMessage(id, "ball", "s1", 9), "{\"cmd_id\":37,\"json_data\":{\"0\":68,\"1\":98,\"2\":97,\"3\":108,\"4\":108,\"5\":0,\"6\":115,\"7\":49,\"8\":0,\"9\":57}}");
    }

    @Test
    public void ViewOwnedStoresMessage() throws DecodeException {

        long id = 33;

        test(new ViewOwnedStoresMessage(id), "{\"cmd_id\":43,\"json_data\":{\"0\":7}}");
    }


    @Test
    public void memberTypeMessage() throws DecodeException {

        long id = 33;

        test(new memberTypeMessage(id), "{\"cmd_id\":44,\"json_data\":{\"0\":112}}");
    }

    @Test
    public void AcceptPendingAppointmentMessage() throws DecodeException {

        long id = 33;

        test(new AcceptPendingAppointmentMessage(id, "s1", "u5"), "{\"cmd_id\":23,\"json_data\":{\"0\":76,\"1\":115,\"2\":49,\"3\":0,\"4\":117,\"5\":53}}");
    }

    @Test
    public void getManagerPermitions() throws DecodeException {

        long id = 33;

        test(new getManagerPermitions(id, "s1", ""), "{\"cmd_id\":31,\"json_data\":{\"0\":122,\"1\":115,\"2\":49}}");
    }









    private void test(Message expected, String input){
        Message encoded = null;

        try {
            encoded = dec.decode(input);
        } catch (Exception e) {
            Assert.fail("decode failed");
        }

        Assert.assertEquals(expected, encoded);
    }
}
