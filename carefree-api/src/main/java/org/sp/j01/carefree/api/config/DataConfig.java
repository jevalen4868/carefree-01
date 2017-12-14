package org.sp.j01.carefree.api.config;

import org.sp.j01.carefree.api.db.FamilyRepository;
import org.sp.j01.carefree.api.db.jdbc.JdbcFamilyRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
//@ComponentScan(basePackages = {"org.sp.j01.carefree.api.db"})
public class DataConfig {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("schema.sql")
                .build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public FamilyRepository familyRepository() {
        return new JdbcFamilyRepository(jdbcTemplate(dataSource()));
    }

}
