package org.kizhaku.springaop.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DatabaseHealthCheck {

    @Bean(name = "ProductDbCheck")
    public HealthIndicator health(JdbcTemplate jdbcTemplate) {
        return () -> {
            try {
                Integer dbResult = jdbcTemplate.queryForObject("Select 1", Integer.class);
                if (dbResult != null && dbResult == 1)
                    return Health.up().withDetail("Product DB", "OK").build();
                else
                    return Health.down().withDetail("Product DB", "Unexpected result").build();
            } catch (Exception ex) {
                return Health.down(ex).withDetail("Product DB", "Error connecting to database").build();
            }
        };
    }
}
