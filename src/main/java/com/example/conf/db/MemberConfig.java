package com.example.conf.db;


import com.alibaba.druid.util.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;

@Configuration
@MapperScan(basePackages = "com.example.mapper.member", sqlSessionTemplateRef = "memberSqlSessionTemplate")
public class MemberConfig implements IDataSourrceEnvironmentAware {
    private static final Logger logger = LoggerFactory.getLogger(MemberConfig.class);
    private Environment environment;
    private RelaxedPropertyResolver propertyResolver;
    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
        this.propertyResolver = new RelaxedPropertyResolver(environment, "spring.datasource.member.");
    }

    @Bean(name = "memberDataSource",initMethod = "init", destroyMethod = "close")
    public DataSource dataSource() throws SQLException {
        if (StringUtils.isEmpty(propertyResolver.getProperty("url"))) {
            logger.error("数据库连接池配置错误!"
                    + " 请检查Spring配置文件，目前的配置有："
                    + Arrays.toString(environment.getActiveProfiles()));
            throw new ApplicationContextException(
                    "数据库连接池配置错误");
        }
        return this.setCommonDataSourceProperties(propertyResolver);
    }

    @Bean(name = "memberSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("memberDataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/member/*Mapper.xml"));
        return bean.getObject();
    }

    @Bean(name = "memberTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("memberDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "memberSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("memberSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}