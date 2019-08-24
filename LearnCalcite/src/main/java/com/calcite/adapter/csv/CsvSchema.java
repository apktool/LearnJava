package com.calcite.adapter.csv;

import org.apache.calcite.schema.Table;
import org.apache.calcite.schema.impl.AbstractSchema;
import org.apache.calcite.util.Source;
import org.apache.calcite.util.Sources;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CsvSchema extends AbstractSchema {
    final private File file;

    public CsvSchema(File file) {
        this.file = file;
    }

    @Override
    protected Map<String, Table> getTableMap() {
        Source source = Sources.of(file);
        CsvScannableTable csvScannableTable = new CsvScannableTable(source, null);

        Map<String, Table> map = new HashMap<>();
        map.put(file.getName().split("\\.")[0].toUpperCase(), csvScannableTable);

        return map;
    }
}
