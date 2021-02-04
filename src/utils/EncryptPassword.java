package utils;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptPassword {

    public static void encryptPassword(String args []) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        //-------------- Senha Admin
        String senhaAdmin = "admin";

        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte messageDigestSenhaAdmin[] = algorithm.digest(senhaAdmin.getBytes("UTF-8"));

        StringBuilder hexStringSenhaAdmin = new StringBuilder();
        for (byte b : messageDigestSenhaAdmin) {
            hexStringSenhaAdmin.append(String.format("%02X", 0xFF & b));
        }
        String senhahexAdmin = hexStringSenhaAdmin.toString();

        System.out.println(senhahexAdmin);
    }

}
