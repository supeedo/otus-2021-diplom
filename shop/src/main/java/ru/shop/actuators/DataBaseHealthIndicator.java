package ru.shop.actuators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Component
public class DataBaseHealthIndicator implements HealthIndicator {
    private static final Logger log = LoggerFactory.getLogger(DataBaseHealthIndicator.class);

    private final DataSource dataSource;

    public DataBaseHealthIndicator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Health health() {
        try {
            dataSource.getConnection();
            return Health.up().status(Status.UP).withDetail("Message", "database is working").build();
        } catch (SQLException e) {
            log.error("Database connection errors", e);
            return Health.down().status(Status.DOWN).withDetail("Message", "database is not working. Have some problem").build();
        }
    }
}