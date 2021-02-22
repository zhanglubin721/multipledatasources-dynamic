package com.zhanglubin.multipledatasourcesdynamic.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhanglubin
 * @date 2021/2/22
 */
@Configuration
@MapperScan(basePackages = {"com.zhanglubin.multipledatasourcesdynamic.mapper"})
public class MybatisConfig {

    @Bean("datasourcemyself")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.datasourcemyself")
    public DataSource datasourcemyself() {
        return DataSourceBuilder.create().build();
    }

    @Bean("datasourcework")
    @ConfigurationProperties(prefix = "spring.datasource.datasourcework")
    public DataSource datasourcework() {
        return DataSourceBuilder.create().build();
    }

    @Bean("dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put("datasourcemyself", datasourcemyself());
        dataSourceMap.put("datasourcework", datasourcework());
        // 将 datasourcemyself 数据源作为默认指定的数据源
        dynamicDataSource.setDefaultDataSource(datasourcemyself());
        // 将 datasourcemyself 和 datasourcework 数据源作为指定的数据源
        dynamicDataSource.setDataSources(dataSourceMap);
        return dynamicDataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        // 配置数据源，此处配置为关键配置，如果没有将 dynamicDataSource作为数据源则不能实现切换
        sessionFactory.setDataSource(dynamicDataSource());
        sessionFactory.setTypeAliasesPackage("com.zhanglubin.multipledatasourcesdynamic.domain");
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath:mybatis/mapper/*Mapper.xml"));
        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        // 配置事务管理, 使用事务时在方法头部添加@Transactional注解即可
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}
