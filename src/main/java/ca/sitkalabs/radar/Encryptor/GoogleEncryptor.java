package ca.sitkalabs.radar.Encryptor;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

public class GoogleEncryptor implements Encryptor {

    public byte[] publicKeyHex;

    public GoogleEncryptor(String publicKey) {
        publicKeyHex = Base64.getDecoder().decode(publicKey);
    }

    @Override
    public String encrypt(String plaintext) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        // First of all, let's convert Google login public key from base64
        // to PublicKey, and then calculate SHA-1 of the key:

        // 2. Calculating the first BigInteger
        int i = readInt(publicKeyHex, 0);
        byte [] half = new byte[i];
        System.arraycopy(publicKeyHex, 4, half, 0, i);
        BigInteger firstKeyInteger = new BigInteger(1, half);

        // 3. Calculating the second BigInteger
        int j = readInt(publicKeyHex, i + 4);
        half = new byte[j];
        System.arraycopy(publicKeyHex, i + 8, half, 0, j);
        BigInteger secondKeyInteger = new BigInteger(1, half);

        // 4. Let's calculate SHA-1 of the public key, and put it to signature[]:
        // signature[0] = 0 (always 0!)
        // signature[1...4] = first 4 bytes of SHA-1 of the public key
        byte[] sha1 = MessageDigest.getInstance("SHA-1").digest(publicKeyHex);

        byte[] signature = new byte[5];
        signature[0] = 0;
        System.arraycopy(sha1, 0, signature, 1, 4);

        // 5. Use the BigInteger's (see calculations above) to generate
        // a PublicKey object
        PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(firstKeyInteger, secondKeyInteger));

        // It's time to encrypt our password:
        // 1. Let's create Cipher:
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA1ANDMGF1PADDING");

        // 3. Then converting the string to bytes
        byte[] plain = plaintext.getBytes(StandardCharsets.UTF_8);

        // 4. and encrypt the bytes with the public key:
        cipher.init(Cipher.PUBLIC_KEY, publicKey);
        byte[] encrypted = cipher.doFinal(plain);

        // 5. Add the result to a byte array output[] of 133 bytes length:
        // output[0] = 0 (always 0!)
        // output[1...4] = first 4 bytes of SHA-1 of the public key
        // output[5...132] = encrypted login+password ("\u0000" is used as a separator)
        byte[] output = new byte [133];
        System.arraycopy(signature, 0, output, 0, signature.length);
        System.arraycopy(encrypted, 0, output, signature.length, encrypted.length);

        // Done! Just encrypt the result as base64 string and return it
        return Base64.getUrlEncoder().withoutPadding().encodeToString(output);
    }

    // Aux. method, it takes 4 bytes from a byte array and turns the bytes to int
    private static int readInt(byte[] arrayOfByte, int start) {
        return (0xFF & arrayOfByte[start]) << 24 | (0xFF & arrayOfByte[(start + 1)]) << 16 | (0xFF & arrayOfByte[(start + 2)]) << 8 | 0xFF & arrayOfByte[(start + 3)];
    }

    @Override
    public String decrypt(String ciphertext) {
        return ciphertext;
    }
}
