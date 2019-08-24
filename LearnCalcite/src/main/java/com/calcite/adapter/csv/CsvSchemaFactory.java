package com.calcite.adapter.csv;

import org.apache.calcite.schema.Schema;
import org.apache.calcite.schema.SchemaFactory;
import org.apache.calcite.schema.SchemaPlus;

import java.io.File;
import java.util.Map;

public class CsvSchemaFactory implements SchemaFactory {
    public static final CsvSchemaFactory INSTANCE = new CsvSchemaFactory();

    private CsvSchemaFactory() {
    }

    @Override
    public Schema create(SchemaPlus schemaPlus, String s, Map<String, Object> operand) {
        File file = new File((String) operand.get("file"));
        return new CsvSchema(file);
    }
}
