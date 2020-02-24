package com.lee.utils;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha1Hash;

public class MD5Utils {

    public static void main(String[] args) {
            md5("123","nihao",2);
            sha1("789","chilema",3);
    }

    /**
     *
     * @param source  要加密的字符窗
     * @param salt  混淆字符串（盐）
     * @param hashIterations （迭代加密次数）
     */
    public static  void  md5(String source,String salt,Integer hashIterations){
        Md5Hash hash1 = new Md5Hash("12345");
        System.out.println("加密一次:"+hash1);
        Md5Hash hash2= new Md5Hash(hash1);
        System.out.println("加密2次:"+hash2);

        Md5Hash hash3 = new Md5Hash("12112","jiangxi");
        System.out.println("hash3:"+hash3.toString());

        Md5Hash hash4= new Md5Hash("123","user3wangwu",2);
        System.out.println("hash4加密3次:"+hash4.toString());
}

    public static  void sha1(String source,String salt,Integer hashIterations){
        Sha1Hash hash1 = new Sha1Hash("12345");
        System.out.println("加密一次:"+hash1);
        Sha1Hash hash2= new Sha1Hash(hash1.toString());
        System.out.println("加密2次:"+hash2);

        Sha1Hash hash3 = new Sha1Hash("12112","jiangxi");
        System.out.println("hash3:"+hash3.toString());

        Sha1Hash hash4= new Sha1Hash("12112","jiangxi",3);
        System.out.println(hash4.toString());
    }


}
