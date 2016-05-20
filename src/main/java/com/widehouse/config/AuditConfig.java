package com.widehouse.config;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Created by kiel on 2016. 4. 19..
 */
@Configuration
@EnableJpaAuditing
public class AuditConfig {
}
