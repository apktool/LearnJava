package com.calcite.adapter.chapter4;

import org.apache.calcite.config.CalciteConnectionConfigImpl;
import org.apache.calcite.config.Lex;
import org.apache.calcite.jdbc.CalciteConnection;
import org.apache.calcite.jdbc.CalcitePrepare;
import org.apache.calcite.model.ModelHandler;
import org.apache.calcite.prepare.CalciteCatalogReader;
import org.apache.calcite.rel.type.RelDataTypeSystem;
import org.apache.calcite.server.CalciteServerStatement;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.SqlSelect;
import org.apache.calcite.sql.dialect.OracleSqlDialect;
import org.apache.calcite.sql.fun.SqlStdOperatorTable;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.type.SqlTypeFactoryImpl;
import org.apache.calcite.sql.validate.*;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class App {
    public static void main(String[] args) throws SQLException, IOException {
        String schema = "{" +
            "  \"version\": \"1.0\"," +
            "  \"defaultSchema\": \"SALES\"," +
            "  \"schemas\": [" +
            "    {" +
            "      \"name\": \"SALES\"," +
            "      \"tables\": [" +
            "        {" +
            "          \"type\": \"custom\"," +
            "          \"name\": \"Parts\"," +
            "          \"factory\": \"com.calcite.adapter.chapter1.OrdersTableFactory\"" +
            "        }" +
            "      ]" +
            "    }" +
            "  ]" +
            "}";

        Properties info = new Properties();
        info.setProperty("lex", "JAVA");


        try (
            CalciteConnection connection = DriverManager.getConnection("jdbc:calcite:inline", info).unwrap(CalciteConnection.class);
        ) {
            // ModelHandler reads the schema and load the schema to connection's root schema and sets the default schema
            new ModelHandler(connection, "inline:" + schema);

            CalciteServerStatement statement = connection.createStatement().unwrap(CalciteServerStatement.class);
            // 解析配置 - mysql设置
            SqlParser.Config mysqlConfig = SqlParser.configBuilder().setLex(Lex.MYSQL).build();
            // 创建解析器
            SqlParser parser = SqlParser.create("", mysqlConfig);
            // Sql语句
            String sql = "SELECT * FROM Parts WHERE color = 'red'";
            // 解析sql
            SqlNode sqlNode = parser.parseQuery(sql);
            // 还原某个方言的SQL
            System.out.println(sqlNode.toSqlString(OracleSqlDialect.DEFAULT));

            System.out.println("--------------------------------");

            CalcitePrepare.Context prepareContext = statement.createPrepareContext();
            // sql validate（会先通过Catalog读取获取相应的metadata和namespace）
            SqlTypeFactoryImpl factory = new SqlTypeFactoryImpl(RelDataTypeSystem.DEFAULT);
            CalciteCatalogReader calciteCatalogReader = new CalciteCatalogReader(
                prepareContext.getRootSchema(),
                prepareContext.getDefaultSchemaPath(),
                factory,
                new CalciteConnectionConfigImpl(new Properties()));

            // 校验（包括对表名，字段名，函数名，字段类型的校验。）
            SqlValidator validator = SqlValidatorUtil.newValidator(
                SqlStdOperatorTable.instance(),
                calciteCatalogReader,
                factory,
                SqlConformanceEnum.DEFAULT
            );
            // 校验后的SqlNode
            SqlNode validateSqlNode = validator.validate(sqlNode);
            System.out.println(validateSqlNode.toString());

            System.out.println("--------------------------------");

            SqlValidatorNamespace namespace = validator.getNamespace(sqlNode);
            System.out.println(namespace.fieldExists("color"));

            System.out.println("--------------------------------");

            List<SqlMoniker> sqlMonikerList = new ArrayList<>();

            SqlValidatorScope selectScope = validator.getSelectScope((SqlSelect) validateSqlNode);
            selectScope.findAllColumnNames(sqlMonikerList);

            for (SqlMoniker sqlMoniker : sqlMonikerList) {
                System.out.println(sqlMoniker.id());
            }

        } catch (SqlParseException e) {
            e.printStackTrace();
        }
    }
}
