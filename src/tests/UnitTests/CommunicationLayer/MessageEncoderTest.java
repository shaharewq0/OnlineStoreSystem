package tests.UnitTests.CommunicationLayer;

import Communication.websocket.App.EncoderDecoder.MessageEncoder;
import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.App.messages.Objects.server2client.AckMessage;
import Communication.websocket.App.messages.Objects.server2client.NackMessage;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.websocket.EncodeException;

public class MessageEncoderTest {

    private MessageEncoder enc;
    private JSONObject json;

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

        int id = 15;

        String encoded = enc.encode((new AckMessage(id)));
        json.put("cmd_id", id);
        json.put("result", Opcodes.Ack);

        Assert.assertEquals(json.toString() ,encoded);
    }

    @Test
    public void encode_nack() throws EncodeException {

        int id = 42;

        String encoded = enc.encode((new NackMessage(id)));
        json.put("cmd_id", id);
        json.put("result", Opcodes.Nack);

        Assert.assertEquals(json.toString() ,encoded);
    }
}
