package com.study.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created on 2017. 12. 11..
 */
@Slf4j
public class CipherUtils {

    public static String encrypt(String plainText, byte[] encryptionKey) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec keySpec = new SecretKeySpec(encryptionKey, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            return Hex.encodeHexString(cipher.doFinal(plainText.getBytes()));
        } catch (Exception e) {
            log.error("encryption error", e);
//            throw new SimpleException(ErrorCode.ENCRYPTION_ERROR);
            throw new RuntimeException();
        }
    }

    public static String decrypt(String cipherText, byte[] encryptionKey) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec keySpec = new SecretKeySpec(encryptionKey, "AES");
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            return new String(cipher.doFinal(Hex.decodeHex(cipherText.toCharArray())));
        } catch (Exception e) {
            log.error("decryption error", e);
//            throw new SimpleException(ErrorCode.DECRYPTION_ERROR);
            throw new RuntimeException();
        }
    }

    public static byte[] makeValidKey(String key) {
        while(key.length()<16) {
            key += "!";
        }
        return key.getBytes();
    }


    public static String encrypt(String plainText, String key) {
        return encrypt(plainText, makeValidKey(key));
    }

    public static String decrypt(String cipherText, String key) {
        return decrypt(cipherText, makeValidKey(key));
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