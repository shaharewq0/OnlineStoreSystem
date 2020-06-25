package tests.StressTests.Tools;

import java.nio.charset.Charset;
import java.util.Random;

public  class RandomStringGenerator {
    public static String randomString(int length) {
        byte[] array = new byte[length];
        new Random().nextBytes(array);
        return new String(array, Charset.forName("UTF-8"));
    }
}
