package com.calcite.adapter.chapter1;

import org.apache.calcite.rel.type.RelDataType;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.schema.Table;
import org.apache.calcite.schema.TableFactory;

import java.util.Map;

public class OrdersTableFactory implements TableFactory<Table> {
    @Override
    public Table create(SchemaPlus schema, String name, Map<String, Object> operand, RelDataType rowType) {
        return new OrdersTable();
    }
}
