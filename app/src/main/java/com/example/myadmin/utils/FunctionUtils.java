package com.example.myadmin.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

public class FunctionUtils {

   public static HashMap<String, Object> getDataMapFromClass(Object object){
       if(object == null){
           return null;
       } else {
          return new Gson().fromJson(new Gson().toJson(object), new TypeToken<HashMap<String, Object>>() {
           }.getType());
       }
   }

   public static boolean isNotEmpty(String value){
       return value!=null && value.trim().length() > 0;
   }

    public static Object getDataFromMap(Class clazz, HashMap<String,Object> map){
        if(map ==null||map.isEmpty()){
            return null;
        } else {
            return new Gson().fromJson(new Gson().toJson(map), clazz);
        }
    }

    public static Object getDataFromObject(Class clazz, Object object) {
        if (object == null) {
            return null;
        }
        HashMap<String,Object> map = (HashMap<String, Object>) object;
        if (map == null || map.isEmpty()) {
            return null;
        } else {
            return new Gson().fromJson(new Gson().toJson(map), clazz);
        }
    }

    public static HashMap<String, Object> getMapFromObject(Object object) {
        if (object == null) {
            return null;
        }
        return new Gson().fromJson(new Gson().toJson(object), new TypeToken<HashMap<String, Object>>() {
        }.getType());
    }
}
