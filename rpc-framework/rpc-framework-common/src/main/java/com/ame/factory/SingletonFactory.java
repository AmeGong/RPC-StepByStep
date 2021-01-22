package com.ame.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * FileName: SingletonFactory
 * Author:   AmeGong
 * Date:     2021/1/4 14:21
 */
public final class SingletonFactory {
    private static final Map<String, Object> OBJECT_MAP = new HashMap<>();

    private SingletonFactory(){}

    public static <T> T getInstance(Class<T> c) {
        String key = c.toString();
        Object instance = OBJECT_MAP.get(key);
        if (instance == null) {
            synchronized (c) {
                instance = OBJECT_MAP.get(key);
                if (instance == null) {
                    try {
                        instance = c.newInstance();
                        OBJECT_MAP.put(key, instance);
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return c.cast(instance);
    }
}
