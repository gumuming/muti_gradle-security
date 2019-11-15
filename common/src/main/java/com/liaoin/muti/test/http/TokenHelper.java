package com.liaoin.muti.test.http;



import com.liaoin.muti.test.file.util.token.AESUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.UUID;

/***************************************
 * @Author: Sara Karma
 * @Date: 2019/5/28
 * @Email: sarakarma49@gmail.com  
 ***************************************/

public class TokenHelper {

    /**
     * 作者： liwei
     * 时间：2018年4月11日
     * 描述：生成token
     *
     * @param appkey
     * @return
     */
    public synchronized static final String getToken(String appkey) {
        return AESUtils.encrypt(appkey + numberOnlyRoomId()
                + randomNumber(9) + UUID.randomUUID().toString()).replace("_", "");
    }

    /**
     * 作者： liwei
     * 时间：2018年1月2日
     * 描述：精确到毫秒数产生时间数
     *
     * @return
     */
    public static String numberOnlyRoomId() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Calendar calendar = Calendar.getInstance();
        String str = format.format(calendar.getTime());
        return str;
    }

    /**
     * 作者： liwei
     * 时间：2018年1月2日
     * 描述：随机数，数量自定义
     *
     * @param o
     * @return
     */
    public static String randomNumber(Integer o) {
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < o; i++) {
            buffer.append(random.nextInt(9) % (9) + 1);
        }
        return buffer.toString();
    }
}
