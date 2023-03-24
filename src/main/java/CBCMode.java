import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CBCMode {

    private static final String ALGORITHM = "DES";
    private static final String TRANSFORMATION = "DES/CBC/PKCS5Padding";

    public static String encrypt(String plaintext, String key, String iv) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), ALGORITHM);
        IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        byte[] encrypted = cipher.doFinal(plaintext.getBytes());

        return bytesToHex(encrypted);
    }

    public static String decrypt(String ciphertext, String key, String iv) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), ALGORITHM);
        IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

        byte[] decrypted = cipher.doFinal(hexToBytes(ciphertext));

        return new String(decrypted);
    }


    //nepavyko, teko daryt su hex
    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

    private static byte[] hexToBytes(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i+1), 16));
        }
        return data;
    }
}
