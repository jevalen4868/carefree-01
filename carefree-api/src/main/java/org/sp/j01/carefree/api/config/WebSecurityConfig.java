package org.sp.j01.carefree.api.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sp.j01.carefree.api.config.firebase.FirebaseAuthenticationProvider;
import org.sp.j01.carefree.api.config.firebase.FirebaseAuthenticationTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LogManager.getLogger(WebSecurityConfig.class);

    @Inject
    ObjectMapper jsonObjectMapper;

    @Bean
    public FirebaseAuthenticationProvider authenticationProvider() {
        return new FirebaseAuthenticationProvider();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // you USUALLY want this
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedHeader("x-firebase-auth");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    public FirebaseAuth firebaseAuth() throws IOException {
        // Read in firebase json.
        InputStream jsonInputStream = WebSecurityConfig.class.getResourceAsStream("/firebase-adminsdk.json");
        JsonNode firebaseJsonNode = jsonObjectMapper.readTree(jsonInputStream);
        // Setup google credentials.
        GoogleCredentials credentials = GoogleCredentials.newBuilder()
                .setAccessToken(new AccessToken(firebaseJsonNode.get("apiKey").textValue(), null))
                .build();
        // Map json fields to firebase firebaseOptions.
        FirebaseOptions firebaseOptions = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .setDatabaseUrl(firebaseJsonNode.get("databaseURL").textValue())
                .setProjectId(firebaseJsonNode.get("projectId").textValue())
                //.setStorageBucket(firebaseJsonNode.get("storageBucket").textValue())
                .build();
        // Initialize!!!
        FirebaseApp.initializeApp(firebaseOptions);
        return FirebaseAuth.getInstance();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return new ProviderManager(Arrays.asList(authenticationProvider()));
    }


    public FirebaseAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        FirebaseAuthenticationTokenFilter authenticationTokenFilter = new FirebaseAuthenticationTokenFilter();
        authenticationTokenFilter.setAuthenticationManager(authenticationManager());
        authenticationTokenFilter.setAuthenticationSuccessHandler((request, response, authentication) -> {
            logger.info("Login was successful, not sure why that doesn't matter quite yet. Maybe handled elsewhere.");
        });
        return authenticationTokenFilter;
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS);
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors()
                .and()
                // we don't need CSRF because our token is invulnerable
                .csrf().disable()
                // All urls must be authenticated (filter for token always fires (/**)
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/**").authenticated()
                .and()
                // don't create session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //.and()
        // Custom JWT based security filter
        httpSecurity
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);


        // disable page caching
        // httpSecurity.headers().cacheControl();
    }
}
