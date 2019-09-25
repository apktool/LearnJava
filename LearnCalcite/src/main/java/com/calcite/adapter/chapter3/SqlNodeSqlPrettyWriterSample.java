package com.calcite.adapter.chapter3;

import org.apache.calcite.config.Lex;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.dialect.MysqlSqlDialect;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.pretty.SqlPrettyWriter;

public class SqlNodeSqlPrettyWriterSample {
    public static void main(String[] args) throws SqlParseException {
        SqlNodeSqlPrettyWriterSample sample = new SqlNodeSqlPrettyWriterSample();
        String sql = "select * from emps where id = 1";

        sample.format1(sql);

        System.out.println("--------------------");

        sample.format2(sql);
    }

    public void format1(String sql) throws SqlParseException {
        SqlPrettyWriter prettyWriter = new SqlPrettyWriter(MysqlSqlDialect.DEFAULT);
        SqlParser.Config mysqlConfig = SqlParser.configBuilder().setLex(Lex.MYSQL).build();
        SqlParser parser = SqlParser.create(sql, mysqlConfig);
        SqlNode sqlNode = parser.parseQuery();

        String format = prettyWriter.format(sqlNode);
        System.out.println(format);
    }

    public void format2(String sql) throws SqlParseException {
        SqlPrettyWriter prettyWriter = new SqlPrettyWriter(MysqlSqlDialect.DEFAULT);
        SqlParser.Config mysqlConfig = SqlParser.configBuilder().setLex(Lex.MYSQL).build();
        SqlParser parser = SqlParser.create(sql, mysqlConfig);
        SqlNode sqlNode = parser.parseQuery();

        sqlNode.unparse(prettyWriter, 0, 0);
        System.out.println(prettyWriter.toSqlString());
    }
}
