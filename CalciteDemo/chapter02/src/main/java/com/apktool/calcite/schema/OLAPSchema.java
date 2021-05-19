package com.apktool.calcite.schema;

import java.util.HashMap;
import java.util.Map;

import org.apache.calcite.schema.Table;
import org.apache.calcite.schema.impl.AbstractSchema;

public class OLAPSchema extends AbstractSchema {
    @Override
    protected Map<String, Table> getTableMap() {
        Map<String, Table> olapTables = new HashMap<>();
        olapTables.put("T_TABLE", new OLAPTable(this));

        return olapTables;
    }
}