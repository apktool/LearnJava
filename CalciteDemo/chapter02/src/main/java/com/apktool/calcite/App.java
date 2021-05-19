package com.apktool.calcite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private final Logger log = LoggerFactory.getLogger(getClass());

    public void run(String[] args) {
        Properties info = new Properties();
        info.put("caseSensitive", true);
        info.put("unquotedCasing", "TO_UPPER");
        // info.put("typeSystem", "org.apache.kylin.query.calcite.KylinRelDataTypeSystem");
        info.put("conformance", "LENIENT");
        info.put("quoting", "DOUBLE_QUOTE");
        info.put("model", getClass().getClassLoader().getResource("model.json").getPath());

        String sql = "SELECT id,color,units FROM t_table";

        try (
                Connection connection = DriverManager.getConnection("jdbc:calcite:", info);
                Statement stmt = connection.createStatement()
        ) {
            ResultSet result = stmt.executeQuery(sql);
            ResultSetMetaData metaData = result.getMetaData();

            while (result.next()) {
                StringBuilder builder = new StringBuilder();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    builder.append(String.format("column: %s, value: %s | ", metaData.getColumnName(i), result.getString(i)));
                }
                builder.deleteCharAt(builder.length() - 2);
                log.info(builder.toString());
            }

            result.close();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        App app = new App();
        app.run(args);
    }
}
