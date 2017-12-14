package org.sp.j01.carefree.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@Import(DataConfig.class)
// All config files in config package are scanned.
@ComponentScan(basePackages = {
        "org.sp.j01.carefree.api.config" })
public class RootConfig {
    @Bean
    public ObjectMapper jsonObjectMapper() {
        return new ObjectMapper();
    }
}
