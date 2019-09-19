package com.calcite.adapter;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.PrintStream;
import java.sql.*;
import java.util.Properties;

public class App {
    static final Logger logger = LogManager.getLogger(App.class);
    static final String prefix = "conf";
    static final String model = "model.json";

    public static void main(String[] args) throws SQLException {
        String sql = "select * from DEPTS";
        Connection connection = null;

        Statement statement = null;
        try {
            Properties info = new Properties();
            info.put("model", jsonPath(model));

            connection = DriverManager.getConnection("jdbc:calcite:", info);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            output(resultSet, System.out);
        } finally {
            close(connection, statement);
        }
    }

    private static String jsonPath(String file) {
        ClassLoader loader = App.class.getClassLoader();
        String jsonPath = null;
        try {
            jsonPath = loader.getResource(prefix + "/" + file).getPath();
        } catch (NullPointerException e) {
            try {
                jsonPath = loader.getResource(file).getPath();
            } catch (Exception ex) {
                logger.error("I Can't find " + file + " in " + jsonPath);
                System.exit(-1);
            }
        }

        return jsonPath;
    }


    private static void output(ResultSet resultSet, PrintStream out)
        throws SQLException {
        final ResultSetMetaData metaData = resultSet.getMetaData();
        final int columnCount = metaData.getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; ; i++) {
                out.print(resultSet.getString(i));
                if (i < columnCount) {
                    out.print(", ");
                } else {
                    out.println();
                    break;
                }
            }
        }
    }

    private static void close(Connection connection, Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.error("I Can't close statement");
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("I Can't close connection");
            }
        }
    }
}
