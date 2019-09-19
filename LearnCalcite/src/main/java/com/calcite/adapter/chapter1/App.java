package com.calcite.adapter.chapter1;

import com.google.common.io.Resources;
import org.apache.calcite.jdbc.CalciteConnection;
import org.apache.calcite.model.ModelHandler;
import org.apache.calcite.plan.RelOptUtil;
import org.apache.calcite.rel.RelNode;
import org.apache.calcite.rel.RelRoot;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class App {
    public static void main(String[] args) throws SQLException, IOException {
        URL url = App.class.getClassLoader().getResource("model-chapter1.json");
        String schema = Resources.toString(url, Charset.defaultCharset());

        Properties info = new Properties();
        info.setProperty("lex", "JAVA");

        String sql = "SELECT * FROM Parts WHERE color = 'red'";

        try (
            CalciteConnection connection = DriverManager.getConnection("jdbc:calcite:", info).unwrap(CalciteConnection.class)
        ) {
            // ModelHandler reads the schema and load the schema to connection's root schema and sets the default schema
            new ModelHandler(connection, "inline:" + schema);
            QueryPlanner planner = new QueryPlanner(
                connection.getRootSchema().getSubSchema(connection.getSchema())
            );
            RelRoot relRoot = planner.getRelRoot(sql);

            RelNode logicalPlan = relRoot.project();

            System.out.println(logicalPlan.getDescription());
            System.out.println(RelOptUtil.toString(logicalPlan));
        }


        try (
            CalciteConnection connection = DriverManager.getConnection("jdbc:calcite:", info).unwrap(CalciteConnection.class);
            Statement stmt = connection.createStatement()
        ) {
            // ModelHandler reads the schema and load the schema to connection's root schema and sets the default schema
            new ModelHandler(connection, "inline:" + schema);

            ResultSet result = stmt.executeQuery(sql);
            while (result.next()) {
                System.out.println(result.getString(1) + ", " + result.getString(2) + ", " + result.getString(3));
            }
            result.close();
        }
    }
}
