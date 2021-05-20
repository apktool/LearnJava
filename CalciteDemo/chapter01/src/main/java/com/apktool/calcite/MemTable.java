package com.apktool.calcite;

import java.util.List;
import java.util.Map;

import org.apache.calcite.DataContext;
import org.apache.calcite.linq4j.AbstractEnumerable;
import org.apache.calcite.linq4j.Enumerable;
import org.apache.calcite.linq4j.Enumerator;
import org.apache.calcite.rel.type.RelDataType;
import org.apache.calcite.rel.type.RelDataTypeFactory;
import org.apache.calcite.schema.ScannableTable;
import org.apache.calcite.schema.impl.AbstractTable;
import org.apache.calcite.sql.type.SqlTypeName;

public class MemTable extends AbstractTable implements ScannableTable {

    private final List<Map<String, String>> schemas;
    private final List<Map<String, Object>> values;

    public MemTable(List<Map<String, String>> schemas, List<Map<String, Object>> values) {
        this.schemas = schemas;
        this.values = values;
    }

    @Override
    public Enumerable<Object[]> scan(DataContext dataContext) {
        return new AbstractEnumerable<>() {
            @Override
            public Enumerator<Object[]> enumerator() {
                return new MemEnumerator<>(values);
            }
        };
    }

    @Override
    public RelDataType getRowType(RelDataTypeFactory relDataTypeFactory) {
        RelDataTypeFactory.Builder builder = relDataTypeFactory.builder();

        for (Map<String, String> schema : schemas) {
            builder.add(schema.get("name"), SqlTypeName.get(schema.get("type")));
        }

        return builder.build();
    }
}