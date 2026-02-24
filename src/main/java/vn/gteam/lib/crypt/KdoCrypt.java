package vn.gteam.lib.crypt;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KdoCrypt {
    public static String encrypt(String plainText){
        List<String> chars = KdoCrypt.getRandomFullChars();
        String[] keys = KdoCrypt.getRandomTenChars(chars);
        String[] splits = KdoCrypt.getRandomTenChars(chars);

        String result = "";
        byte[] bytes = plainText.getBytes(StandardCharsets.US_ASCII);

        for (int i = 0; i < bytes.length; i++){
            result += (bytes[i] + i) + splits[i % splits.length];
        }

        for (int i = 0; i < 10; i++){
            result = result.replace(String.valueOf(i), keys[i % keys.length]);
        }
        String textKey = "";
        for (String key : keys) {
            textKey += key;
        }

        String textSplit = "";
        for (String split : splits){
            textSplit += split;
        }
        result = textKey + result + textSplit;
        return result;
    }


    private static List<String> getRandomFullChars(){
        List<String> keyChars = new ArrayList<>();
        for (int i = 'A'; i <= 'Z'; i++){
            keyChars.add(String.valueOf((char) i));
        }
        Collections.shuffle(keyChars);
        return keyChars;
    }

    private static String[] getRandomTenChars(List<String> chars){
        String[] keys = new String[10];
        for (int i = 0; i < keys.length; i++){
            keys[i] = chars.remove(0);
        }
        return keys;
    }

}
