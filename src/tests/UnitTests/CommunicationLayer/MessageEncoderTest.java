package tests.UnitTests.CommunicationLayer;

import Communication.websocket.App.EncoderDecoder.MessageEncoder;
import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.App.messages.Objects.server2client.*;
import Communication.websocket.App.messages.api.Message;
import Domain.info.ProductDetails;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.websocket.EncodeException;
import java.util.LinkedList;
import java.util.List;

public class MessageEncoderTest {

    private MessageEncoder enc;
    private JSONObject json;

    int id = 42;
    byte op = -1;

    @Before
    public void setUp() {
        enc = new MessageEncoder();
        json = new JSONObject();
    }

    @After
    public void tearDown() {
        enc = null;
        json = null;
    }

    @Test
    public void encode_ack() throws EncodeException {

        String encoded = enc.encode((new AckMessage(id)));
        json.put("cmd_id", id);
        json.put("result", Opcodes.Ack);

        Assert.assertEquals(json.toString() ,encoded);
    }

    @Test
    public void encode_nack() throws EncodeException {

        String encoded = enc.encode((new NackMessage(id)));
        json.put("cmd_id", id);
        json.put("result", Opcodes.Nack);

        Assert.assertEquals(json.toString() ,encoded);
    }


    @Test
    public void memberTypeResponse() {
        test_runner("114", new memberTypeResponse((byte) 114, id));
    }

    @Test
    public void StringResponse() {
        test_runner("{\"22\":97,\"44\":125,\"23\":109,\"24\":111,\"25\":117,\"26\":110,\"27\":116,\"28\":32,\"29\":111,\"30\":102,\"31\":32,\"10\":110,\"32\":39,\"11\":123,\"33\":98,\"12\":10,\"34\":97,\"13\":9,\"35\":108,\"14\":48,\"36\":108,\"15\":46,\"37\":39,\"16\":32,\"38\":32,\"17\":77,\"39\":105,\"18\":105,\"19\":110,\"0\":65,\"1\":99,\"2\":113,\"3\":117,\"4\":105,\"5\":115,\"6\":105,\"7\":116,\"8\":105,\"9\":111,\"40\":115,\"41\":32,\"20\":46,\"42\":53,\"21\":32,\"43\":10}", new StringResponse(id,"Acquisition{\n" +
                "\t0. Min. amount of 'ball' is 5\n" +
                "}"));
    }

    @Test
    public void ProductDetailsListResponse() {
        List<ProductDetails> products = new LinkedList<>();
        products.add(new ProductDetails("ball", new LinkedList<>(), "s1", 10, 3.5));

        test_runner("{\"0\":98,\"1\":97,\"2\":108,\"3\":108,\"4\":2,\"5\":51,\"6\":46,\"7\":53,\"8\":2,\"9\":115,\"10\":49}", new ProductDetailsListResponse( id, products));
    }

    @Test
    public void StoreListResponse() {
        List<String> stores = new LinkedList<>();
        stores.add("s1");

        test_runner("{\"0\":115,\"1\":49}", new StoreListResponse(op, id, stores));
    }

    @Test
    public void StringListResponse() {
        List<String> stores = new LinkedList<>();
        stores.add("s1");

        test_runner("{\"0\":115,\"1\":49}", new StringListResponse(id, stores));
    }

    @Test
    public void ByteArrayResponse() {
        List<Byte> bytes = new LinkedList<>();
        bytes.add((byte)51);
        bytes.add((byte)52);
        bytes.add((byte)53);
        bytes.add((byte)54);
        bytes.add((byte)56);
        bytes.add((byte)57);
        bytes.add((byte)58);
        bytes.add((byte)59);
        bytes.add((byte)60);
        bytes.add((byte)76);
        bytes.add((byte)61);
        bytes.add((byte)77);

        test_runner("{\"0\":51,\"11\":77,\"1\":52,\"2\":53,\"3\":54,\"4\":56,\"5\":57,\"6\":58,\"7\":59,\"8\":60,\"9\":76,\"10\":61}", new ByteArrayResponse(op, id, bytes));
    }













    private void test_runner(String data, Message input) {
        String expected = String.format("{\"result\":%s,\"cmd_id\":%d}",data, id);
        test(expected, input);
    }

    private void test(String expected, Message input){
        try {
            String encoded = enc.encode(input);
            Assert.assertEquals(expected, encoded);
        } catch (EncodeException e) {
            Assert.fail("encoder failed");
        }
    }
}
