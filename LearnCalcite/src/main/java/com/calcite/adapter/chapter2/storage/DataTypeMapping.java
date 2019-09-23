package com.calcite.adapter.chapter2.storage;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.apache.calcite.sql.type.SqlTypeName;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

/**
 * 这里使用了GUAVA的table 作为存SQL和JAVA数据类型的数据结构
 * 这并不是一个好的设计，而是为了让大家更容易理解而做的设计
 */
public class DataTypeMapping {

    public static Table<String, SqlTypeName, Class> TYPEMAPPING = HashBasedTable.create();

    static {
        TYPEMAPPING.put("char", SqlTypeName.CHAR, Character.class);
        TYPEMAPPING.put("varchar", SqlTypeName.VARCHAR, String.class);
        TYPEMAPPING.put("boolean", SqlTypeName.BOOLEAN, Boolean.class);
        TYPEMAPPING.put("date", SqlTypeName.DATE, Date.class);
        TYPEMAPPING.put("integer", SqlTypeName.INTEGER, Integer.class);
        TYPEMAPPING.put("tinyint", SqlTypeName.TINYINT, Integer.class);
        TYPEMAPPING.put("smallint", SqlTypeName.SMALLINT, Integer.class);
        TYPEMAPPING.put("bigint", SqlTypeName.BIGINT, Long.class);
        TYPEMAPPING.put("decimal", SqlTypeName.DECIMAL, BigDecimal.class);
        TYPEMAPPING.put("numeric", SqlTypeName.DECIMAL, Long.class);
        TYPEMAPPING.put("float", SqlTypeName.FLOAT, Float.class);
        TYPEMAPPING.put("real", SqlTypeName.REAL, Double.class);
        TYPEMAPPING.put("double", SqlTypeName.DOUBLE, Double.class);
        TYPEMAPPING.put("time", SqlTypeName.TIME, Date.class);
        TYPEMAPPING.put("timestamp", SqlTypeName.TIMESTAMP, Long.class);
        TYPEMAPPING.put("any", SqlTypeName.ANY, String.class);
    }

    /**
     * 根据名字获取，对应的java类型
     */
    public static Class getJavaClassByName(String name) {
        Set<Table.Cell<String, SqlTypeName, Class>> table = TYPEMAPPING.cellSet();
        for (Table.Cell<String, SqlTypeName, Class> it : table) {
            if (it.getRowKey().equals(name)) {
                return it.getValue();
            }
        }
        return null;
    }

    public static SqlTypeName getSqlTypeByName(String name) {
        for (Table.Cell<String, SqlTypeName, Class> it : TYPEMAPPING.cellSet()) {
            if (it.getRowKey().equals(name)) {
                return it.getColumnKey();
            }
        }
        return null;
    }
}
