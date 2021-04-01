package com.apktool.hbase.table;

import java.util.Arrays;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptor;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptorBuilder;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.TableDescriptorBuilder;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.apktool.hbase.exception.ApktoolIoException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TableOperatorTest {
    String table = "tmp";
    String columnFamily = "cf";

    @Test
    public void create() throws ApktoolIoException {
        ColumnFamilyDescriptor cfDescriptor = ColumnFamilyDescriptorBuilder.of(columnFamily);

        TableDescriptorBuilder tableBuilder = TableDescriptorBuilder.newBuilder(TableName.valueOf(table));
        tableBuilder.setColumnFamily(cfDescriptor);

        try (TableOperator operator = new TableOperator()) {
            boolean res = operator.create(tableBuilder.build());
            Assertions.assertTrue(res);
            log.info("create table return {}", res);
        } catch (ApktoolIoException e) {
            throw new ApktoolIoException(e.getMessage());
        }
    }

    @Test
    public void drop() throws ApktoolIoException {
        try (TableOperator operator = new TableOperator()) {
            boolean res = operator.drop(TableName.valueOf(table));
            Assertions.assertTrue(res);
            log.info("drop table return {}", res);
        } catch (ApktoolIoException e) {
            throw new ApktoolIoException(e.getMessage());
        }
    }

    @Test
    public void scan() throws ApktoolIoException {
        try (TableOperator operator = new TableOperator()) {
            ResultScanner scanner = operator.scan(TableName.valueOf(table), new Scan());
            for (Result item : scanner) {
                System.out.println(item);
            }
        } catch (ApktoolIoException e) {
            throw new ApktoolIoException(e.getMessage());
        }
    }

    @Test
    public void put() throws ApktoolIoException {
        try (TableOperator operator = new TableOperator()) {
            Put put = new Put(Bytes.toBytes("row01"))
                    .addColumn(Bytes.toBytes("cf"), Bytes.toBytes("f1"), Bytes.toBytes("value"));

            operator.put(TableName.valueOf(table), put);
        } catch (ApktoolIoException e) {
            throw new ApktoolIoException(e.getMessage());
        }
    }

    @Test
    public void list() {
        try (TableOperator operator = new TableOperator()) {
            TableName[] tableNames = operator.list();
            Arrays.asList(tableNames).forEach(t -> log.info(t.getNameAsString()));
        } catch (ApktoolIoException e) {
            log.error(e.getMessage());
        }
    }
}