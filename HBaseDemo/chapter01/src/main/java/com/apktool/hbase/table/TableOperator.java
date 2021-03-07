package com.apktool.hbase.table;

import java.io.Closeable;
import java.io.IOException;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.TableDescriptor;

import com.apktool.hbase.exception.ApktoolIoException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TableOperator implements Closeable {
    private Connection connection;

    public TableOperator() throws ApktoolIoException {
        connection = connect();
    }

    private Connection connect() throws ApktoolIoException {
        if (connection != null) {
            return connection;
        }

        try {
            return ConnectionFactory.createConnection(HBaseConfiguration.create());
        } catch (IOException e) {
            throw new ApktoolIoException(e.getMessage());
        }
    }

    /**
     * Create Table
     *
     * @param table
     * @throws IOException
     */
    public boolean create(TableDescriptor table) {
        try (Admin admin = connection.getAdmin()) {
            admin.createTable(table);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public boolean drop(TableName tableName) {
        try (Admin admin = connection.getAdmin()) {
            if (!admin.isTableDisabled(tableName)) {
                admin.disableTable(tableName);
            }
            admin.deleteTable(tableName);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public TableName[] list() throws ApktoolIoException {
        try (Admin admin = connection.getAdmin()) {
            return admin.listTableNames();
        } catch (IOException e) {
            throw new ApktoolIoException(e.getMessage());
        }
    }

    @Override
    public void close() throws ApktoolIoException {
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (IOException e) {
            throw new ApktoolIoException(e.getMessage());
        }
    }
}