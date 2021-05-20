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
        info.put("conformance", "LENIENT");
        info.put("model", "inline:" + model);
        info.put("quoting", "DOUBLE_QUOTE");

        String sql = "SELECT p.sex, COUNT(id) AS cnt " +
                "FROM t_mem_table AS p " +
                "WHERE name = 'tom'" +
                "GROUP BY p.sex " +
                "HAVING p.sex = 'male' " +
                "ORDER BY p.sex";

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

    private static String model = "{" +
            "    \"version\": \"1.0\", " +
            "    \"defaultSchema\": \"default\", " +
            "    \"schemas\": [" +
            "        {" +
            "            \"name\": \"default\", " +
            "            \"type\": \"custom\", " +
            "            \"factory\": \"com.apktool.calcite.MemSchemaFactory\", " +
            "            \"operand\": {" +
            "                \"table\": \"T_MEM_TABLE\", " +
            "                \"schema\": [" +
            "                    {" +
            "                        \"name\": \"ID\", " +
            "                        \"type\": \"INTEGER\"" +
            "                    }," +
            "                    {" +
            "                        \"name\": \"NAME\", " +
            "                        \"type\": \"VARCHAR\"" +
            "                    }," +
            "                    {" +
            "                        \"name\": \"SEX\", " +
            "                        \"type\": \"VARCHAR\"" +
            "                    }" +
            "                ], " +
            "                \"value\": [" +
            "                    {" +
            "                        \"ID\": 0, " +
            "                        \"NAME\": \"bob\"," +
            "                        \"SEX\": \"male\"" +
            "                    }, " +
            "                    {" +
            "                        \"ID\": 1, " +
            "                        \"NAME\": \"tom\"," +
            "                        \"SEX\": \"male\"" +
            "                    }, " +
            "                    {" +
            "                        \"ID\": 2, " +
            "                        \"NAME\": \"tom\"," +
            "                        \"SEX\": \"female\"" +
            "                    }" +
            "                ]" +
            "            }" +
            "        }" +
            "    ]" +
            "}";
}
