package com.apktool.calcite.schema;

import java.util.Map;

import org.apache.calcite.schema.Schema;
import org.apache.calcite.schema.SchemaFactory;
import org.apache.calcite.schema.SchemaPlus;

public class OLAPSchemaFactory implements SchemaFactory {
    @Override
    public Schema create(SchemaPlus parentSchema, String schemaName, Map<String, Object> operand) {
        return new OLAPSchema();
    }
}
