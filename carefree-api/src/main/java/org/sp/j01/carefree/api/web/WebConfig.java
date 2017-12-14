package org.sp.j01.carefree.api.web;

import org.sp.j01.carefree.api.controller.FamilyController;
import org.sp.j01.carefree.api.db.FamilyRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.inject.Inject;

@Configuration
@EnableWebMvc
// Defining controllers here. Not scanning in.
//@ComponentScan({"org.sp.j01.carefree.api.controller"})
public class WebConfig implements WebMvcConfigurer {
    @Inject
    FamilyRepository familyRepository;

    @Bean
    public FamilyController familyController() {
        return new FamilyController(familyRepository);
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}
