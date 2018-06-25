package com.riot.study.config.mybatis;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.Properties;

/**
 * Created by bluegony on 2017. 6. 9..
 */

@Intercepts(@Signature(type=Executor.class, method="update", args={MappedStatement.class, Object.class}))
public class StringNullInterceptor implements Interceptor {

    private static final Logger log = LoggerFactory.getLogger(StringNullInterceptor.class);

    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        boolean isTarget = checkCommandType(mappedStatement.getSqlCommandType());
        Object parameter = invocation.getArgs()[1];
        if (!isTarget || parameter == null) {
            return invocation.proceed();
        }

        BeanWrapper bw = new BeanWrapperImpl(parameter);
        for(PropertyDescriptor pd : bw.getPropertyDescriptors()) {
            if (String.class.isAssignableFrom(pd.getPropertyType())) {
                String parameterClassName = parameter.getClass().getSimpleName();
                final String name = pd.getName();
                try {
                    Object value = bw.getPropertyValue(name);
                    if (value == null) {
//                        log.debug("{} class {} property is null! convert empty(\"\") value", parameterClassName, name);
                        if (bw.isWritableProperty(name)) {
                            bw.setPropertyValue(name, "");
                        }
                    }
                } catch (Exception e) {
                    log.warn("parameterClassName: {}, property: {}", parameterClassName, name, e);
                    // ignore
                }
            }
        }

        return invocation.proceed();
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {
        // Nothing
    }

    private boolean checkCommandType(SqlCommandType sqlCommandType) {
        return SqlCommandType.INSERT == sqlCommandType || SqlCommandType.UPDATE == sqlCommandType;
    }

}