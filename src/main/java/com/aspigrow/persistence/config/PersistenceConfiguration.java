package com.aspigrow.persistence.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.netflix.config.DynamicPropertyFactory;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
public class PersistenceConfiguration {

    private static final String DATASOURCE_CLASS_NAME = "persistence.dataSourceClassName";
    private static final String DATASOURCE_DB_NAME = "persistence.databaseName";
    private static final String DATASOURCE_USERNAME = "persistence.user";
    private static final String DATASOURCE_PASSWORD = "persistence.password";
    private static final String DATASOURCE_PORT = "persistence.portNumber";
    private static final String DATASOURCE_SERVER_NAME = "persistence.serverName";
    private static final String MAX_POOL_SIZE = "persistence.maximumPoolSize";

    private static final String DIALECT = "hibernate.dialect";
    private static final String SHOW_SQL = "hibernate.show_sql";
    private static final String FORMAT_SQL = "hibernate.format_sql";
    private static final String HBM2DDL_AUTO = "hbm2ddl.auto";

    private String getClassName() {
        return DynamicPropertyFactory.getInstance()
                .getStringProperty(DATASOURCE_CLASS_NAME, "com.mysql.jdbc.jdbc2.optional.MysqlDataSource").getValue();
    }

    private int getMaxPoolSize() {
        return DynamicPropertyFactory.getInstance().getIntProperty(MAX_POOL_SIZE, 6).getValue();
    }

    private String getServername() {
        return DynamicPropertyFactory.getInstance()
                .getStringProperty(DATASOURCE_SERVER_NAME, "localhost").getValue();
    }

    private int getPortNumber() {
        return DynamicPropertyFactory.getInstance().getIntProperty(DATASOURCE_PORT, 3306).getValue();
    }

    private String getDatabaseName() {
        return DynamicPropertyFactory.getInstance()
                .getStringProperty(DATASOURCE_DB_NAME, "aspigrow").getValue();
    }

    private String getHbm2DdlAuto() {
        return DynamicPropertyFactory.getInstance().getStringProperty(HBM2DDL_AUTO, "validate").getValue();
    }

    private String getUsername() {
        return DynamicPropertyFactory.getInstance().getStringProperty(DATASOURCE_USERNAME, "root").getValue();
    }

    private String getPassword() {
        return DynamicPropertyFactory.getInstance().getStringProperty(DATASOURCE_PASSWORD, "root").getValue();
    }

    private String getDialect() {
        return DynamicPropertyFactory.getInstance()
                .getStringProperty(DIALECT, "org.hibernate.dialect.MySQLDialect").getValue();
    }

    private Boolean getShowSql() {
        return DynamicPropertyFactory.getInstance().getBooleanProperty(SHOW_SQL, false).getValue();
    }

    private Boolean getFormatSql() {
        return DynamicPropertyFactory.getInstance().getBooleanProperty(FORMAT_SQL, true).getValue();
    }

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("com.aspigrow.persistence");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        Properties props = new Properties();
        props.setProperty("dataSourceClassName", getClassName());
	props.setProperty("dataSource.url", "jdbc:mysql://localhost:3306/"+getDatabaseName());
        props.setProperty("dataSource.user", getUsername());
        props.setProperty("dataSource.password", getPassword());
        props.setProperty("dataSource.databaseName", getDatabaseName());
        props.setProperty("dataSource.serverName", getServername());
        props.setProperty("maximumPoolSize", String.valueOf(getMaxPoolSize()));
        props.setProperty("dataSource.portNumber", String.valueOf(getPortNumber()));
        HikariConfig config = new HikariConfig(props);

        return new HikariDataSource(config);
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put(DIALECT, getDialect());
        properties.put(SHOW_SQL, getShowSql());
        properties.put(FORMAT_SQL, getFormatSql());
        properties.put(HBM2DDL_AUTO, getHbm2DdlAuto());
        return properties;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

    @Bean
    PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessorBean() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
