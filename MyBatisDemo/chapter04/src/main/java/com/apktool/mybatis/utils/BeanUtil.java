package com.apktool.mybatis.utils;

import java.lang.reflect.Field;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BeanUtil {

    public static Object getFieldValue(Object target, String fieldName) {
        Object value = null;
        Field field = getField(target, fieldName);
        if (field != null) {
            try {
                field.setAccessible(true);
                value = field.get(target);
            } catch (Exception e) {
                if (log.isErrorEnabled()) {
                    log.error(e.getMessage(), e);
                }
            }
        }
        return value;
    }

    public static void setFieldValue(Object target, String fieldName, Object value) {
        Field field = getField(target, fieldName);
        if (field != null) {
            try {
                field.setAccessible(true);
                field.set(target, value);
            } catch (Exception e) {
                if (log.isErrorEnabled()) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }

    public static Field getField(Object target, String fieldName) {
        Class clazz = target.getClass();
        while (clazz != Object.class) {
            try {
                return clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        return null;
    }
}