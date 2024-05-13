package com.witch.pan.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author Yuuki
 * fastJson解析封装 json转为对象
 */
public class JsonUtils {
    public static String convertObj2Json(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static <T> T convertJson2Obj(String json, Class<T> classz) {
        return JSONObject.parseObject(json, classz);
    }
    public static <T> List<T> convertJsonArray2List(String json, Class<T> classz) {
        return JSONArray.parseArray(json, classz);
    }

    public static void main(String[] args) {}
}
