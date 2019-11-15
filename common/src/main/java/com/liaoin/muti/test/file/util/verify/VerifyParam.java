package com.liaoin.muti.test.file.util.verify;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public interface VerifyParam {
    @Getter
    enum param{
        HOST("https://fephone.market.alicloudapi.com"),
        METHOD("GET"),
        APP_CODE("fa7f52d50f4942bca98a526f39663e4e"),
        ID_CARD_PATH( "/IDCard"),
        BANK_CHECK4_PATH("/bankCheck4"),
        BANK_CHECK_PATH("/bankCheck"),
        PHONE_CHECK_PATH("/phoneCheck"),
        ;
        String value;
         param(String value){
            this.value = value;
        }
    }

    default Map<String,String> getHeadersMap(){
        Map<String,String> headers = new HashMap<>();
        headers.put("Authorization", "APPCODE " + param.APP_CODE.getValue());
        return headers;
    }
}
