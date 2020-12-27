package priv.shuang.jeepracticum.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Jonathan
 */
public class PasswordEncrypt {
    public static String encrypt(String pwd) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(pwd.getBytes());
            return new BigInteger(1, md.digest()).toString(32);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
