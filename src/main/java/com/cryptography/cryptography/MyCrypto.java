package com.cryptography.cryptography;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import java.security.*;

// Класс для шифрования внизу его методы
public class MyCrypto {
    public static String getHashString(String message) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(message.getBytes());
        byte[] digest = md.digest();
        return getHexString(digest) + "\n string: " + new String(digest);
    }

    public static String getMacString(String message) throws Exception {
        KeyGenerator KeyGen = KeyGenerator.getInstance("DES");
        SecureRandom sr = new SecureRandom();
        KeyGen.init(sr);
        Key key = KeyGen.generateKey();
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(key);
        byte[] bytes = message.getBytes();
        byte[] macResult = mac.doFinal(bytes);
        return getHexString(macResult) + "\n String: " + new String(macResult);
    }

    public static String getCipherString(String message) throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        SecureRandom sr = new SecureRandom();
        keyGen.init(sr);
        Key key = keyGen.generateKey();
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(cipher.ENCRYPT_MODE, key);
        byte[] bytes = message.getBytes();
        byte[] cipherResult = cipher.doFinal(bytes);
        cipher.init(cipher.DECRYPT_MODE, key);
        byte[] bytes1 = cipher.doFinal(cipherResult);
        String str = new String(bytes1);
        String str1 = getHexString(cipherResult);
        str = str1 + "\n исходный текст: " + str;
        return str;
    }

    public static String getPairCipherString(String message) throws Exception {
        KeyPairGenerator KeyPairGen = KeyPairGenerator.getInstance("RSA");
        KeyPairGen.initialize(2048);
        KeyPair pair = KeyPairGen.generateKeyPair();
        PublicKey publicKey = pair.getPublic();
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytes = message.getBytes();
        cipher.update(bytes);
        byte[] cipherResult = cipher.doFinal();
        cipher.init(Cipher.DECRYPT_MODE, pair.getPrivate());
        byte[] bytes1 = cipher.doFinal(cipherResult);
        String str = new String(bytes1);
        String str1 = getHexString(cipherResult);
        str = str1 + "\n исходный текст: " + str;
        return str;
    }

    public static String getElectSigmatString(String message) throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DSA");
        keyPairGen.initialize(2048);
        KeyPair pair = keyPairGen.generateKeyPair();
        PrivateKey privKey = pair.getPrivate();
        Signature sign = Signature.getInstance("SHA256withDSA");
        sign.initSign(privKey);
        byte[] bytes = message.getBytes();
        sign.update(bytes);
        byte[] signature = sign.sign();
        sign.initVerify(pair.getPublic());
        sign.update(bytes);
        boolean bool = sign.verify(signature);
        String str;
        if (bool) str = "Signature verified";
        else str = "Signature filed";
        String str1 = getHexString(signature);
        str = str1 + "\n" + str;
        return str;
    }

    private static String getHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            hexString.append(Integer.toHexString(0xFF & bytes[i]));
        }
        return "HexStr:  " + hexString.toString();
    }
}
