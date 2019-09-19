package com.calcite.adapter.chapter1;

import org.apache.calcite.DataContext;
import org.apache.calcite.linq4j.Enumerable;
import org.apache.calcite.linq4j.Linq4j;
import org.apache.calcite.rel.type.RelDataType;
import org.apache.calcite.rel.type.RelDataTypeFactory;
import org.apache.calcite.schema.ScannableTable;
import org.apache.calcite.schema.impl.AbstractTable;
import org.apache.calcite.sql.type.SqlTypeName;

public class OrdersTable extends AbstractTable implements ScannableTable {
    final Object[][] rows = {
        {1, "red", 10},
        {2, "yellow", 5},
        {3, "red", 12},
        {4, "blue", 3},
        {5, "red", 3}
    };

    @Override
    public Enumerable<Object[]> scan(DataContext root) {
        return Linq4j.asEnumerable(rows);
    }

    @Override
    public RelDataType getRowType(RelDataTypeFactory typeFactory) {
        RelDataTypeFactory.Builder builder = typeFactory.builder();
        builder
            .add("ID", SqlTypeName.INTEGER)
            .add("color", SqlTypeName.VARCHAR, 5)
            .add("units", SqlTypeName.INTEGER);

        return builder.build();
    }
}
