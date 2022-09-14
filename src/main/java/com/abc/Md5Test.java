package com.abc;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * @author lb
 * @date 9/13/22
 */
public class Md5Test {
    public static void main(String[] args) {
        String signdata = null;
        String caseId = "111";
        long timesTamp = System.currentTimeMillis();
        String type = "1";
        String status = "1";
        String appid = "222";
        String appsecret = "333";
        String data = caseId+timesTamp+type+status+appsecret;
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 计算md5函数
            md.update(data.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            System.out.println( md.digest());
            signdata = new BigInteger(1, md.digest()).toString(16);
            System.out.println(signdata);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

}
