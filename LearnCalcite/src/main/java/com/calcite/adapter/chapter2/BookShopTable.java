package com.calcite.adapter.chapter2;

import com.calcite.adapter.chapter2.storage.Storage;
import org.apache.calcite.DataContext;
import org.apache.calcite.linq4j.AbstractEnumerable;
import org.apache.calcite.linq4j.Enumerable;
import org.apache.calcite.linq4j.Enumerator;
import org.apache.calcite.rel.type.RelDataType;
import org.apache.calcite.rel.type.RelDataTypeFactory;
import org.apache.calcite.schema.ScannableTable;
import org.apache.calcite.schema.impl.AbstractTable;
import org.apache.calcite.sql.type.SqlTypeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BookShopTable extends AbstractTable implements ScannableTable {
    private static Logger logger = LoggerFactory.getLogger(BookShopTable.class);

    private Storage.DummyTable table;
    private RelDataType dataType;

    public BookShopTable(Storage.DummyTable it) {
        this.table = it;
    }


    @Override
    public RelDataType getRowType(RelDataTypeFactory typeFactory) {
        if (dataType == null) {
            RelDataTypeFactory.Builder builder = typeFactory.builder();

            for (Storage.DummyColumn column : this.table.getColumns()) {
                RelDataType sqlType = typeFactory.createJavaType(column.getJavaClass());
                sqlType = SqlTypeUtil.addCharsetAndCollation(sqlType, typeFactory);

                builder.add(column.getName(), sqlType);
            }

            /* 上面的for循环等价于下面的这种写法
             *
             * builder.add("ID", typeFactory.createJavaType(Integer.class));
             * builder.add("NAME", typeFactory.createJavaType(String.class));
             * builder.add("AGE", typeFactory.createJavaType(Integer.class));
             * builder.add("AID", typeFactory.createJavaType(DataTypeMapping.getJavaClassByName("integer")));
             * builder.add("TYPE", typeFactory.createJavaType(DataTypeMapping.getJavaClassByName("varchar")));
             */

            this.dataType = builder.build();

        }


        return this.dataType;
    }


    @Override
    public Enumerable<Object[]> scan(DataContext root) {
        logger.info("scan...");
        return new AbstractEnumerable<Object[]>() {
            @Override
            public Enumerator<Object[]> enumerator() {
                return new Enumerator<Object[]>() {
                    private int cur = 0;

                    @Override
                    public Object[] current() {
                        if (logger.isDebugEnabled()) {
                            logger.debug("cur = " + cur + " => ");
                            for (int i = 0; i < table.getData(cur).length; i++) {
                                logger.debug(table.getData(cur)[i].toString());
                            }
                        }

                        return table.getData(cur++);
                    }

                    @Override
                    public boolean moveNext() {
                        logger.debug("++cur < table.getRowCount() = " + (cur + 1 < table.getRowCount()));
                        return cur < table.getRowCount();
                    }

                    @Override
                    public void reset() {

                    }

                    @Override
                    public void close() {

                    }
                };
            }
        };
    }
}
