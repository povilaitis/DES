import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class CFBCode {
    private static final String ALGORITHM = "DES";
    public static String encrypt(String plaintext, String key, String iv) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/CFB/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), ALGORITHM);
        IvParameterSpec ivParam = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParam);
        byte[] encrypted = cipher.doFinal(plaintext.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypt(String ciphertext, String key, String iv) throws Exception {
        byte[] decoded = Base64.getDecoder().decode(ciphertext);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance("DES/CFB/PKCS5Padding");
        IvParameterSpec ivParam = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParam);
        byte[] decrypted = cipher.doFinal(decoded);
        return new String(decrypted, "UTF-8");
    }


}