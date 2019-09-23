package com.calcite.adapter.chapter2.storage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.calcite.sql.type.SqlTypeName;

import java.util.*;

/**
 * 用于模拟数据库结构及数据
 * *******************
 * AUTHOR
 * ID | NAME | AGE
 * 1   Abbey  33
 * 2   Haley  23
 * 3   Abby   32
 * 4   Paige  42
 * *******************
 * BOOK
 * ID | AID | NAME | TYPE
 * 1     1    HBase   Java
 * 2     2    Kafka   Scala
 * 3     1    Hive    Java
 * 4     3    Spark   Scala
 */

public class Storage {
    public static final String TABLE_AUTHOR = "AUTHOR";
    public static final String TABLE_BOOK = "BOOK";
    public static Hashtable<String, DummyTable> bag = new Hashtable<>();

    static {
        DummyColumn id = new DummyColumn("ID", "integer");
        DummyColumn name = new DummyColumn("NAME", "varchar");
        DummyColumn age = new DummyColumn("AGE", "integer");
        DummyColumn aid = new DummyColumn("AID", "integer");
        DummyColumn type = new DummyColumn("TYPE", "varchar");

        DummyTable author = new DummyTable(TABLE_AUTHOR);
        author.addColumn(id).addColumn(name).addColumn(age);
        author.addRow(1, "Abbey", 33);
        author.addRow(2, "Haley", 23);
        author.addRow(3, "Abby", 32);
        author.addRow(4, "Paige", 42);
        bag.put(TABLE_AUTHOR, author);

        DummyTable book = new DummyTable(TABLE_BOOK);
        book.addColumn(id).addColumn(aid).addColumn(name).addColumn(type);
        book.addRow(1, 1, "HBase", "Java");
        book.addRow(2, 2, "Kafka", "Scala");
        book.addRow(3, 1, "Hive", "Java");
        book.addRow(4, 3, "Spark", "Scala");
        bag.put(TABLE_BOOK, book);
    }

    public static Collection<DummyTable> getTables() {
        return bag.values();
    }

    public static DummyTable getTable(String tableName) {
        return bag.get(tableName);
    }

    @Data
    @RequiredArgsConstructor
    public static class DummyTable {
        @NonNull
        private String name;
        private List<DummyColumn> columns;
        private List<List<Object>> datas = new ArrayList<>();

        public DummyTable addColumn(DummyColumn dc) {
            if (this.columns == null) {
                this.columns = new ArrayList<>();
            }
            this.columns.add(dc);
            return this;
        }


        public Object[] getData(int index) {
            return this.datas.get(index).toArray();
        }

        public int getRowCount() {
            return this.datas.size();
        }

        public void addRow(Object... objects) {
            this.datas.add(Arrays.asList(objects));
        }

    }

    @Data
    @AllArgsConstructor
    public static class DummyColumn {
        private String name;
        private String type;

        public Class getJavaClass() {
            return DataTypeMapping.getJavaClassByName(this.type);
        }

        public SqlTypeName getSqlTypeName() {
            return DataTypeMapping.getSqlTypeByName(this.type);
        }
    }

}
