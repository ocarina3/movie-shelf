package utils;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptPassword {

    /**
     * Deixa a senha encriptada para que não seja possivel ver a senha do usuario.
     * */
    public static String encryptPassword(String salt, String basePassword) {

        try {

            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            byte[] messageDigestSalt = algorithm.digest(salt.getBytes(StandardCharsets.UTF_8));
            byte[] messageDigestPassword = algorithm.digest(basePassword.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexStringSalt = new StringBuilder();
            for (byte b : messageDigestSalt) {
                hexStringSalt.append(String.format("%02X", 0xFF & b));
            }

            StringBuilder hexStringPassword = new StringBuilder();
            for (byte b : messageDigestPassword) {
                hexStringPassword.append(String.format("%02X", 0xFF & b));
            }

            return hexStringSalt.toString() + hexStringPassword.toString();

        } catch (NoSuchAlgorithmException e) {
            return e.getMessage();
        }
    }

}
