package com.apktool.mybatis.page;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.apktool.mybatis.utils.BeanUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination {
    /**
     * 当前页面条数
     */
    private int pageSize;

    /**
     * 开始记录数
     */
    private int startRow;

    /**
     * 结束记录数
     */
    private int endRow;

    /**
     * 参数信息
     */
    private Map<String, Object> param = new HashMap<String, Object>();

    public Pagination(Pageable query) {
        this.param = toQueryMap(query);
        this.pageSize = query.getPageSize();
        this.startRow = query.getPageNo() * pageSize;
        this.endRow = startRow + query.getPageNo() * pageSize;
    }


    /**
     * 请传入的参数对象转化为map 形式(算法单独封装保证稳定性)
     *
     * @param query 参数对象
     * @return 参数Map
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> toQueryMap(Object query) {
        if (query == null) {
            throw new IllegalArgumentException("Target bean can not be null!");
        }
        if (query instanceof Map) {
            return (Map<String, Object>) query;
        }
        Map<String, Object> map = new HashMap<String, Object>(20);
        List<Field> fields = new ArrayList<Field>();

        Class aClass = query.getClass();
        do {
            Field[] declaredFields = aClass.getDeclaredFields();
            fields.addAll(Arrays.asList(declaredFields));
            aClass = aClass.getSuperclass();
        } while (!aClass.equals(Object.class));
        for (Field field : fields) {
            String fieldName = field.getName();
            map.put(fieldName, BeanUtil.getFieldValue(query, fieldName));
        }
        return map;
    }
}