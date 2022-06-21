package com.kyy.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableCaching
public class DefaultConfig {
//    @Bean
//    public HttpMessageConverters customConverters() {
//        return new HttpMessageConverters(false,
//                Collections.<HttpMessageConverter<?>>singleton(new MappingJackson2HttpMessageConverter()));
//    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
//        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
//                        .allowCredentials(false)
                        .allowedMethods("GET", "POST", "PUT", "DELETE");
            }
        };
    }
}
