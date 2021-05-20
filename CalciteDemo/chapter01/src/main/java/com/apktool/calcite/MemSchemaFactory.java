package com.apktool.calcite;

import java.util.Map;

import org.apache.calcite.schema.Schema;
import org.apache.calcite.schema.SchemaFactory;
import org.apache.calcite.schema.SchemaPlus;

public class MemSchemaFactory implements SchemaFactory {
    @Override
    public Schema create(SchemaPlus schemaPlus, String s, Map<String, Object> map) {
        return new MemSchema(map);
    }
}
