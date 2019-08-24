package com.calcite.adapter.csv.utils;

import com.calcite.adapter.csv.types.CsvFieldType;

public class SingleColumnRowConverter extends RowConverter<Object> {
    private final CsvFieldType fieldType;
    private final int fieldIndex;

    private SingleColumnRowConverter(CsvFieldType fieldType, int fieldIndex) {
        this.fieldType = fieldType;
        this.fieldIndex = fieldIndex;
    }

    @Override
    public Object convertRow(String[] strings) {
        return convert(fieldType, strings[fieldIndex]);
    }

}
