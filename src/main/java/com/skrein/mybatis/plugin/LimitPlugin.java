package com.skrein.mybatis.plugin;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.util.Properties;

/**
 * @Author: hujiansong
 * @Date: 2019/6/10 10:21
 * @since: 1.8
 */
@Slf4j
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class LimitPlugin implements Interceptor {

    private Integer maxLimit;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("Limit plugin intercept");
        StatementHandler target = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = SystemMetaObject.forObject(target);
        String originSql = (String) metaObject.getValue("delegate.boundSql.sql");
        originSql = originSql.trim().replace(";","");
        originSql = originSql + " limit " + maxLimit;
        metaObject.setValue("delegate.boundSql.sql",originSql);
        return invocation.proceed();
    }


    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        String maxLimit = properties.getProperty("maxLimit", "10");
        this.maxLimit = Integer.parseInt(maxLimit);
    }
}
