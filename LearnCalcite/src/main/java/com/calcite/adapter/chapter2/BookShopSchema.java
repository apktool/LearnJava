package com.calcite.adapter.chapter2;

import com.calcite.adapter.chapter2.storage.Storage;
import org.apache.calcite.schema.Table;
import org.apache.calcite.schema.impl.AbstractSchema;

import java.util.HashMap;
import java.util.Map;

public class BookShopSchema extends AbstractSchema {

    @Override
    public Map<String, Table> getTableMap() {

        Map<String, Table> tables = new HashMap<>();

        Storage.getTables().forEach(it ->
            tables.put(it.getName(), new BookShopTable(it))
        );

        return tables;
    }
}
