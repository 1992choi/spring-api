package choi.web.api.common.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories(
        basePackages = "choi.web.api.common.repository.jpa",
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager"
)
public class JpaConfig {

    // === Master/Slave HikariConfig ===
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public HikariConfig masterHikariConfig() { return new HikariConfig(); }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public HikariConfig slaveHikariConfig() { return new HikariConfig(); }

    // === Master/Slave DataSource ===
    @Bean(name = "masterDataSource")
    public DataSource masterDataSource(@Qualifier("masterHikariConfig") HikariConfig cfg) {
        return new HikariDataSource(cfg);
    }

    @Bean(name = "slaveDataSource")
    public DataSource slaveDataSource(@Qualifier("slaveHikariConfig") HikariConfig cfg) {
        return new HikariDataSource(cfg);
    }

    // === Routing DataSource (master/slave 분기) + LazyConnectionDataSourceProxy ===
    @Primary
    @Bean(name = "dataSource")
    public DataSource routingDataSource(
            @Qualifier("masterDataSource") DataSource master,
            @Qualifier("slaveDataSource") DataSource slave
    ) {
        Map<Object, Object> targets = new HashMap<>();
        targets.put("master", master);
        targets.put("slave", slave);

        MasterSlaveRoutingDataSourceConfig routing = new MasterSlaveRoutingDataSourceConfig();
        routing.setTargetDataSources(targets);
        routing.setDefaultTargetDataSource(master);
        routing.afterPropertiesSet();

        // 핵심: LazyConnectionDataSourceProxy로 감싸야 트랜잭션 readOnly 시점에서 라우팅 가능
        return new LazyConnectionDataSourceProxy(routing);
    }

    // === EntityManagerFactory ===
    @Primary
    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory entityManagerFactory(@Qualifier("dataSource") DataSource routingDataSource) {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setPackagesToScan("choi.web.api.common.domain");
        factory.setDataSource(routingDataSource);
        factory.setPersistenceUnitName("MySQL");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        factory.setJpaVendorAdapter(vendorAdapter);

        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.hbm2ddl.auto", "create-drop");
        props.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        props.put("hibernate.format_sql", true);
        props.put("hibernate.show_sql", true);
        props.put("hibernate.use_sql_comments", true);
        props.put("hibernate.globally_quoted_identifiers", true);
        props.put("hibernate.physical_naming_strategy", "choi.web.api.common.config.JpaNamingStrategyConfig");
        factory.setJpaPropertyMap(props);
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    // === Transaction Manager ===
    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory emf
    ) {
        return new JpaTransactionManager(emf);
    }
}
