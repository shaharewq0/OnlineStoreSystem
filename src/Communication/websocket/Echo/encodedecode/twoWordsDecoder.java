package Communication.websocket.Echo.encodedecode;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class twoWordsDecoder implements Decoder.Text<twoWords> {
    @Override
    public twoWords decode(String s) throws DecodeException {
        String[] splited = s.split("\n");

        if(splited.length != 2)
            throw new IllegalArgumentException("not splittable");

        return new twoWords(splited[0], splited[1]);
    }

    @Override
    public boolean willDecode(String s) {
        return s !=null && s.split("\n").length == 2;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
