package com.calcite.adapter.chapter3;

import org.apache.calcite.config.Lex;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.dialect.OracleSqlDialect;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;

public class SqlParserSample {
    public static void main(String[] args) throws SqlParseException {
        // SQL语句
        String sql = "select * from emps where id = 1";
        parserSql(sql);

        System.out.println("---------------");

        // SQL表达式
        String exp = "id = 1 and name='1'";
        parserExp(exp);
    }

    /**
     * 解析SQL
     *
     * @throws SqlParseException
     */
    public static void parserSql(String sql) throws SqlParseException {
        // 解析配置 - mysql设置
        SqlParser.Config mysqlConfig = SqlParser.configBuilder().setLex(Lex.MYSQL).build();
        // 创建解析器
        SqlParser parser = SqlParser.create("", mysqlConfig);
        // 解析sql
        SqlNode sqlNode = parser.parseQuery(sql);
        // 还原某个方言的SQL
        System.out.println(sqlNode.toSqlString(OracleSqlDialect.DEFAULT));
    }

    /**
     * 解析表达式
     *
     * @throws SqlParseException
     */
    public static void parserExp(String exp) throws SqlParseException {
        // 解析配置 - mysql设置
        SqlParser.Config mysqlConfig = SqlParser.configBuilder().setLex(Lex.MYSQL).build();
        // 创建解析器
        SqlParser parser = SqlParser.create(exp, mysqlConfig);
        // 解析sql
        SqlNode sqlNode = parser.parseExpression();
        // 还原某个方言的SQL
        System.out.println(sqlNode.toSqlString(OracleSqlDialect.DEFAULT));
    }
}
