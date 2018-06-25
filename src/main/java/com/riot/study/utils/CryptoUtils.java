//package com.riot.study.utils;
//
//import lombok.extern.slf4j.Slf4j;
//
//import javax.crypto.BadPaddingException;
//import javax.crypto.Cipher;
//import javax.crypto.IllegalBlockSizeException;
//import javax.crypto.NoSuchPaddingException;
//import javax.crypto.spec.SecretKeySpec;
//import javax.xml.bind.DatatypeConverter;
//import java.io.*;
//import java.security.*;
//import java.security.cert.CertificateException;
//import java.util.Base64;
//
///**
// * 암/복호화 모듈
// * - String 및 File 에 대한 암호화/복호화 기능 제공
// * - KeyStore 에 적재된 Secure Key 를 통하여 SEED/DES/AES 등의 대칭키 암호화 알고리즘 사용 가능
// * - JCA (Java Cryptography Architecture) + BouncyCastle Provider 의 Wrapper
// *
// * Author : dyhan
// * Created date : 2017.08.30
// */
//
//@Slf4j
//public class CryptoUtils {
//
//    /**
//     * 문자열을 입력받아 암호화 후 Base64 인코딩 문자열로 반환
//     * @param orig Plain String
//     * @param cryptoInfo crypto detailed information
//     * @return encrypted + Base64 encoded String
//     */
//    public static String enc(String orig, CryptoInfo cryptoInfo) {
//        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
//
//        Key key = CryptoUtils.getKey(cryptoInfo);
//        if (key == null) return null;
//
//        String rslt = null;
//
//        try {
//            log.debug("[Input String] " + orig);
//            byte[] input = orig.getBytes();
//            CryptoUtils.bytesToHexa("Input-Plain", input);
//
//            Cipher cipher = Cipher.getInstance(cryptoInfo.getCipherSpec());//, "BC");
//            cipher.init(Cipher.ENCRYPT_MODE, key);
//
//            byte[] cipherText = cipher.doFinal(input);
//            CryptoUtils.bytesToHexa("Encoded", cipherText);
//
//            Base64.Encoder b64Encoder = Base64.getEncoder();
//            byte[] b64Encoded = b64Encoder.encode(cipherText);
//
//            rslt = new String(b64Encoded);
//            log.debug("[Return-Base64] " + rslt);
//
//        } catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | NoSuchPaddingException e) {
//            log.error("Crypto Encoding error");
//            e.printStackTrace();
//        }
//
//        return rslt;
//    }
//
//    /**
//     * Base64 인코딩 된 암호화 문자열을 받아 복호화
//     * @param encStr 대칭키 암호화 + Base64 인코딩 된 문자열
//     * @param cryptoInfo crypto detailed information
//     * @return Decrypted plain string
//     */
//    public static String dec(String encStr, CryptoInfo cryptoInfo) {
//        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
//
//        Key key = CryptoUtils.getKey(cryptoInfo);
//        if (key == null) return null;
//
//        String rslt = null;
//
//        try {
//            log.debug("[Input-Base64] " + encStr);
//            Base64.Decoder decoder = Base64.getDecoder();
//            byte[] cipherText = decoder.decode(encStr);
//            CryptoUtils.bytesToHexa("Encoded", cipherText);
//
//            Cipher cipher = Cipher.getInstance(cryptoInfo.getCipherSpec());//, "BC");
//            cipher.init(Cipher.DECRYPT_MODE, key);
//
//            byte[] plainText = cipher.doFinal(cipherText);
//            CryptoUtils.bytesToHexa("Decoded", plainText);
//
//            rslt = new String(plainText);
//            log.debug("[Return-Plain] " + rslt);
//
//        } catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | NoSuchPaddingException e) {
//            log.error("Crypto Decoding error");
//            e.printStackTrace();
//        }
//
//        return rslt;
//    }
//
//    /**
//     * 파일 암호화 (파일명 사용)
//     * @param inFilePath 원본 파일 경로
//     * @param outFilePath 암호화 된 파일 저장 경로
//     * @param cryptoInfo crypto detailed information
//     * @return true when success
//     */
//    public static boolean enc(String inFilePath, String outFilePath, CryptoInfo cryptoInfo) {
//        InputStream inputStream;
//        OutputStream outputStream;
//        boolean result = false;
//
//        try {
//            inputStream = new FileInputStream(inFilePath);
//            outputStream = new FileOutputStream(outFilePath);
//
//            result = enc(inputStream, outputStream, cryptoInfo);
//
//            inputStream.close();
//            outputStream.close();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            return false;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return result;
//    }
//
//    /**
//     * 파일 암호화 (파일 스트림 사용)
//     * @param inputStream 원본 파일 스트림
//     * @param outputStream 암호화 결과 저장 파일 스트림
//     * @param cryptoInfo crypto detailed information
//     * @return true when success
//     */
//    public static boolean enc(InputStream inputStream, OutputStream outputStream, CryptoInfo cryptoInfo) {
//        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
//
//        Key key = CryptoUtils.getKey(cryptoInfo);
//        if (key == null) return false;
//
//        Cipher cipher = null;//, "BC");
//        try {
//            cipher = Cipher.getInstance(cryptoInfo.getCipherSpec());
//            cipher.init(Cipher.ENCRYPT_MODE, key);
//        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
//            e.printStackTrace();
//            return false;
//        }
//
//
//        byte[] buffer = new byte[cryptoInfo.getBlockSize()];
//        int noBytes = 0;
//        byte[] cipherBlock = new byte[cipher.getOutputSize(buffer.length)];
//        int cipherBytes;
//
//        try {
//            while ((noBytes = inputStream.read(buffer)) != -1) {
//                cipherBytes = cipher.update(buffer, 0, noBytes, cipherBlock);
//                outputStream.write(cipherBlock, 0, cipherBytes);
//            }
//            //always call doFinal
//            cipherBytes = cipher.doFinal(cipherBlock, 0);
//            outputStream.write(cipherBlock, 0, cipherBytes);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//
//        return true;
//    }
//
//    /**
//     * 파일 복호화 (파일경로 사용)
//     * @param inFilePath 암호화 된 파일 경로
//     * @param outFilePath 복호화 후 저장할 파일 경로
//     * @param cryptoInfo crypto detailed information
//     * @return true when success
//     */
//    public static boolean dec(String inFilePath, String outFilePath, CryptoInfo cryptoInfo) {
//        InputStream inputStream;
//        OutputStream outputStream;
//        boolean result = false;
//
//        try {
//            inputStream = new FileInputStream(inFilePath);
//            outputStream = new FileOutputStream(outFilePath);
//
//            result = dec(inputStream, outputStream, cryptoInfo);
//
//            inputStream.close();
//            outputStream.close();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            return false;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return result;
//    }
//
//    /**
//     * 파일 복호화 (파일 스트림 사용)
//     * @param inputStream 암호화 된 파일 스트림
//     * @param outputStream 복호화 후 저장할 파일 스트림
//     * @param cryptoInfo crypto detailed information
//     * @return true when success
//     */
//    public static boolean dec(InputStream inputStream, OutputStream outputStream, CryptoInfo cryptoInfo) {
//        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
//
//        Key key = CryptoUtils.getKey(cryptoInfo);
//        if (key == null) return false;
//
//        Cipher cipher = null;//, "BC");
//        try {
//            cipher = Cipher.getInstance(cryptoInfo.getCipherSpec());
//            cipher.init(Cipher.DECRYPT_MODE, key);
//        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
//            e.printStackTrace();
//            return false;
//        }
//
//        byte[] buffer = new byte[cryptoInfo.getBlockSize()];
//        int noBytes = 0;
//        byte[] cipherBlock = new byte[cipher.getOutputSize(buffer.length)];
//        int cipherBytes;
//
//        try {
//            while ((noBytes = inputStream.read(buffer)) != -1) {
//                cipherBytes = cipher.update(buffer, 0, noBytes, cipherBlock);
//                outputStream.write(cipherBlock, 0, cipherBytes);
//            }
//            //always call doFinal
//            cipherBytes = cipher.doFinal(cipherBlock, 0);
//            outputStream.write(cipherBlock, 0, cipherBytes);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//
//        return true;
//    }
//
//
//    /**
//     * Get Key from KeyStore
//     * @param cryptoInfo
//     * @return Key (or null when error)
//     */
//    public static Key getKey(CryptoInfo cryptoInfo) {
//        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
//
//        Key key = null;
//        try {
//            KeyStore ks = KeyStore.getInstance("JCEKS");
//            char[] passwordForKeyCharArray = cryptoInfo.getKeyPassword().toCharArray();
//            char[] keystorePasswordCharArray = cryptoInfo.getKeyStorePassword().toCharArray();
//            File filePathToStore = new File(cryptoInfo.getKeyStorePath());
//
//            InputStream readStream = new FileInputStream(filePathToStore);
//            ks.load(readStream, keystorePasswordCharArray);
//            key = ks.getKey(cryptoInfo.getKeyAlias(), passwordForKeyCharArray);
//            readStream.close();
//
//            //bytesToHexa("Key", key.getEncoded());  // Key 가 노출되니 Debug 시에만 사용할 것!!
//
//        } catch (KeyStoreException e) {
//            log.error("KeyStore error - " + cryptoInfo.toString());
//        } catch (FileNotFoundException e) {
//            log.error("KeyStore file not found" + cryptoInfo.toString());
//        } catch (CertificateException e) {
//            log.error("Wrong KeyStore password" + cryptoInfo.toString());
//        } catch (NoSuchAlgorithmException e) {
//            log.error("Not found key (wrong name or not exist)" + cryptoInfo.toString());
//        } catch (UnrecoverableKeyException e) {
//            log.error("Wrong Key password" + cryptoInfo.toString());
//        } catch (IOException e) {
//            log.error("Key loading error" + cryptoInfo.toString());
//        }
//
//        return key;
//    }
//
//    /**
//     * 바이트의 Hexa 코드를 문자열로 로그 출력
//     * @param title 로그의 헤더 타이틀
//     * @param bytes 출력할 byte array
//     */
//    public static void bytesToHexa(String title, byte[] bytes) {
//        StringBuilder dumpStr = new StringBuilder("[" + title + "] ");
//        for (byte aPbCipher : bytes) {
//            dumpStr.append(Integer.toHexString(0xff & aPbCipher)).append(" ");
//        }
//        log.debug(dumpStr.toString());
//    }
//
//
//}
