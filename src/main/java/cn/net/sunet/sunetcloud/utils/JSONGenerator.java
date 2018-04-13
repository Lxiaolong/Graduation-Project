package cn.net.sunet.sunetcloud.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Copyright 2018. bestsource corporation All rights reserved.
 * 作者： Lawrence Li
 * 日期： 2018/2/26
 * 邮箱： AC_Dreamer@163.com
 * 网址： www.bestsource.net.cn
 */

/**
 * 生成json数据类
 */
@Component
public class JSONGenerator {
    private Map<String, Object> json, data;

    public JSONGenerator() {
        json = new HashMap<>();
        data = new HashMap<>();
        json.put("code", 0);
        json.put("msg", "");
        json.put("data", data);
    }

    public Map<String, Object> getJson() {
        return json;
    }

    public JSONGenerator setCode(int code) {
        json.put("code", code);
        return this;
    }

    public JSONGenerator setMsg(String msg) {
        json.put("msg", msg);
        return this;
    }

    public JSONGenerator setData(Object data) {
        json.put("data", data);
        return this;
    }

    public JSONGenerator addToData(String key, Object value) {
        data.put(key, value);
        return this;
    }

    public JSONGenerator addRootProperty(String key, Object value) {
        json.put(key, value);
        return this;
    }

    public String asJson() {
        if (data.size() > 0)
            json.put("data", data);
        JSONObject jsonObject = new JSONObject(json);
        return jsonObject.toJSONString();
    }

    public JSONObject asJsonObject() {
        if (data.size() > 0)
            json.put("data", data);
        return new JSONObject(json);
    }

    public static List<Map<String, Object>> getMapList(List<?> list, String... methodNameArray) {
        List<Map<String, Object>> maps = new ArrayList<>();

        for (Object entity : list) {
            Class<?> clazz = entity.getClass();

            Map<String, Object> map = new HashMap<>();
            for (String methodName : methodNameArray) {
                try {
                    Method method = clazz.getDeclaredMethod(methodName);
                    try {
                        Object result = method.invoke(entity);
                        String key = methodName;
                        if (key.indexOf("get") == 0 && key.length() > 3) {
                            key = String.valueOf(key.charAt(3)).toLowerCase() + key.substring(4);
                        }
                        map.put(key, result);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
            maps.add(map);
        }
        return maps;
    }
}
