package com.riot.study.utils;

import org.apache.commons.codec.binary.Hex;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by bluegony on 2018. 6. 21..
 */
public class CipherUtil {

    public static String encryptWithHexKey(String plainText, String key) {
        try {
            return encrypt(plainText, DatatypeConverter.parseHexBinary(key));
        } catch (Exception e) {
            throw new RuntimeException( "encryption key error", e);
        }
    }
    // DI AES-128 key
    public static byte[] keyValueDI = new byte[]{'g', 'f', 'j', 'k', '2', 'i', ';', 's', 'c', 'g', 'a', 'u', 'k', 'q', 'f', '!'};

    public static String encrypt(String plainText, byte[] encryptionKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec keySpec = new SecretKeySpec(encryptionKey, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        return Hex.encodeHexString(cipher.doFinal(plainText.getBytes()));
    }

    public static String decrypt(String cipherText, byte[] encryptionKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec keySpec = new SecretKeySpec(encryptionKey, "AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        return new String(cipher.doFinal(Hex.decodeHex(cipherText.toCharArray())));
    }

    public static byte[] makeValidKey(String key) {
        while(key.length()<16) {
            key += "!";
        }
        return key.getBytes();
    }


    public static String encrypt(String plainText, String key) {
        try {
            return encrypt(plainText, makeValidKey(key));
        } catch (Exception e) {
            throw new RuntimeException( "DB encryption key error");
        }
    }

    public static String decrypt(String cipherText, String key) {
        try {
            return decrypt(cipherText, makeValidKey(key));
        } catch (Exception e) {
            throw new RuntimeException( "DB encryption key error");
        }
    }


    public static String encryptForUserInfoDB(String plainText) throws Exception {
        return encrypt(plainText,keyValueDI);
    }

    public static String decryptForUserInfoDB(String cipherText) throws Exception {
        return decrypt(cipherText, keyValueDI);
    }


    public static String getSHA256(String str){

        String SHA = "";

        if(StringUtils.isEmpty(str)) {
            return null;
        }

        try{
            MessageDigest sh = MessageDigest.getInstance("SHA-256");
            sh.update(str.getBytes());
            byte byteData[] = sh.digest();
            StringBuffer sb = new StringBuffer();
            for(int i = 0 ; i < byteData.length ; i++){
                sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
            }
            SHA = sb.toString();

        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            SHA = null;
        }
        return SHA;
    }
}