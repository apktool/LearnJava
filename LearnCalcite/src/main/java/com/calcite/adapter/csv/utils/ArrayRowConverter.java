package com.calcite.adapter.csv.utils;

import com.calcite.adapter.csv.types.CsvFieldType;

import java.util.List;

public class ArrayRowConverter extends RowConverter<Object[]> {
    private final CsvFieldType[] fieldTypes;
    private final int[] fields;

    public ArrayRowConverter(List<CsvFieldType> fieldTypes, int[] fields) {
        this.fieldTypes = fieldTypes.toArray(new CsvFieldType[0]);
        this.fields = fields;
    }

    @Override
    public Object[] convertRow(String[] strings) {
        final Object[] objects = new Object[fields.length];
        for (int i = 0; i < fields.length; i++) {
            int field = fields[i];
            objects[i] = convert(fieldTypes[field], strings[field]);
        }
        return objects;
    }
}
