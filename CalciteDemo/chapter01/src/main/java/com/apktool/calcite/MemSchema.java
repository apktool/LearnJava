package com.apktool.calcite;

import java.util.List;
import java.util.Map;

import org.apache.calcite.schema.Table;
import org.apache.calcite.schema.impl.AbstractSchema;

public class MemSchema extends AbstractSchema {
    private final Map<String, Object> map;

    public MemSchema(Map<String, Object> map) {
        this.map = map;
    }

    @Override
    protected Map<String, Table> getTableMap() {
        String tableName = (String) map.get("table");
        List<Map<String, String>> tableSchema = (List<Map<String, String>>) map.get("schema");
        List<Map<String, Object>> tableValue = (List<Map<String, Object>>) map.get("value");
        return Map.of(tableName, new MemTable(tableSchema, tableValue));
    }
}
