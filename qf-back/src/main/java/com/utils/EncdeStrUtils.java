package com.utils;

import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密
 *
 * @Auth chengst
 * @Date Created in  11:04 2020/4/15
 */
public class EncdeStrUtils {

    // 加盐
    private static final String SHU = "shu";


    // 哈希加密
    public static String getSHA256Str(String str) {
        MessageDigest messageDigest;
        String encdeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(str.getBytes("UTF-8"));
            encdeStr = Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encdeStr;
    }


    // MD5加密
    public static String getMD5Str(String str) throws Exception {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            throw new Exception("MD5加密出现错误，" + e.toString());
        }
    }


    //  生成一段加密的字符串
    public static String encrypt(String str){
        try {
            String sha256Str = getSHA256Str(str);
            String md5Str = getMD5Str(sha256Str + SHU).toUpperCase();
            return md5Str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        String cs = encrypt("cs");
        System.out.println(cs);
    }
}
