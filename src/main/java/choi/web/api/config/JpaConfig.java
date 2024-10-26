package choi.web.api.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        value = "choi.web.api.repository.jpa",
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager"
)
public class JpaConfig {

    @Bean(name = "hikariConfig")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    /**
     * datasource
     */
    @Bean(name = "dataSource")
    @Primary
    public HikariDataSource dataSource(@Qualifier("hikariConfig") HikariConfig hikariConfig) {
        return new HikariDataSource(hikariConfig);
    }

    /**
     * jpa entityManagerFactory
     */
    @Bean(name = "entityManagerFactory")
    @Primary
    public EntityManagerFactory entityManagerFactory(@Qualifier("dataSource") DataSource dataSource) {
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setPackagesToScan("choi.web.api.domain");
//        factory.setDataSource(dataSource);
//        factory.setPersistenceUnitName("H2");
//
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setShowSql(true);
//        factory.setJpaVendorAdapter(vendorAdapter);
//
//        Map<String, Object> properties = new HashMap<>();
//        properties.put("hibernate.hbm2ddl.auto", "none");
//        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
//        properties.put("hibernate.format_sql", true);
//        properties.put("hibernate.show_sql", true);
//        properties.put("hibernate.use_sql_comments", true);
//        properties.put("hibernate.globally_quoted_identifiers", true);
//        properties.put("hibernate.physical_naming_strategy", "choi.web.api.config.JpaNamingStrategyConfig");
//        factory.setJpaPropertyMap(properties);
//        factory.afterPropertiesSet();
//
//        return factory.getObject();

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setPackagesToScan("choi.web.api.domain");
        factory.setDataSource(dataSource);
        factory.setPersistenceUnitName("MySQL"); // MySQL로 persistenceUnitName 변경

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        factory.setJpaVendorAdapter(vendorAdapter);

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "create"); // 필요에 따라 "none", "create", "update"로 설정
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.put("hibernate.format_sql", true);
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.use_sql_comments", true);
        properties.put("hibernate.globally_quoted_identifiers", true);
        properties.put("hibernate.physical_naming_strategy", "choi.web.api.config.JpaNamingStrategyConfig");
        factory.setJpaPropertyMap(properties);
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean(name = "transactionManager")
    @Primary
    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);

        return transactionManager;
    }

}