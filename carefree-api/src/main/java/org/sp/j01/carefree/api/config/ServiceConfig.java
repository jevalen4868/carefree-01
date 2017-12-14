package org.sp.j01.carefree.api.config;

import org.sp.j01.carefree.FamilyService;
import org.sp.j01.carefree.api.db.FamilyRepository;
import org.sp.j01.carefree.api.services.FamilyServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.inject.Inject;

@Configuration
public class ServiceConfig {
    @Inject
    FamilyRepository familyRepository;

    @Bean
    FamilyService familyService() {
        return new FamilyServiceImpl(familyRepository);
    }

}
