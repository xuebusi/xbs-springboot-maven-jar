package com.xuebusi.springboot.maven.db;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.apache.bcel.util.ClassLoaderRepository;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Configuration
@AutoConfigureAfter({DataSourceConfiguration.class})
public class MybatisConfiguration extends MybatisAutoConfiguration {

  @Resource(name = "masterDataSource")
  private DataSource masterDataSource;
  @Resource(name = "slaveDataSource")
  private DataSource slaveDataSource;

  public MybatisConfiguration(MybatisProperties properties, ObjectProvider<Interceptor[]> interceptorsProvider, ResourceLoader resourceLoader, ObjectProvider<DatabaseIdProvider> databaseIdProvider, ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizersProvider) {
    super(properties, interceptorsProvider, resourceLoader, databaseIdProvider, configurationCustomizersProvider);
  }

  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    return super.sqlSessionFactory(roundRobinDataSouceProxy());
  }

  public AbstractRoutingDataSource roundRobinDataSouceProxy(){
    ReadWriteSplitRoutingDataSource proxy = new ReadWriteSplitRoutingDataSource();
    Map<Object,Object> targetDataResources = new ClassLoaderRepository.SoftHashMap();
    targetDataResources.put(DbContextHolder.DbType.MASTER,masterDataSource);
    targetDataResources.put(DbContextHolder.DbType.SLAVE,slaveDataSource);
    proxy.setDefaultTargetDataSource(masterDataSource);//默认源
    proxy.setTargetDataSources(targetDataResources);
    return proxy;
  }
}