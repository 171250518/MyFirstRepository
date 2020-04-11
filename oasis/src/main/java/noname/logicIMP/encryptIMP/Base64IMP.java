package noname.logicIMP.encryptIMP;


import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64IMP{
    private static Base64.Decoder decoder = Base64.getDecoder();
    private static Base64.Encoder encoder = Base64.getEncoder();

    public static String encode(String msg) {
        final byte[] textByte = msg.getBytes(StandardCharsets.UTF_8);
        return encoder.encodeToString(textByte);
    }

    static String decode(String msg) {
        return new String(decoder.decode(msg), StandardCharsets.UTF_8);
    }

}
