package org.gradle.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.gradle.dao.UserDao;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class AppConfig {
	@Autowired
	private ApplicationContext applicationContext;
	
	@Bean
	public UserDao userDao(SqlSession session) {
		return new UserDao(session);
	}
	
	@Bean
	public DataSource dataSource(){
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:mem:testdb;INIT=RUNSCRIPT FROM 'classpath:init.sql'");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        
        return dataSource;
	}
	
	@Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
	
	@Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setTypeAliasesPackage("org.gradle.vo");
        sessionFactory.setMapperLocations(applicationContext.getResources("classpath:sql/*.xml"));
        return (SqlSessionFactory)sessionFactory.getObject();
    }
	
	@Bean
	public SqlSession session() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
}
