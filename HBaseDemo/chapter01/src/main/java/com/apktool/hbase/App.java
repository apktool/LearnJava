package com.apktool.hbase;

import java.util.Arrays;

import org.apache.hadoop.hbase.TableName;

import com.apktool.hbase.exception.ApktoolIoException;
import com.apktool.hbase.table.TableOperator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
    public static void main(String[] args) {
        try (TableOperator operator = new TableOperator()) {
            TableName[] tableNames = operator.list();
            Arrays.asList(tableNames).forEach(t -> System.out.println(t.getNameAsString()));
        } catch (ApktoolIoException e) {
            log.error(e.getMessage());
        }
    }
}
