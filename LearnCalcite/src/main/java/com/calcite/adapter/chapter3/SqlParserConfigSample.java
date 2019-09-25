package com.calcite.adapter.chapter3;

import org.apache.calcite.avatica.util.Casing;
import org.apache.calcite.avatica.util.Quoting;
import org.apache.calcite.config.Lex;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.parser.SqlParser;

public class SqlParserConfigSample {
    public static void main(String[] args) throws Exception {
        SqlParserConfigSample sample = new SqlParserConfigSample();
        sample.unquotedCasing();

        System.out.println("--------------------");

        sample.identifierMaxLength();
    }

    /**
     * 转义字符外-大小写转换
     *
     * @throws Exception
     */
    public void unquotedCasing() throws Exception {
        String sql = "select id, name from hr.emp where dept_id = 1";

        SqlParser.Config config = SqlParser.configBuilder()
            .setLex(Lex.JAVA)
            .setQuoting(Quoting.BACK_TICK)
            .setUnquotedCasing(Casing.TO_LOWER)
            .build();
        SqlParser parser = SqlParser.create(sql, config);
        SqlNode sqlNode = parser.parseQuery();
        System.out.println(sql);
        printSql(sqlNode);
    }

    /**
     * 字段长度
     *
     * @throws Exception
     */
    public void identifierMaxLength() throws Exception {
        // 设置字段长度最大个数
        try {
            String sql = "select id, name from hr.emp where dept_id = 1";
            SqlParser.Config config = SqlParser.configBuilder()
                .setIdentifierMaxLength(3)
                .build();
            SqlParser parser = SqlParser.create("", config);
            SqlNode sqlNode = parser.parseQuery(sql);
            System.out.println(sql);
            printSql(sqlNode);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }


    public void printSql(SqlNode sqlNode) {
        System.out.println(sqlNode.toString());
    }

}
