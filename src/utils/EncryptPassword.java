package utils;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptPassword {

    public static String encryptPassword(String basePassword) {

        try {

            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            byte[] messageDigestPassword = algorithm.digest(basePassword.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexStringPassword = new StringBuilder();
            for (byte b : messageDigestPassword) {
                hexStringPassword.append(String.format("%02X", 0xFF & b));
            }

            return hexStringPassword.toString();

        } catch (NoSuchAlgorithmException e) {
            return e.getMessage();
        }
    }

}
