package com.gameley.common.util;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MapUtil{

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
