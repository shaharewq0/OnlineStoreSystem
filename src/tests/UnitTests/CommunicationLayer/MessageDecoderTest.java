package tests.UnitTests.CommunicationLayer;

import Communication.websocket.App.EncoderDecoder.MessageDecoder;
import Communication.websocket.App.messages.Macros.Opcodes;
import Communication.websocket.App.messages.Objects.LoginMessage;
import Communication.websocket.App.messages.Objects.LogoutMessage;
import Communication.websocket.App.messages.Objects.RegisterMessage;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.websocket.DecodeException;
import java.nio.ByteBuffer;


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
}
