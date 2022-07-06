package com.epam.utils;


import com.epam.exception.CustomException;

import java.util.HashMap;
import java.util.Map;

public class ContextUtil {
    public static String USER_ID = "userid";
    public static String USER_NAME = "username";
    public static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    public static void set(String key, Object value) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>();
            threadLocal.set(map);
        }
        map.put(key, value);
    }

    public static Object get(String key) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>();
            threadLocal.set(map);
        }
        return map.get(key);
    }

    public static long getUserId() {
        Object o = ContextUtil.get(ContextUtil.USER_ID);
        if (null == o) {
            throw new CustomException("");
        }
        return Long.parseLong((String) o);
    }

    public static String getUsername() {
        Object value = get(USER_NAME);
        return returnObjectValue(value);
    }

    public static void setUserID(String userID) {
        set(USER_ID, userID);
    }

    public static void setUsername(String username) {
        set(USER_NAME, username);
    }

    private static String returnObjectValue(Object value) {
        return value == null ? null : value.toString();
    }

    public static void remove() {
        threadLocal.remove();
    }


}
