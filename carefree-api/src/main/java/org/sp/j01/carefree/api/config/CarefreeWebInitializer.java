package org.sp.j01.carefree.api.config;

import org.sp.j01.carefree.api.web.WebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class CarefreeWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    super.onStartup(servletContext);
    // TODO This should be property-ified. Needs to be set before the spring context is instantiated.
    System.setProperty("spring.profiles.active", "dev");
  }

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class<?>[] { WebSecurityConfig.class, RootConfig.class };
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class<?>[] { WebConfig.class };
  }

  @Override
  protected String[] getServletMappings() {
    return new String[] { "/" };
  }

}