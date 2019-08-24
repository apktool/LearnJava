package com.calcite.adapter.csv;

import com.calcite.adapter.csv.types.CsvFieldType;
import com.calcite.adapter.csv.utils.ArrayRowConverter;
import com.opencsv.CSVReader;
import org.apache.calcite.DataContext;
import org.apache.calcite.adapter.java.JavaTypeFactory;
import org.apache.calcite.linq4j.AbstractEnumerable;
import org.apache.calcite.linq4j.Enumerable;
import org.apache.calcite.linq4j.Enumerator;
import org.apache.calcite.rel.type.RelDataType;
import org.apache.calcite.rel.type.RelDataTypeFactory;
import org.apache.calcite.rel.type.RelProtoDataType;
import org.apache.calcite.schema.ScannableTable;
import org.apache.calcite.schema.impl.AbstractTable;
import org.apache.calcite.sql.type.SqlTypeName;
import org.apache.calcite.util.Pair;
import org.apache.calcite.util.Source;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class CsvScannableTable extends AbstractTable implements ScannableTable {
    protected final Source source;
    protected final RelProtoDataType protoRowType;
    private List<CsvFieldType> fieldTypes;

    public CsvScannableTable(Source source, RelProtoDataType protoRowType) {
        this.source = source;
        this.protoRowType = protoRowType;
    }

    @Override
    public RelDataType getRowType(RelDataTypeFactory typeFactory) {
        if (protoRowType != null) {
            return protoRowType.apply(typeFactory);
        }
        if (fieldTypes == null) {
            fieldTypes = new ArrayList<>();
            return deduceRowType((JavaTypeFactory) typeFactory, source, fieldTypes);
        } else {
            return deduceRowType((JavaTypeFactory) typeFactory, source, null);
        }
    }

    @Override
    public Enumerable<Object[]> scan(DataContext root) {
        final int[] fields = identityList(fieldTypes.size());
        final AtomicBoolean cancelFlag = DataContext.Variable.CANCEL_FLAG.get(root);
        return new AbstractEnumerable<Object[]>() {
            @Override
            public Enumerator<Object[]> enumerator() {
                return new CsvEnumerator<>(source, cancelFlag, null, new ArrayRowConverter(fieldTypes, fields));
            }
        };
    }

    /**
     * Deduces the names and types of a table's columns by reading the first line
     * of a CSV file.
     */
    private RelDataType deduceRowType(JavaTypeFactory typeFactory, Source source, List<CsvFieldType> fieldTypes) {
        final List<RelDataType> types = new ArrayList<>();
        final List<String> names = new ArrayList<>();
        try (CSVReader reader = new CSVReader(source.reader())) {
            String[] strings = reader.readNext();
            if (strings == null) {
                strings = new String[]{"EmptyFileHasNoColumns:boolean"};
            }
            for (String string : strings) {
                final String name;
                final CsvFieldType fieldType;
                final int colon = string.indexOf(':');
                if (colon >= 0) {
                    name = string.substring(0, colon);
                    String typeString = string.substring(colon + 1);
                    fieldType = CsvFieldType.of(typeString);
                    if (fieldType == null) {
                        System.out.println("WARNING: Found unknown type: " + typeString + " in file: " + source.path()
                            + " for column: " + name + ". Will assume the type of column is string");
                    }
                } else {
                    name = string;
                    fieldType = null;
                }
                final RelDataType type;
                if (fieldType == null) {
                    type = typeFactory.createSqlType(SqlTypeName.VARCHAR);
                } else {
                    type = fieldType.toType(typeFactory);
                }
                names.add(name);
                types.add(type);
                if (fieldTypes != null) {
                    fieldTypes.add(fieldType);
                }
            }
        } catch (IOException e) {
            // ignore
        }
        if (names.isEmpty()) {
            names.add("line");
            types.add(typeFactory.createSqlType(SqlTypeName.VARCHAR));
        }
        return typeFactory.createStructType(Pair.zip(names, types));
    }

    /**
     * Returns an array of integers {0, ..., n - 1}.
     */
    private int[] identityList(int n) {
        int[] integers = new int[n];
        for (int i = 0; i < n; i++) {
            integers[i] = i;
        }
        return integers;
    }
}
