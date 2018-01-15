package com.gameley.common.util;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MapUtil{
    /**
     * 根据所传参数名称，将非空的参数值存入到map中返回
     * @param jsonObject
     * @param strings
     * @return
     */
    public static Map<String,Object> changeToMap(JSONObject jsonObject,String... strings){
        Map<String,Object> maps=new HashMap<>();
        for(String name:strings){
            Object object=jsonObject.get(name);
            if(object!=null&&!"null".equals(object)){
                maps.put(name, object);
            }
        }

        return maps;
    }
}
