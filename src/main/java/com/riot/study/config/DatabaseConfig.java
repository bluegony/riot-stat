package com.riot.study.config;

import com.riot.study.config.mybatis.StringNullInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1000773 on 2017. 6. 9..
 */
@Slf4j
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"com.riot.study"})
public class DatabaseConfig {

    @Value("${spring.datasource.initialSize}") private Integer datasourceInitialSize;
    @Value("${spring.datasource.maxActive}") private Integer datasourceMaxActive;
    @Value("${spring.datasource.maxIdle}") private Integer datasourceMaxIdle;
    @Value("${spring.datasource.minIdle}") private Integer datasourceMinIdle;
    @Value("${spring.datasource.validationQuery}") private String validationQuery;
    @Value("${spring.datasource.testWhileIdle}") private Boolean testWhileIdle;
    @Value("${spring.datasource.minEvictableIdleTimeMillis}") private Integer minEvictableIdleTimeMillis;
    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}") private Integer timeBetweenEvictionRunsMillis;
    @Value("${spring.datasource.driverClassName}") private String driverClassName;
    @Value("${spring.datasource.url}") private String url;
    @Value("${spring.datasource.username}") private String userName;
    @Value("${spring.datasource.password}") private String password;
//    @Value("${encrypt.key}") private String secret;


    @Bean
    public DataSource dataSource() {
        org.apache.tomcat.jdbc.pool.DataSource datasource = new org.apache.tomcat.jdbc.pool.DataSource();
        datasource.setDriverClassName(driverClassName);
        datasource.setUrl(url);
        datasource.setUsername(userName);
        datasource.setPassword(password);
//        datasource.setPassword(CipherUtils.decrypt(password, secret));
        datasource.setInitialSize(datasourceInitialSize);
        datasource.setMaxActive(datasourceMaxActive);
        datasource.setMaxIdle(datasourceMaxIdle);
        datasource.setMinIdle(datasourceMinIdle);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);

        return datasource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPlugins(getPlugins()); // insert, update 시 jdbcType를 작성하지 않아도 예외 발생하지 않도록 처리 추가
        sessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis/config.xml"));

        return sessionFactory.getObject();
    }

    /**
     * Oracle insert시 String에서 null이 들어가면 예외 발생 하는 부분 잡기 위한 Plugin 추가
     * @return
     */
    private Interceptor[] getPlugins() {
        List<Interceptor> interceptors = new ArrayList<>();
        interceptors.add(new StringNullInterceptor());
        return interceptors.toArray(new Interceptor[] {});
    }


}

