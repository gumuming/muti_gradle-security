package com.liaoin.muti.test.file.util.token;

/***************************************
 * @Author: Sara Karma
 * @Date: 2019/5/21
 * @Email: sarakarma49@gmail.com  
 ***************************************/

public class TokenGeneration {

    public static String getToken(String key){
        return AESUtils.encrypt(key);
    }

    public static void main(String[] args) {
        System.out.println( getToken("84884"));
    }


}
