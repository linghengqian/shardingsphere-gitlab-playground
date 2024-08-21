package io.github.linghengqian;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;

import java.time.Duration;

public class SimpleTest {
    @Test
    void testSimple() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("org.apache.shardingsphere.driver.ShardingSphereDriver");
        hikariConfig.setJdbcUrl("jdbc:shardingsphere:classpath:test-sharding.yaml");
        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
        Awaitility.await().atMost(Duration.ofMinutes(1L)).ignoreExceptions().until(() -> {
            hikariDataSource.getConnection().close();
            return true;
        });
        hikariDataSource.close();
    }
}
