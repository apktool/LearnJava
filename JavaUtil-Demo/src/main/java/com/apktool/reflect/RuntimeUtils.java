package com.apktool.reflect;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 通过反射的方式为指定的对象赋值
 * 使用场景
 * - 比如从数据库中查询到了某些值，然后要将这些值赋值给某个对象，常规方法需要写大量的 set，但是反射的方式就省事很多。
 */

public class RuntimeUtils {
    public static void writeFieldValue(String field, Object value, Object obj) {
        try {
            PropertyDescriptor descriptor = new PropertyDescriptor(field, obj.getClass());
            Method writeMethod = descriptor.getWriteMethod();
            if (writeMethod == null) {
                return;
            }
            value = Optional.ofNullable(value).orElse("");
            writeMethod.invoke(obj, value);
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("need custom exception", e);
        }
    }

    public static void parse(Map<String, Object> map, Object obj) {
        map.forEach((key, value) -> RuntimeUtils.writeFieldValue(key, value, obj));
    }

    public static <T> T parse(Map<String, Object> maps, Class<T> clazz) {
        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());
        Map<String, Field> fieldMap = fields.stream().collect(Collectors.toMap(t -> t.getName(), t -> t));

        for (Map.Entry<String, Object> entry : maps.entrySet()) {
            if (!fieldMap.containsKey(entry.getKey())) {
                throw new RuntimeException("need custom exception");
            }
        }

        T r = null;
        try {
            r = clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        parse(maps, r);

        return r;
    }
}
