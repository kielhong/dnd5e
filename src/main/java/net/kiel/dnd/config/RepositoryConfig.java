package net.kiel.dnd.config;

import java.util.Properties;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class RepositoryConfig {
    @Value("${datasource.driverClassName}") private String datasourceDriverClassName;
    @Value("${datasource.url}") private String datasourceUrl;
    @Value("${datasource.username}") private String datasourceuserName;
    @Value("${datasource.password}") private String datasourcePassword;
    
    @Value("${hibernate.dialect}") private String hibernateDialect;
    @Value("${hibernate.driverClass}") private String hibernateDriverClass;
    @Value("${hibernate.show_sql}") private String hibernateShowSql;
    
    
    @Bean
    public DataSource dataSource() {
        DataSource datasource = new DataSource();
        datasource.setDriverClassName("com.mysql.jdbc.Driver");
        datasource.setUrl("jdbc:mysql://127.0.0.1:3306/dnd?autoReconnect=true&characterEncoding=UTF8");
        datasource.setUsername("root");
        datasource.setPassword("123!@#");
        
        return datasource;
    }
    
    // hibernate sessionFactory
    @Bean 
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setHibernateProperties(hibernateProperties());
        sessionFactory.setPackagesToScan(new String[] {"net.kiel.dnd.model"});
        
        return sessionFactory;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory().getObject());
        
        return txManager;
    }
    
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
    
    @SuppressWarnings("serial")
    Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.connection.driver_class", hibernateDriverClass);
                setProperty("hibernate.dialect", hibernateDialect);
                setProperty("hibernate.show_sql", "true");
                setProperty("hibernate.format_sql", "true");
                setProperty("hibernate.globally_quoted_identifiers", "true");
            }
        };
    }
}
