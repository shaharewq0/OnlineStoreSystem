package Communication.websocket.Echo.encodedecode;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class twoWordsrEncoder  implements Encoder.Text<twoWords> {
    @Override
    public String encode(twoWords words) throws EncodeException {
        return words.getWord1() + '\n' + words.getWord2();
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
