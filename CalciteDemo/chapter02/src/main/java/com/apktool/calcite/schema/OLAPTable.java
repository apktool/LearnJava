package com.apktool.calcite.schema;

import java.lang.reflect.Type;

import org.apache.calcite.DataContext;
import org.apache.calcite.adapter.java.AbstractQueryableTable;
import org.apache.calcite.linq4j.Enumerable;
import org.apache.calcite.linq4j.Enumerator;
import org.apache.calcite.linq4j.QueryProvider;
import org.apache.calcite.linq4j.Queryable;
import org.apache.calcite.plan.RelOptTable;
import org.apache.calcite.rel.RelNode;
import org.apache.calcite.rel.type.RelDataType;
import org.apache.calcite.rel.type.RelDataTypeFactory;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.schema.TranslatableTable;
import org.apache.calcite.schema.impl.AbstractTableQueryable;
import org.apache.calcite.sql.type.SqlTypeName;

import com.apktool.calcite.enumerator.OLAPQuery;
import com.apktool.calcite.relnode.OLAPRel;
import com.apktool.calcite.relnode.OLAPTableScan;

public class OLAPTable extends AbstractQueryableTable implements TranslatableTable {
    private final OLAPSchema olapSchema;

    public OLAPTable(OLAPSchema schema) {
        super(Object[].class);
        this.olapSchema = schema;
    }

    public OLAPSchema getSchema() {
        return this.olapSchema;
    }

    @Override
    public Type getElementType() {
        return Object[].class;
    }

    @Override
    public <T> Queryable<T> asQueryable(QueryProvider queryProvider, SchemaPlus schema, String tableName) {
        return new AbstractTableQueryable<T>(queryProvider, schema, this, tableName) {
            @SuppressWarnings("unchecked")
            public Enumerator<T> enumerator() {
                final OLAPQuery query = new OLAPQuery(OLAPQuery.EnumeratorTypeEnum.MEM);
                return (Enumerator<T>) query.enumerator();
            }
        };
    }

    @Override
    public RelNode toRel(RelOptTable.ToRelContext toRelContext, RelOptTable relOptTable) {
        return new OLAPTableScan(toRelContext.getCluster(), toRelContext.getCluster().traitSetOf(OLAPRel.CONVENTION), relOptTable);
    }

    @Override
    public RelDataType getRowType(RelDataTypeFactory relDataTypeFactory) {
        RelDataTypeFactory.Builder builder = relDataTypeFactory.builder();

        RelDataType dataType = builder
                .add("ID", SqlTypeName.INTEGER)
                .add("COLOR", SqlTypeName.VARCHAR, 5)
                .add("UNITS", SqlTypeName.INTEGER)
                .build();

        return dataType;
    }

    public Enumerable<Object[]> executeMemQuery(final DataContext root) {
        return new OLAPQuery(root, OLAPQuery.EnumeratorTypeEnum.MEM);
    }
}
