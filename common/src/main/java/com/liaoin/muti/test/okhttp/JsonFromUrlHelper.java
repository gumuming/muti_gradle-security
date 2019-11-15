package com.liaoin.muti.test.okhttp;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liaoin.muti.test.file.util.JsonUtils;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @program: config-spring-boot-starter
 * @description: 将url中json转化为josn 对象
 * @author: JingDe Ran
 * @create: 2019-10-14 10:07
 * @Email: sarakarma49@gmail.com
 */
@Component
public class JsonFromUrlHelper {

    @Resource
    ObjectMapper objectMapper;

    /**
     * 通用接口返回
     * <T> List<T>
     *
     * @param url
     * @return
     * @Example :{   "response":{"code":"502","msg":"该订单号已经存在！"},
     * "body":{"accountID": "29ff8936-d473-4dd6-8025-9dd400c58afd","accountName": "物业费预付款", "accountAmount": "90.00"}
     * }
     */
    public <T> T jsonToBody(String url, Class<T> beanType, ObjectMapper mapper) {
        try {
            Response response = OkhttpUtils.get(url);
            assert response.body() != null;
            String string = response.body().string();
            ResponseNoList responseList = JsonUtils.jsonToPojo(string, ResponseNoList.class, mapper);
            Object listDatas = responseList.getBody();
            String s = JsonUtils.objectToJson(listDatas, mapper);
            return JsonUtils.jsonToPojo(s, beanType, mapper);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 返回body 中的list
     * @Example :
     * {
     *     "response": { "code": "200",  "msg": "" },
     *     "body": [
     *         {
     *             "accountID": "2f214514-131c-4045-b46c-9763012310eb",
     *             "accountName": "预付款",
     *             "accountAmount": "120.00"
     *         },
     *         {
     *             "accountID": "29ff8936-d473-4dd6-8025-9dd400c58afd",
     *             "accountName": "物业费预付款",
     *             "accountAmount": "90.00"
     *         }
     *     ]
     * }
     * @param url
     * @param beanType
     * @param mapper
     * @param <T>
     * @return
     */
    public<T> List<T> jsonToBodyList(String url, Class<T> beanType, ObjectMapper mapper) {
        try {
            Response response = OkhttpUtils.get(url);
            assert response.body() != null;
            String string = response.body().string();
            ResponseNoList responseList = JsonUtils.jsonToPojo(string, ResponseNoList.class, mapper);
            Object listDatas = responseList.getBody();
            String s = JsonUtils.objectToJson(listDatas, mapper);
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, beanType);
            return mapper.readValue(s, javaType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public com.liaoin.muti.test.http.Response jsonToListNoData(String url) {
        try {
            Response response = OkhttpUtils.get(url);
            assert response.body() != null;
            String string = response.body().string();
            return JsonUtils.jsonToPojo(string,  com.liaoin.muti.test.http.Response.class, objectMapper);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public com.liaoin.muti.test.http.Response login(String url, Map<String,String> map,String header,String headerValue ) {
        try {
            Response response = OkhttpUtils.post(url,map,header,headerValue);
            assert response.body() != null;
            String string = response.body().string();
            return JsonUtils.jsonToPojo(string,  com.liaoin.muti.test.http.Response.class, objectMapper);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}