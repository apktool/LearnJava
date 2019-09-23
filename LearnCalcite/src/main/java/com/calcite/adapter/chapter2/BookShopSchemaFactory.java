package com.calcite.adapter.chapter2;

import org.apache.calcite.schema.Schema;
import org.apache.calcite.schema.SchemaFactory;
import org.apache.calcite.schema.SchemaPlus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class BookShopSchemaFactory implements SchemaFactory {
    private static Logger logger = LoggerFactory.getLogger(BookShopSchemaFactory.class);

    @Override
    public Schema create(SchemaPlus parentSchema, String name, Map<String, Object> operand) {
        logger.info(name);
        logger.info(operand.toString());

        return new BookShopSchema();
    }
}
