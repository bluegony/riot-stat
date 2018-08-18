package com.study.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * Created by 1000773 on 2018. 7. 26..
 */
@Slf4j
public class ByteBufferWrapper {

    private ByteBuffer buffer;

    public ByteBufferWrapper(int size) {
        buffer = ByteBuffer.allocate(size);
    }

    public byte[] array() {
        return buffer.array();
    }


    public byte[] getAlphabetBytes(String input, int size) {
        if (input == null) input = "";
        byte[] target = StringUtils.repeat(' ', size).getBytes(Charset.forName("euc-kr"));
        byte[] source = input.getBytes(Charset.forName("euc-kr"));

        int copyLength = source.length;
        if(source.length>target.length) {
            copyLength = target.length;
        }
        System.arraycopy(source, 0, target, target.length - copyLength, copyLength);  // left padding
//        checkKoreanEncodingProblem(new String(target,Charset.forName("utf-8")));    // euc-kr로 인코딩한것을 utf-8로 디코딩해서 문제가 발생하는 경우를 확인하기 위함.
        return target;
    }

    byte[] getNumericBytes(String input, int size) {
        if (input==null) input = "";
        return StringUtils.leftPad(input, size,'0').getBytes();
    }

    public void safePutAlphabet( String input, int length) {
//        log.debug("alphabet:{} [{}]",input, new String(getAlphabetBytes(input,length)));
        buffer.put(getAlphabetBytes(input,length),0,length);
    }
    protected void safePutNumber( String input, int length) {
//        log.debug("number  :{} [{}]",input, new String(getNumericBytes(input,length)));
        buffer.put(getNumericBytes(input,length),0,length);
    }

    public ByteBuffer put(byte[] array) {
        return buffer.put(array);
    }

    public void checkKoreanEncodingProblem(String word)  {
        log.info("================================================================================================================================");
        log.info(word);

        try {
            // 앞이 인코딩, 뒤가 디코딩하는 문자셋
            System.out.println("utf-8 -> euc-kr        : " + new String(word.getBytes("utf-8"), "euc-kr"));
            System.out.println("utf-8 -> ksc5601       : " + new String(word.getBytes("utf-8"), "ksc5601"));
            System.out.println("utf-8 -> x-windows-949 : " + new String(word.getBytes("utf-8"), "x-windows-949"));
            System.out.println("utf-8 -> iso-8859-1    : " + new String(word.getBytes("utf-8"), "iso-8859-1"));
            System.out.println("iso-8859-1 -> euc-kr        : " + new String(word.getBytes("iso-8859-1"), "euc-kr"));
            System.out.println("iso-8859-1 -> ksc5601       : " + new String(word.getBytes("iso-8859-1"), "ksc5601"));
            System.out.println("iso-8859-1 -> x-windows-949 : " + new String(word.getBytes("iso-8859-1"), "x-windows-949"));
            System.out.println("iso-8859-1 -> utf-8         : " + new String(word.getBytes("iso-8859-1"), "utf-8"));
            System.out.println("euc-kr -> utf-8         : " + new String(word.getBytes("euc-kr"), "utf-8"));
            System.out.println("euc-kr -> ksc5601       : " + new String(word.getBytes("euc-kr"), "ksc5601"));
            System.out.println("euc-kr -> x-windows-949 : " + new String(word.getBytes("euc-kr"), "x-windows-949"));
            System.out.println("euc-kr -> iso-8859-1    : " + new String(word.getBytes("euc-kr"), "iso-8859-1"));
            System.out.println("ksc5601 -> euc-kr        : " + new String(word.getBytes("ksc5601"), "euc-kr"));
            System.out.println("ksc5601 -> utf-8         : " + new String(word.getBytes("ksc5601"), "utf-8"));
            System.out.println("ksc5601 -> x-windows-949 : " + new String(word.getBytes("ksc5601"), "x-windows-949"));
            System.out.println("ksc5601 -> iso-8859-1    : " + new String(word.getBytes("ksc5601"), "iso-8859-1"));
            System.out.println("x-windows-949 -> euc-kr     : " + new String(word.getBytes("x-windows-949"), "euc-kr"));
            System.out.println("x-windows-949 -> utf-8      : " + new String(word.getBytes("x-windows-949"), "utf-8"));
            System.out.println("x-windows-949 -> ksc5601    : " + new String(word.getBytes("x-windows-949"), "ksc5601"));
            System.out.println("x-windows-949 -> iso-8859-1 : " + new String(word.getBytes("x-windows-949"), "iso-8859-1"));
        } catch (Exception e) {
            log.info("checkKoreanEncodingProblem exception",e);
        }
    }
}
