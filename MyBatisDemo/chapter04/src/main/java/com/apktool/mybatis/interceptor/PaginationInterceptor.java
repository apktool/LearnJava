package com.apktool.mybatis.interceptor;

import java.sql.Connection;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import com.apktool.mybatis.page.Pageable;
import com.apktool.mybatis.page.Pagination;
import com.apktool.mybatis.utils.BeanUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Intercepts({
        @Signature(
                type = StatementHandler.class,
                method = "prepare",
                args = {Connection.class, Integer.class}
        )
})
public final class PaginationInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();

        // 查询的参数是 Pageable 的实现类
        StatementHandler delegate = (StatementHandler) BeanUtil.getFieldValue(target, "delegate");
        BoundSql boundSql = delegate.getBoundSql();
        Object paramObject = boundSql.getParameterObject();

        if (paramObject instanceof Pageable) {
            String pageSql = new MySqlSqlBuilder().query(new Pagination((Pageable) paramObject), boundSql.getSql());

            BeanUtil.setFieldValue(boundSql, "sql", pageSql);
            BeanUtil.setFieldValue(delegate, "boundSql", boundSql);
            BeanUtil.setFieldValue(delegate.getParameterHandler(), "boundSql", boundSql);
        }

        return invocation.proceed();
    }

    interface SqlBuilder {
        /**
         * 添加分页
         *
         * @param paramMybatisPagination
         * @param paramString
         * @return
         */
        String query(Pagination paramMybatisPagination, String paramString);

        /**
         * 计算总数
         *
         * @param mybatisPagination
         * @param paramString
         * @return
         */
        String count(Pagination mybatisPagination, String paramString);
    }

    private class MySqlSqlBuilder implements SqlBuilder {
        @Override
        public String query(Pagination page, String sql) {
            if (page.getPageSize() == 0 && page.getStartRow() == 0) {
                return sql;
            }
            return String.format("%s LIMIT %d,%d", sql, page.getStartRow(), page.getPageSize());
        }

        @Override
        public String count(Pagination mybatisPagination, String sql) {
            return String.format("SELECT COUNT(1) FROM (%s) AS t", sql);
        }
    }
}