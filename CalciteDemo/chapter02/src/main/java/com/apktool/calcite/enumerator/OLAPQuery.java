package com.apktool.calcite.enumerator;

import org.apache.calcite.DataContext;
import org.apache.calcite.linq4j.AbstractEnumerable;
import org.apache.calcite.linq4j.Enumerable;
import org.apache.calcite.linq4j.Enumerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OLAPQuery extends AbstractEnumerable<Object[]> implements Enumerable<Object[]> {
    private static final Logger log = LoggerFactory.getLogger(OLAPQuery.class);

    private final DataContext optiqContext;
    private final EnumeratorTypeEnum type;

    public enum EnumeratorTypeEnum {
        MEM, // using mem
        OLAP, //finish query with Cube or II, or a combination of both
        LOOKUP_TABLE, //using a snapshot of lookup table
        HIVE, //using hive
        COL_DICT // using a column's dictionary
    }

    public OLAPQuery(EnumeratorTypeEnum type) {
        this(null, type);
    }

    public OLAPQuery(DataContext optiqContext, EnumeratorTypeEnum type) {
        this.optiqContext = optiqContext;
        this.type = type;
    }

    @Override
    public Enumerator<Object[]> enumerator() {
        switch (type) {
            case MEM:
                return new MemEnumerator<>();
            default:
                return new EmptyEnumerator();
        }
    }

    public static class EmptyEnumerator implements Enumerator<Object[]> {

        public EmptyEnumerator() {
            log.debug("Using empty enumerator");
        }

        @Override
        public void close() {
        }

        @Override
        public Object[] current() {
            return null;
        }

        @Override
        public boolean moveNext() {
            return false;
        }

        @Override
        public void reset() {
        }
    }
}
